import "@/src/styles/globals.css";
import { Metadata, Viewport } from "next";

import { Providers } from "./providers";

import { fontSans } from "@/src/config/fonts";
import React from "react";

import { siteConfig } from "@/src/config/site";

export const metadata: Metadata = {
  title: {
    default: siteConfig.name,
    template: "%s | " + siteConfig.name
  },
  description: siteConfig.description,
  icons: {
    icon: "/favicon.ico",
    shortcut: "/favicon.ico",
    apple: "/favicon.ico"
  }
};

export const viewport: Viewport = {
  themeColor: [
    { media: "(prefers-color-scheme: light)", color: "white" },
    { media: "(prefers-color-scheme: dark)", color: "black" },
  ],
};

export default function RootLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <html suppressHydrationWarning className={fontSans.className} lang="en">
    <body
      className={
        "bg-gradient-to-tl from-violet-600 from-0% via-black via-50% to-blue-500 to-100% bg-cover bg-center min-h-[100dvh]"
      }
    >
    <div className={"select-none m-0"}>
          <Providers>{children}</Providers>
    </div>
      </body>
    </html>
  );
}
