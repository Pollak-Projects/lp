"use client";

import { Navbar as NextUINavbar, NavbarContent, NavbarItem } from "@nextui-org/navbar";
import { link } from "@nextui-org/theme";
import NextLink from "next/link";
import React, { useRef } from "react";
import { Avatar } from "@nextui-org/avatar";
import clsx from "clsx";
import { signOut, useSession } from "next-auth/react";

import { siteConfig } from "@/src/config/site";
import { Button } from "@nextui-org/button";

export const NavbarCustom = () => {
  const loggedIn = useRef(false);

  loggedIn.current = useSession().status === "authenticated";

  const handleLogout = () => {
    signOut().then(r => console.log("Logged out", r));
  };

  return (
    <>
      <NextUINavbar
        className="rounded-md w-10/11 bg-content1"
        height={"3em"}
        maxWidth="full"
        style={{
          marginLeft: "0.3em",
          marginRight: "0.3em",
          borderRadius: "0.6em",
        }}
      >
        <NavbarContent className="flex flex-nowrap">
          <ul className="flex gap-4  w-full">
            {siteConfig.navItems.map((item, index) => (
              <NavbarItem key={index}>
                <NextLink
                  className={clsx(
                    link({ color: "foreground" }),
                    "data-[active=true]:text-primary data-[active=true]:font-medium"
                  )}
                  color={"foreground"}
                  href={item.href}
                >
                  {item.label}
                </NextLink>
              </NavbarItem>
            ))}
          </ul>

          <ul className="flex gap-4 justify-end items-center">
            <NavbarItem>
              {!loggedIn.current ? (
                <>
                  <NextLink className={"align-middle"} href={"/login"}>
                    Log in
                  </NextLink>
                  <span className={"align-middle"}> or </span>
                  <NextLink className={"align-middle"} href={"/login"}>
                    Register
                  </NextLink>
                </>
              ) : (
                <>
                  <Button className={"align-middle"} onPress={handleLogout}>
                    Sign out
                  </Button>
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
