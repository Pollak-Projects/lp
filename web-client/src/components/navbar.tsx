import {
  Navbar as NextUINavbar,
  NavbarContent,
  NavbarItem,
} from "@nextui-org/navbar";
import NextLink from "next/link";

export const Navbar = () => {
  return (
    <NextUINavbar
      className="rounded-md w-79/80 mx-3 bg-[#191919]"
      maxWidth="full"
    >
      <NavbarContent className="" justify="start">
        <ul className="flex gap-4 justify-start">
          <NavbarItem>
            <NextLink href={"/"}>Home</NextLink>
          </NavbarItem>
          <NavbarItem>
            <NextLink href={"/quiz"}>Quiz</NextLink>
          </NavbarItem>
        </ul>
      </NavbarContent>
    </NextUINavbar>
  );
};
