import "@/src/styles/globals.css";
import { Viewport } from "next";
import {fontSans} from "@/src/config/fonts"

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


    <html suppressHydrationWarning lang="en" className={fontSans.className}>
    <head>
      <script
        dangerouslySetInnerHTML={{
          __html: `
                        (function() {
                            const storedColorMode = localStorage.getItem('colorMode');
                            const prefersDarkMode = window.matchMedia('(prefers-color-scheme: dark)').matches;
                            const colorMode = storedColorMode || (prefersDarkMode ? 'dark' : 'light');
                            document.documentElement.style.colorScheme = colorMode;
                        })();
                    `,
        }}
      />

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
