import "@/src/styles/globals.css";
import { Viewport } from "next";

import { Providers } from "@/src/app/providers";

import { NavbarCustom } from "@/src/components/NavbarCustom";

export default function RootLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return <>{children}</>;
}
