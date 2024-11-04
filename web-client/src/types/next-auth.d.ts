import { type DefaultSession, User } from "next-auth";
import { JWT } from "next-auth/jwt";
import { AdapterSession } from "@auth/core/adapters";

declare module "next-auth" {
  interface Session {
    session: {} & AdapterSession & DefaultSession;
    // Do not question it works
    token?: JWT;
    error?: "RefreshTokenError";
  }
}

declare module "next-auth/jwt" {
  interface JWT {
    id_token?: string;
    access_token?: string;
    expires_at?: number;
    refresh_token?: string;
    error?: "RefreshTokenError";
  }
}

declare module "next-auth" {
  interface RegisterUser extends User {
    username?: string | null;
    email?: string | null;
    enabled?: boolean | null;
    firstName?: string | null;
    lastName?: string | null;
    credentials?: {
      type?: string | null
      value?: string | null
    } | null;
  }
}
