
import { Button } from "@nextui-org/button";
import React from "react";

import Link from "next/link";
import SignOutButton from "@/src/components/SignOutButton";
import { auth, signOut } from "@/src/auth";
import { useSession } from "next-auth/react";

export default async function Home() {

  const session = await auth();
  return (
    <section className="flex flex-col items-center bg-amber-300 justify-center gap-4 py-8 md:py-10">
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
      ) : (
        <Link href="/login">
          <Button color="primary">Sign In</Button>
        </Link>
      )}
      <Button color={"primary"}>Click me</Button>
    </section>
  );
}
