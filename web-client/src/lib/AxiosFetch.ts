import axios from "axios";

import { auth } from "@/src/auth";

export default async function axiosFetch() {
  const instance = axios.create({
    baseURL: process.env.NEXT_PUBLIC_BACKEND_URL,
    headers: {
      "Content-Type": "application/json"
    }
  });

  const session = await auth();

  if (!session) return;

  const requestInterceptor = instance.interceptors.request.use(
    (config) => {
      config.headers.Authorization = `Bearer ${session.token?.access_token}`;

      return config;
    },
    (error) => {
      console.log("Request error", error);

      return Promise.reject(error);
    }
  );

  const responseInterceptor = instance.interceptors.response.use(
    (config) => {
      config.data = config.data.json();
      return config;
    },
    (error) => {
      console.log("Response error", error);

      return Promise.reject(error);
    }
  );

  return instance;
}