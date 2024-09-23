import NextAuth from "next-auth"
import Keycloak from "next-auth/providers/keycloak"

export const { handlers, signIn, signOut, auth } = NextAuth({
  session: { strategy: "jwt" },
  debug: true,
  providers: [Keycloak],
  events: {
    async signOut() {
      console.log('signOut');
      const session = await auth();
      console.log(session?.access_token);
      await fetch(`${process.env.KEYCLOAK_SESSION_END_URL}
        &id_token_hint=${session?.access_token}
        &post_logout_redirect_uri=${encodeURIComponent(process.env.HOST_URL || "")}`, {method: "POST"})//.then(res => console.log(res))
    }
  },
  callbacks: {
    async jwt({token, account}) {
      if (account) {
        token = Object.assign({}, token, { access_token: account.access_token });
      }
      return token
    },
    async session({ session, token }) {
      // token is the returned value of `jwt()`
      return { ...session, ...token }
      // Can just return token if you want.
    }
  }
})