import { Button } from "@nextui-org/button";
import React from "react";

import Link from "next/link";
import { auth, signOut } from "@/src/auth";

import { Navbar } from "@/src/components/navbar";

export default async function Home() {
  const session = await auth();
  return (
    <>
      <section className={"pt-2"}>
        <Navbar loggedIn={!!session} />

        <div
          className={"flex flex-col place-items-center align-middle gap-4 mt-3"}
        >
          <Button>Click me</Button>
          {session ? (
            <form
              action={async () => {
                await signOut();
              }}
            >
              <Button type={"submit"}>Sign out</Button>
            </form>
          ) : (
            <Link href="/login">
              <Button color="primary">Sign In</Button>
            </Link>
          )}
          <Button color={"primary"}>Click me</Button>
        </div>
      </section>
    </>
  );
}
