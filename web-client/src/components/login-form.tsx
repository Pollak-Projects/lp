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
        <Input
          type="text"
          name="username"
          labelPlacement="inside"
          label="Username"
          required={true}
          autoFocus={true}
        />
        <Input
          type="password"
          name="password"
          labelPlacement="inside"
          label="Password"
          required={true}
        />
        <Button aria-disabled={pending} type="submit">
          Submit button
        </Button>
      </form>
    </>
  );
}
