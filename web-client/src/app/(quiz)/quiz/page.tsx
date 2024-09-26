"use client";
import NextLink from "next/link";
import InputText from "@/src/components/quizComponents/inputText";
import InputRadio from "@/src/components/quizComponents/inputRadio";
import { RadioQuestion } from "@/src/types/questionRadio";
import { useState } from "react";


export default function Home() {
  const dummyData: RadioQuestion = {
        "title": "This is such a long ass dummy question, that it needs to be displayed in two completely different set of lines",
        "value": [
          "why",
          "just",
          "cause",
          "bad",
          "game",
          "I jest"
        ]
  }


  const [Radio, SetRadio] = useState(null);
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
          <h1>{Radio}</h1>
          <InputRadio question={dummyData}  SetRadio={SetRadio} />
        </section>
        <section
          className={"w-1/5 bg-content1 h-full p-1"}>

        </section>
      </section>
    </>
  );
}
