import { Button } from "@nextui-org/button";
import { signOut } from "@/src/auth";

export function Signout() {
  return (
    <form
      action={async () => {
        'use server';
        await signOut();
      }
      }>
      <Button type={"submit"}>signout</Button>
    </form>
  )
}