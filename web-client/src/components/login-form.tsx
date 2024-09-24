"use client";

import { useFormStatus } from "react-dom";
import { FormEvent } from "react";
import { signIn } from "next-auth/react";
import { Input } from "@nextui-org/input";
import { Button } from "@nextui-org/button";

export default function LoginForm() {
  const { pending } = useFormStatus();

  const onSubmit = async (event: FormEvent<HTMLFormElement>) => {
    event.preventDefault();

    const formData = new FormData(event.currentTarget);

    await signIn("credentials", {
      username: formData.get("username"),
      password: formData.get("password"),
      // redirect: true,
    });
  };

  return (
    <>
      <form
        action="/api/auth/callback/credentials"
        onSubmit={onSubmit}
        method="post"
      >
        <div className={"flex gap-4 flex-col"}>
          <Input
            type="text"
            name="username"
            labelPlacement="inside"
            label="Username"
            isRequired={true}
            autoFocus={true}
            variant={"underlined"}
            autoComplete={"off"}

          />
          <Input
            type="password"
            name="password"
            labelPlacement="inside"
            label="Password"
            isRequired={true}
            variant={"underlined"}
          />
        </div>
          <Button aria-disabled={pending} type="submit"
                  className={"bg-primary"}>
            Submit button
            <div>

            </div>
          </Button>

      </form>
    </>
  );
}
