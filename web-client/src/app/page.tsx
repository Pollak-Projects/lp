import { Button } from "@nextui-org/button";
import React from "react";
import { Signin } from "@/src/components/signin";
import { auth } from "@/src/auth";
import { Signout } from "@/src/components/signout";

export default async function Home() {
  const session = await auth()
  console.log(session)
  return (
    <section className="flex flex-col items-center bg-amber-300 justify-center gap-4 py-8 md:py-10">
      <Button>Click me</Button>
      { session ? <Signout/> : <Signin/>}
    <section className="flex flex-col items-center justify-center gap-4 py-8 md:py-10">
      <Button color={"primary"}>Click me</Button>
    </section>
  )
}
