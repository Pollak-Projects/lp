import "@/src/styles/globals.css";
import { Viewport } from "next";

import { Providers } from "@/src/app/providers";

import { Navbar } from "@/src/components/navbar";

export const viewport: Viewport = {
  themeColor: [
    { media: "(prefers-color-scheme: light)", color: "white" },
    { media: "(prefers-color-scheme: dark)", color: "black" },
  ],
};


export default function RootLayout(
  {
    children,
  }: {
    children: React.ReactNode
  }) {
  return (
    <main style={{width: "21.5em"}}>
        <div className={"bg-secondary mt-2 rounded-md w-full"}
        style={{borderRadius: "0.6em"}}>
          {children}</div>

    </main>
  )
}
