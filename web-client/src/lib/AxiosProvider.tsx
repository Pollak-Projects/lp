'use client'

import { AxiosInstance } from "axios";
import React from "react";
import { auth } from "@/src/auth";
import { useSession } from "next-auth/react";
import { Session } from "next-auth";

export const AxiosContext = React.createContext<AxiosInstance | undefined>(
  undefined,
)

export type AxiosExchangeProps = {
  url: string

}

export const useAxios = ({
    url
  }: AxiosExchangeProps ): AxiosInstance | JSON => {
  const instance = React.useContext(AxiosContext)

  console.log(url);
  console.log(instance);
  if (instance) {
    return instance
  }

  if (!instance) {
    throw new Error('No AxiosInstance set, use AxiosProvider to set one')
  }

  return instance
}

export type AxiosProviderProps = {
  instance: AxiosInstance
  children?: React.ReactNode
}

export const AxiosProvider = ({
  instance,
  children,
}: AxiosProviderProps): React.JSX.Element => {
  React.useEffect(() => {
    const requestInterceptor = instance.interceptors.request.use(async (config) => {
      // This is fine, i love next-auth
      const session = useSession() as unknown as Session;
      config.headers.common['Authorization'] = `Bearer ${session?.token?.accessToken}`;
      return config;
    }, (error) => {
      console.log("Request error", error);
      return Promise.reject(error);
    });

    const responseInterceptor =  instance.interceptors.response.use(async (config) => {
      // This too
      const session = useSession() as unknown as Session;
      config.headers.common['Authorization'] = `Bearer ${session?.token?.accessToken}`;
      return config;
    }, (error) => {
      console.log("Response error", error);
      return Promise.reject(error);
    });
    return () => {
      instance.interceptors.request.eject(requestInterceptor)
      instance.interceptors.response.eject(responseInterceptor)
    }
  }, [instance])
  return (
    <AxiosContext.Provider value={instance}>
      {children}
    </AxiosContext.Provider>
  )
}