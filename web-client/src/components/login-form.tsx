"use client";

import { useFormStatus } from "react-dom";
import { FormEvent } from "react";
import { signIn } from "next-auth/react";
import { Input } from "@nextui-org/input";
import { Button } from "@nextui-org/button";
import { Link } from "@nextui-org/link";

export default function LoginForm({ csrfToken }: { csrfToken: string }) {
  const { pending } = useFormStatus();

  const onSubmit = async (event: FormEvent<HTMLFormElement>) => {
    event.preventDefault();

    const formData = new FormData(event.currentTarget);

    await signIn("login", {
      username: formData.get("username"),
      password: formData.get("password"),
      redirect: true,
      csrfToken: csrfToken
    });
  };

  return (
    <>
      <form
        action="/api/auth/callback/login"
        onSubmit={onSubmit}
        method="post"
        className={"flex justify-center items-center"}
      >
        <div
          className={
            "w-[20dvw] bg-content1 rounded-md text-center mt-4 flex-row justify-center items-center"
          }
        >
          <h4 className={"mt-3"}>Login to LearningPulse</h4>
          <Input
            className={"w-[18dvw] mx-auto mt-3"}
            type="text"
            name="username"
            labelPlacement="inside"
            label="Username"
            isRequired={true}
            autoFocus={true}
            variant={"underlined"}
            autoComplete={"off"}
            style={{ background: "none" }}
          />
          <Input
            className={"w-[18dvw] mx-auto my-3"}
            type="password"
            name="password"
            labelPlacement="inside"
            label="Password"
            isRequired={true}
            variant={"underlined"}
            style={{ background: "none" }}
          />
          <div>
            <span>Don't have an account? </span>

            <Link
              className={" mt-2"}
              href={"/register"} /*Todo: /register*/
              style={{ color: "#006FEE" }}
              //color={"primary"} remind me to send a pipe bomb to whomever set the colour to inherit by default
            >
              {" "}
              Register
            </Link>
          </div>
          <div>
            <Button
              // TODO make tshe button redirect to some url
              aria-disabled={pending}
              type="submit"
              className={"bg-primary my-3"}
            >
              {" "}
              Log in
            </Button>
          </div>
        </div>
      </form>
    </>
  );
}
