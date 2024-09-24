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
  // url: string

}

export const useAxios = (): AxiosInstance => {
  const instance = React.useContext(AxiosContext)

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
  const session = useSession();
  React.useEffect(() => {
    const requestInterceptor = instance.interceptors.request.use((config) => {
      // TODO fix this isnt giving the correct header
      config.headers.Authorization = (`Bearer ${session?.data?.token?.access_token}`);
      return config;
    }, (error) => {
      console.log("Request error", error);
      return Promise.reject(error);
    });

    const responseInterceptor =  instance.interceptors.response.use((config) => {
      // config.headers.setAuthorization(`Bearer ${session?.data?.token?.access_token}`);
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