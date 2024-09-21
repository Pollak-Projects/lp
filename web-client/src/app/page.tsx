import { Button } from "@nextui-org/button";
import React from "react";
import getServerSession from "next-auth";
import { nextAuthConfig } from "@/src/lib/next-auth-options";
import { session } from "next-auth/core/routes";
import { SignIn, SignOut } from "@/src/components/next-auth-buttons";

export default async function Home() {
  const session = await getServerSession(nextAuthConfig);
  console.log(session);
  return (
    <section className="flex flex-col items-center justify-center gap-4 py-8 md:py-10">
      <Button>Click me</Button>
      {!!session ?
        <>
          <div>Your name is {session.name}</div>
          <div><SignOut/></div>
        </> :
        <div><SignIn/></div>}
    </section>
  );
}
