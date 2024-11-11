"use client";

import {
  Navbar as NextUINavbar,
  NavbarContent,
  NavbarItem,
} from "@nextui-org/navbar";
import NextLink from "next/link";
import React, { useEffect, useState } from "react";

import { Avatar } from "@nextui-org/avatar";
import { auth } from "@/src/auth";
import { useSession } from "next-auth/react";

export const NavbarCustom = () => {
  const [loggedIn, setLoggedIn] = useState(false);

  // useEffect(() => {
  //    setLoggedIn(useSession().status === "authenticated")
  // }, []);

  return (
    <>
      <NextUINavbar
        className="rounded-md w-10/11 bg-content1"
        maxWidth="full"
        style={{
          marginLeft: "0.3em",
          marginRight: "0.3em",
          borderRadius: "0.6em",
        }}
        height={"3em"}
      >
        <NavbarContent className="flex flex-nowrap">
          <ul className="flex gap-4  w-full">
            <NavbarItem>
              <NextLink className={"align-middle"} href={"/"}>
                Home
              </NextLink>
            </NavbarItem>
            <NavbarItem>
              <NextLink className={"align-middle"} href={"/quizSelect"}>
                Quiz
              </NextLink>
            </NavbarItem>
            <NavbarItem>
              {/*THIS IS TEMPORARY*/}
              <NextLink className={"align-middle"} href={"/quiz-create"}>
                QuizCreate
              </NextLink>
            </NavbarItem>
          </ul>

          <ul className="flex gap-4 justify-end items-center">
            <NavbarItem>
              {!loggedIn ? (
                <>
                  <NextLink href={"/login"} className={"align-middle"}>
                    Log in
                  </NextLink>
                  <span className={"align-middle"}> or </span>
                  <NextLink href={"/login"} className={"align-middle"}>
                    Register
                  </NextLink>
                </>
              ) : (
                <>
                  <NextLink href={"/login"} className={"align-middle"}>
                    Sign out
                  </NextLink>
                </>
              )}
            </NavbarItem>
            <NavbarItem>
              <span className={"align-middle"}>UserName</span>
            </NavbarItem>
            <Avatar name="idk" />
          </ul>

          {/*
            {session ? ( // Todo: make the login and register only work if you aren't logged in
              <>
                <span>UserName</span>
                <Avatar name={"User"} size={"sm"} className={"ml-auto"} />
              </>
            ) : (
              <NextLink href={"/login"}>Login or Register</NextLink> // Todo: make the Login and the Register link route to a different path. Do after declaring register path.
            )}*/}
        </NavbarContent>
      </NextUINavbar>
    </>
  );
};
