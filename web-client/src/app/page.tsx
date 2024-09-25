
import { Button } from "@nextui-org/button";
import React from "react";

import Link from "next/link";
import { auth, signOut } from "@/src/auth";
import { useSession } from "next-auth/react";
import { TestDisplay } from "@/src/components/test-display";
import { Navbar } from "@/src/components/navbar";


export default async function Home() {
  const session = await auth();
  return (
    <>
      <Navbar/>
      <section>
        <Button>Click me</Button>
        {session ? (

          <form
            action={async () => {
              "use server"
              await signOut()
            }}
          >
            <Button type={"submit"}>Sign out</Button>
          </form>
        ) : (          <Link href="/login">
            <Button color="primary">Sign In</Button>
          </Link>
        )}
        <Button color={"primary"}>Click me</Button>
      </section>
    </>
  );
}
