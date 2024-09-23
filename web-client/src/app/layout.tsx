import "@/src/styles/globals.css";
import { Viewport } from "next";

import { Providers } from "./providers";

import { Navbar } from "@/src/components/navbar";

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
    <html suppressHydrationWarning lang="en" >
      <head />
      <body className="h-96 pt-2 ">



        <Providers themeProps={{ attribute: "class", defaultTheme: "dark" }}>
          <Navbar />

          <main className=" flex flex-col items-center justify-center w-400 flex-grow">
            {children}
          </main>
        </Providers>

      </body>
    </html>
  );
}
