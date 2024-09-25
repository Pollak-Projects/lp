//not used
import { Button } from "@nextui-org/button";
import { auth, signOut } from "@/src/auth";
import Link from "next/link";
import React, { ReactElement, useEffect, useState } from "react";
import { Session } from "next-auth";

const SignOutButton = () => {

  const [session, setSession] = useState<Session | null>(null)


  useEffect(() => {
    const fetchSession = async () => {
      try {
        const userSession = await auth()
        setSession(userSession)
      } catch (error) {

        setSession(null);
      }
    };

    fetchSession();
  }, [])

  return (
    <>
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
    </>
  );
};

export default SignOutButton;