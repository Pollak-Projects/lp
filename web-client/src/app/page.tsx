import { Button } from "@nextui-org/button";
import React from "react";
import { auth, signOut } from "@/src/auth";
import { Signout } from "@/src/components/signout";
import Link from "next/link";

export default async function Home() {
  const session = await auth();
  return (
    <section className="flex flex-col items-center bg-amber-300 justify-center gap-4 py-8 md:py-10">
      <Button>Click me</Button>
      {session ? (
        <Button
          onClick={() => {
            //
            signOut();
          }}
        />
      ) : (
        <Link href="/login">
          <Button color="primary">Sing in </Button>
        </Link>
      )}
      <Button color={"primary"}>Click me</Button>
    </section>
  );
}
