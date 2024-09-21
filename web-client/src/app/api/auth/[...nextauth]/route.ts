import { nextAuthConfig } from '@/src/lib/next-auth-options';
import NextAuth from "next-auth";

const handler = NextAuth(nextAuthConfig);

export { handler as GET, handler as POST };