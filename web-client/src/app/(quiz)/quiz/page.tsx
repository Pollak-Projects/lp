import { Button } from "@nextui-org/button";
import NextLink from "next/link";
import InputText from "@/src/components/quizComponents/inputText";


export default function Home() {

  return (
    <>
        <div className="w-full flex flex-col items-center justify-center">
          <NextLink
            href="/"
            //style={{color: "#006FEE"}}
          >Go back</NextLink>
        </div>

      <section className={"flex flex-row gap-4 items-center justify-center mx-4 opacity-30 h-96"}>

        <section
          className={"w-1/5 bg-content1 h-full p-1"}>

        </section>
        <section
          className={"w-3/5 bg-content1 h-full p-1"}>
          <InputText/>
        </section>
        <section
          className={"w-1/5 bg-content1 h-full p-1"}>

        </section>
      </section>
    </>
  );
}
