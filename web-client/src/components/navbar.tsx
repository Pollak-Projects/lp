import {
  Navbar as NextUINavbar,
  NavbarContent,
  NavbarItem,
} from "@nextui-org/navbar";
import NextLink from "next/link";
import React, { useEffect, useState } from "react";
import { AvatarIcon } from "@nextui-org/shared-icons";
import { Avatar } from "@nextui-org/avatar";
import { auth } from "@/src/auth";
import { signOut } from "next-auth/react";
import { Session } from "next-auth";

export const Navbar = () => {

  return (
    <>
        <NextUINavbar
          className="rounded-md w-20 bg-secondary jst"
          maxWidth="full"
          style={{ marginLeft: "0.3em", marginRight: "0.3em", borderRadius: "0.6em"}}
          height={"3em"}

        >
          <NavbarContent className="flex flex-nowrap">
            <ul className="flex gap-4  w-full">
              <NavbarItem>
                <NextLink className={"align-middle"} href={"/"}>Home</NextLink>
              </NavbarItem>
              <NavbarItem>
                <NextLink className={"align-middle"} href={"/quiz"}>Quiz</NextLink>
              </NavbarItem>
            </ul>

            <ul className="flex gap-4 justify-end items-center">
              <NavbarItem>
                <NextLink href={"/login"} className={"align-middle"}>Login or Register</NextLink>
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
