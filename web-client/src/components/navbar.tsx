import {
  Navbar as NextUINavbar,
  NavbarContent,
  NavbarItem,
} from "@nextui-org/navbar";
import NextLink from "next/link";
import React from "react";
import { AvatarIcon } from "@nextui-org/shared-icons";
import { Avatar } from "@nextui-org/avatar";

export const Navbar = () => {
  return (
    <>
        <NextUINavbar
          className="rounded-md w-20 bg-secondary"
          maxWidth="full"
          style={{ marginLeft: "0.3em", marginRight: "0.3em", borderRadius: "0.8em"}}
          height={"3em"}

        >
          <NavbarContent className="flex">
            <ul className="flex gap-4  w-full">
              <NavbarItem>
                <NextLink className={"align-middle"} href={"/"}>Home</NextLink>
              </NavbarItem>
              <NavbarItem>
                <NextLink  className={"align-middle"} href={"/quiz"}>Quiz</NextLink>
              </NavbarItem>
            </ul>
            <span>UserName</span>
            <Avatar name={"User"} size={"sm"} className={"ml-auto"}/>
          </NavbarContent>
        </NextUINavbar>

    </>
  );
};
