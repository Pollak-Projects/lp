import "@/src/styles/globals.css";
import { Viewport } from "next";

import { Providers } from "@/src/app/providers";

import { Navbar } from "@/src/components/navbar";

export default function RootLayout({
                                     children,
                                   }: {
  children: React.ReactNode;
}) {
  return (

<>

  {children}
</>




  );
}
