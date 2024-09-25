"use client"

import { useAxios } from "@/src/lib/AxiosProvider";
import { useSession } from "next-auth/react";
import { Session } from "next-auth";
import { auth } from "@/src/auth";
import { useQuery } from "@tanstack/react-query";
import { Button } from "@nextui-org/button";

export function TestDisplay() {
  const axios = useAxios();
  const session = useSession();
  const { isPending, error, data, isFetching } = useQuery({
    queryKey: ["test"],
    // queryFn: async () => {
      // const res = await fetch(`http://localhost:8181/api/v1/quiz/webclient`, {
      //   method: "GET",
      //   headers: {
      //     "Content-Type": "application/json",
      //     Authorization: `Bearer ${session?.data?.token?.access_token}`,
      //   },
      // });
    queryFn: async () => {
      const res = await axios.get("/api/v1/quiz/webclient");
      return res.data;
    },
  });
  if (error) return (<>'An error has occurred: ' + {error.message}</>);
  return (
    <div>
      <h1>Test Display</h1>
      { isPending ? <p>Loading...</p> :
        error ? <p>Error: </p> :
        data ? <p>{JSON.stringify(data)}</p> : <p>No data</p>}
    </div>
  );
}