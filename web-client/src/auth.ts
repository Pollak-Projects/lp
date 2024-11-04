import NextAuth, { Account, RegisterUser, Session } from "next-auth";
import CredentialsProvider from "next-auth/providers/credentials";
import { JWT } from "next-auth/jwt";
import { fetch } from "next/dist/compiled/@edge-runtime/primitives";
import { URLSearchParams } from "node:url";

export const { handlers, signIn, signOut, auth } = NextAuth({
  session: { strategy: "jwt" },
  debug: true,
  providers: [
    CredentialsProvider({
      id: "signup",
      async authorize(credentials: any, req) {
        console.log("Authorizing with credentials\n", JSON.parse(credentials.user));
        const user: RegisterUser = JSON.parse(credentials.user) as RegisterUser;
        console.log("Creating new user\n", user.username);
        try {
          const accessToken = await fetch(process.env.AUTH_KEYCLOAK_TOKEN_URL!, {
            method: "POST",
            headers: {
              "Content-Type": "application/x-www-form-urlencoded"
            },
            body: new URLSearchParams({
              grant_type: "client_credentials",
              client_id: process.env.AUTH_KEYCLOAK_REGISTER_ID!,
              client_secret: process.env.AUTH_KEYCLOAK_REGISTER_SECRET!,
              scope: "openid"
            })
          });

          const access = await accessToken.json();
          if (!accessToken.ok) throw access;

          const registeredResponse = await fetch(process.env.AUTH_KEYCLOAK_REGISTRATION_URL!, {
            method: "POST",
            headers: {
              "Authorization": `Bearer ${access.access_token}`,
              "Content-Type": "application/json"
            },
            body: JSON.stringify({
              username: user.username!,
              email: user.email!,
              enabled: user.enabled!,
              firstName: user.firstName!,
              lastName: user.lastName!,
              credentials: [{
                type: user.credentials?.type!,
                value: user.credentials?.value!
              }]
            })
          });

          if (!registeredResponse.ok) throw registeredResponse;

          const res = await fetch(process.env.AUTH_KEYCLOAK_TOKEN_URL!, {
            method: "POST",
            headers: {
              "Content-Type": "application/x-www-form-urlencoded"
            },
            body: new URLSearchParams({
              grant_type: "password",
              client_id: process.env.AUTH_KEYCLOAK_ID!,
              client_secret: process.env.AUTH_KEYCLOAK_SECRET!,
              username: user.username!,
              password: user.credentials?.value!,
              scope: "openid"
            })
          });
          const tokenOrError = await res.json();
          if (!res.ok) throw tokenOrError;

          console.log("Logging in with credentials\n", tokenOrError);

          return tokenOrError as JWT;
        } catch (error) {
          console.error("Error registering:", error);
          return null;
        }
      }
    }),
    CredentialsProvider({
      id: "login",
      name: "Credentials",
      credentials: {
        username: { type: "text" },
        password: { type: "password" }
      },
      async authorize(credentials, req) {
        console.log("Authorizing with credentials\n");
        try {
          const res = await fetch(process.env.AUTH_KEYCLOAK_TOKEN_URL!, {
            method: "POST",
            headers: {
              "Content-Type": "application/x-www-form-urlencoded"
            },
            body: new URLSearchParams({
              grant_type: "password",
              client_id: process.env.AUTH_KEYCLOAK_ID!,
              client_secret: process.env.AUTH_KEYCLOAK_SECRET!,
              username: credentials?.username as string || "",
              password: credentials?.password as string || "",
              scope: "openid"
            })
          });
          const tokenOrError = await res.json();
          if (!res.ok) throw tokenOrError;

          console.log("Logging in with credentials\n", tokenOrError);

          return tokenOrError as JWT;
        } catch (error) {
          console.error("Error logging in:", error);
          return null;
        }
      }
    })
  ],
  pages: {
    signIn: "/login",
    newUser: "/register"
  },
  events: {
    async signOut() {
      // This is a SERIOUSLY hacky way to do this, but it works.
      const session: Session | null = await auth();

      const url = `${process.env.KEYCLOAK_SESSION_END_URL}?id_token_hint=${encodeURIComponent(session?.token?.id_token!)}&post_logout_redirect_uri=${encodeURIComponent(process.env.HOST_URL!)}`;

      try {
        const resp = await fetch(url, {method: "GET"});
      } catch (error) {
        console.error("Failed to logout:", error);
      }
    }
  },
  callbacks: {
    async signIn({
       user,
       account
      } : {
      user: any,
      account: Account | null
    }) {
      console.log("User signed in\n", user, account)
      // These aren't read only, so it's just ts being stupid
      // @ts-ignore
      account!.id_token = user.id_token
      // @ts-ignore
      account!.access_token = user.access_token
      // @ts-ignore
      account!.refresh_token = user.refresh_token
      account!.expires_at = user.expires_at
      return true
    },
    async jwt({token, account}): Promise<JWT> {
      console.log("JWT\n", token, account)
      if (account) {
        return {
          ...token,
          id_token: account.id_token,
          access_token: account.access_token,
          refresh_token: account.refresh_token,
          expires_at: account.expires_at
        }
      } else if (Date.now() < token.expires_at! * 1000) {
        // Subsequent logins, but the `access_token` is still valid
        console.log("Token is still valid")
        return token
      } else {
        if (!token.refresh_token) throw new TypeError("Missing refresh token");
        console.log("Refreshing token")
        try {
          const res = await fetch(`${process.env.AUTH_KEYCLOAK_TOKEN_URL}`, {
            method: "POST",
            headers: {
              "Content-Type": "application/x-www-form-urlencoded"
            },
            body: new URLSearchParams({
              client_id: process.env.AUTH_KEYCLOAK_ID!,
              client_secret: process.env.AUTH_KEYCLOAK_SECRET!,
              grant_type: "refresh_token",
              refresh_token: token.refresh_token!
            })
          });
          const tokensOrError = await res.json();
          if (!res.ok) throw tokensOrError

          const newToken = tokensOrError as {
            id_token: string
            access_token: string,
            expires_in: number,
            refresh_token: string,
          }

          console.log("old token\n", token);
          console.log("new token\n", newToken);

          token.id_token = newToken.id_token
          token.access_token = newToken.access_token
          token.expires_at = Math.floor(
            Date.now() / 1000 + newToken.expires_in
          )
          if (newToken.refresh_token) {
            token.refresh_token = newToken.refresh_token
          }
          return token
        } catch (error) {
          console.error("Error refreshing token:", error)
          token.error = "RefreshTokenError"
          return token
        }
      }
    },
    async session({ session, token } ) {
      // token is the returned value of `jwt()`
      return { ...session, token }
      // Can just return token if you want.
    }
  }
})