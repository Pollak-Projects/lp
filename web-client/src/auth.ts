import NextAuth, { Account, Session } from "next-auth";
import Keycloak from "next-auth/providers/keycloak"
import { JWT } from "next-auth/jwt";
import { jwtDecode } from "jwt-decode";
import { Awaitable } from "@auth/core/types";

export const { handlers, signIn, signOut, auth } = NextAuth({
  session: { strategy: "jwt" },
  debug: true,
  providers: [Keycloak],
  events: {
    async signOut() {
      // This is a SERIOUSLY hacky way to do this, but it works.
      const session: Session | null = await auth();

      const url = `${process.env.KEYCLOAK_SESSION_END_URL}?id_token_hint=${encodeURIComponent(session?.token?.id_token!)}&post_logout_redirect_uri=${encodeURIComponent(process.env.HOST_URL!)}`; //?id_token_hint=${idToken}&post_logout_redirect_uri=${process.env.HOST_URL}

      try {
        const resp = await fetch(url, {method: "GET"});
      } catch (error) {
        console.error("Failed to logout:", error);
      }
    }
  },
  callbacks: {
    async jwt({token, account}): Promise<JWT> {
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
          const res = await fetch(`${process.env.KEYCLOAK_TOKEN_URL}`, {
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