"use client";
import { signIn, signOut } from "next-auth/react";
import { Button } from "@nextui-org/button";

export const SignIn = () => {
  return <Button onClick={() => signIn("keycloak")}>Sign in</Button>;
};

export const SignOut = () => {
  return <Button onClick={() => signOut()}>Sign out</Button>;
};