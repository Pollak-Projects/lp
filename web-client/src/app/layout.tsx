import "@/src/styles/globals.css";
import { Viewport } from "next";
import {fontSans} from "@/src/config/fonts"

import { Providers } from "./providers";

import { Navbar } from "@/src/components/navbar";
import GlobalStyles from "@/src/components/globalStyles";

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


    <html suppressHydrationWarning lang="en" className={fontSans.className}>
    <GlobalStyles/>
    <head>
    <link rel={"stylesheet"} href={"@/src/styles/globals.css"} />

    </head>
    <body>
    <main>
      <Providers>
        {children}
      </Providers>
    </main>
    </body>
    </html>


  );
}
