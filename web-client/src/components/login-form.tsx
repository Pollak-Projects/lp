"use client";

import { useFormStatus } from "react-dom";
import { FormEvent } from "react";
import { signIn } from "next-auth/react";
import { Input } from "@nextui-org/input";
import { Button } from "@nextui-org/button";
import NextLink from "next/link";

export default function LoginForm() {
  const { pending } = useFormStatus();

  const onSubmit = async (event: FormEvent<HTMLFormElement>) => {
    event.preventDefault();

    const formData = new FormData(event.currentTarget);

    await signIn("credentials", {
      username: formData.get("username"),
      password: formData.get("password"),
      redirect: true,
    });
  };

  return (
    <>
      <form
        action="/api/auth/callback/credentials"
        onSubmit={onSubmit}
        method="post"
      >
        <div>
          <h4>Login to LearningPulse</h4>
          <Input
            type="text"
            name="username"
            labelPlacement="inside"
            label="Username"
            isRequired={true}
            autoFocus={true}
            variant={"underlined"}
            autoComplete={"off"}
            style={{background: "none"}}


          />
          <Input
            type="password"
            name="password"
            labelPlacement="inside"
            label="Password"
            isRequired={true}
            variant={"underlined"}
            style={{background: "none"}}

          />
          <div>
            <span className={"font-sans"}>Don't have an account? </span>

            <NextLink
              href={"/"} /*Todo: /register*/
              style={{color: "#006FEE"}}
              //color={"primary"} remind me to send a pipe bomb to whomever set the colour to inherit by default
            > Register</NextLink>
          </div>
          <div>
            <Button
              // TODO make tshe button redirect to some url
              aria-disabled={pending}
              type="submit"
              className={"bg-primary"}
            > Log in
            </Button>
          </div>
        </div>
      </form>
    </>
  );
}
