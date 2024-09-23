// 'use client'

import { Button } from "@nextui-org/button";
import { signIn } from "@/src/auth";

export function Signin() {
  return (
    <form
      action={async () => {
        'use server';
        await signIn("keycloak")
      }
    }>
      <Button type={"submit"}>dsasasdffdss</Button>
    </form>
  )
}