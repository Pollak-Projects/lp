"use client";

import * as React from "react";
import { useState } from "react";
import { NextUIProvider } from "@nextui-org/system";
import { useRouter } from "next/navigation";
import { ThemeProvider as NextThemesProvider, ThemeProviderProps } from "next-themes";
import { SessionProvider } from "next-auth/react";
import { Session } from "next-auth";
import axios from "axios";
import { QueryClient, QueryClientProvider } from "@tanstack/react-query";
import { ReactQueryDevtools } from "@tanstack/react-query-devtools";

export interface ProvidersProps {
  children: React.ReactNode;
  themeProps?: ThemeProviderProps;
  Session?: Session;
}

export function Providers({ children, themeProps, Session }: ProvidersProps) {
  const router = useRouter();

  const [queryClient] = useState(
    () =>
      new QueryClient({
        defaultOptions: {
          queries: {
            staleTime: 1000 * 5,
            refetchInterval: 1000 * 5
          }
        }
      })
  );

  const instance = axios.create({
    baseURL: process.env.NEXT_PUBLIC_BACKEND_URL,
    headers: {
      "Content-Type": "application/json",
    },
  });

  return (
    <SessionProvider session={Session}>
      <NextUIProvider navigate={router.push}>
        <QueryClientProvider client={queryClient}>
          <ReactQueryDevtools />
          <NextThemesProvider {...themeProps}>{children}</NextThemesProvider>
        </QueryClientProvider>
      </NextUIProvider>
    </SessionProvider>
  );
}
