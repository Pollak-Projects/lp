"use client"

import { useAxios } from "@/src/lib/AxiosProvider";

export function TestDisplay() {
  const instance = useAxios({ url: "https://api.example.com" });
  return (
    <div>
      <h1>Test Display</h1>
      <p></p>
    </div>
  );
}