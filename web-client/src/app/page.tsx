import { Button } from "@nextui-org/button";
import React from "react";

import Link from "next/link";
import { auth } from "@/src/auth";

import { NavbarCustom } from "@/src/components/NavbarCustom";

export default async function Home() {
  const session = await auth();
  return (
    <>
      <section className={"pt-2"}>
        <NavbarCustom/>

        <div
          className={"flex flex-col place-items-center align-middle gap-4 mt-3"}
        >
          <Button>Click me</Button>
          {session ? (
            <></>
          ) : (
            <>
              <Link href="/login">
                <Button color="primary">Sign In</Button>
              </Link>
              <Link href="/register">
                <Button color="secondary">Register</Button>
              </Link>
            </>
          )}
          <Button color={"primary"}>Click me</Button>
        </div>
      </section>
    </>
  );
}
