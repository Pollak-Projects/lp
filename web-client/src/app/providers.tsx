"use client";

import * as React from "react";
import { NextUIProvider } from "@nextui-org/system";
import { useRouter } from "next/navigation";
import { ThemeProvider as NextThemesProvider } from "next-themes";
import { ThemeProviderProps } from "next-themes/dist/types";
import { SessionProvider } from "next-auth/react";
import { Session } from "next-auth";

export interface ProvidersProps {
  children: React.ReactNode,
  themeProps?: ThemeProviderProps,
  Session?: Session
}

export function Providers({ children, themeProps, Session }: ProvidersProps) {
  const router = useRouter();

  return (
    <SessionProvider session={Session}>
      <NextUIProvider navigate={router.push}>
        <NextThemesProvider {...themeProps}>{children}</NextThemesProvider>
      </NextUIProvider>
    </SessionProvider>
  );
}
