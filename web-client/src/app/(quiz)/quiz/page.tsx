import { Button } from "@nextui-org/button";
import NextLink from "next/link";

export default function Home() {
  return (
    <>
        <div className="w-full bg-amber-300 text-center">
            yes center
          <NextLink href="/">Go back</NextLink>
        </div>
    </>
  );
}
