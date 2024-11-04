"use client";

import { Input } from "@nextui-org/input";
import { Button } from "@nextui-org/button";
import { FormEvent } from "react";
import { signIn } from "next-auth/react";
import { useFormStatus } from "react-dom";

export default function RegisterForm() {
  const { pending } = useFormStatus();

  const onSubmit = async (event: FormEvent<HTMLFormElement>) => {
    event.preventDefault();

    const formData = new FormData(event.currentTarget);

    await signIn("signup", {
      user: JSON.stringify({
        username: formData.get("username"),
        email: formData.get("email"),
        enabled: true,
        firstName: formData.get("firstName"),
        lastName: formData.get("lastName"),
        credentials: {
          type: "password",
          value: formData.get("password")
        }
      }),
      redirect: true
    });
  };
  return (
    <>
      <form
        action="/api/auth/callback/signup"
        onSubmit={onSubmit}
        method="post"
        className={"flex justify-center items-center"}
      >
        <div
          className={
            "w-[20dvw] bg-content1 rounded-md text-center mt-4 flex-row justify-center items-center"
          }
        >
          <h4 className={"mt-3"}>Register to LearningPulse</h4>
          <Input
            type="text"
            name="username"
            label="Username"
            isRequired={true}
          />
          <Input
            type="email"
            name="email"
            label="Email"
            isRequired={true}
          />
          <Input
            type="text"
            name="firstName"
            label="First Name"
            isRequired={true}
          />
          <Input
            type="text"
            name="lastName"
            label="Last Name"
            isRequired={true}
          />
          <Input
            type="password"
            name="password"
            label="Password"
            isRequired={true}
          />
          <div>
            <Button
              // TODO make tshe button redirect to some url
              aria-disabled={pending}
              type="submit"
              className={"bg-primary my-3"}
            >
              Register
            </Button>
          </div>
        </div>
      </form>
    </>
  );
}