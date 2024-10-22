import "@/src/styles/globals.css";
import { Viewport } from "next";
import { fontSans } from "@/src/config/fonts";

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
      <GlobalStyles />
      <head>
        <link rel={"stylesheet"} href={"@/src/styles/globals.css"} />
      </head>
      <body className={"bg-gradient-to-tl from-violet-600 from-0% via-black via-50% to-blue-500 to-100% bg-cover bg-center min-h-[100dvh]"}>
        <main className={"select-none m-0"}>
          <Providers>{children}</Providers>
        </main>
      </body>
    </html>
  );
}
