import {
  Navbar as NextUINavbar,
  NavbarContent,
  NavbarItem,
} from "@nextui-org/navbar";
import NextLink from "next/link";

export const Navbar = () => {
  return (
    <NextUINavbar maxWidth="xl" position="sticky">
      <NavbarContent className="basis-1/5 sm:basis-full" justify="start">
        <ul className="hidden lg:flex gap-4 justify-start ml-2">
          <NavbarItem>
            <NextLink href={"/"}>Home</NextLink>
          </NavbarItem>
          <NavbarItem>
            <NextLink href={"/dashboard"}>Dashboard</NextLink>
          </NavbarItem>
        </ul>
      </NavbarContent>
    </NextUINavbar>
  );
};
