"use client";
import NextLink from "next/link";
import InputText from "@/src/components/quizComponents/inputText";
import InputRadio from "@/src/components/quizComponents/inputRadio";
import { QuestionRadio} from "@/src/types/questionRadio";
import { useState } from "react";
import InputCheck from "@/src/components/quizComponents/inputCheck";
import { QuestionCheck } from "@/src/types/questionCheck";
import InputOrder from "@/src/components/quizComponents/inputOrder";
import React from "react";
import { QuestionOrder } from "@/src/types/questionOrder";
import {v4 as uuidv4 } from "uuid";


export default function Home() {


  const RadioDummyData: QuestionRadio = {
    "title": "This is again such a long ass dummy question, that it needs to be displayed in two completely different set of lines",
    "value": [
      "why",
      "just",
      "cause",
      "bad",
      "game",
      "I jest"
    ],
    "comment": "I would like to say something"
  };
  const CheckDummyData: QuestionCheck = {
    "title": "This is again and again such a long ass dummy question",
    "value": [
      "some text",
      "why not again some text",
      "just how many do we need?",
      "nvm, I'll just put there two more",
      "so this is the first one",
      "and this is the second one, enjoy"
    ],
    "comment": "4 answers may be correct"
  };
  const OrderDummyData: QuestionOrder = {
    "title": "some title",
    "value": [
      {id: uuidv4(), content: "This should be the first"},
      {id: uuidv4(), content: "Or should this be the first one"},
      {id: uuidv4(), content: "Maybe this is the one "},
      {id: uuidv4(), content: "This is such a long ass statement that this will surely be the right one you're looking for, no doubt"},
      {id: uuidv4(), content: "Short one but a strong one "},
      {id: uuidv4(), content: "This is the one"}
    ],
    "comment": "Order them descending from top to bottom"
  }

  const [isOrder, setOrder] = useState(OrderDummyData)
  const [isCheck, setCheck] = useState(null);
  const [isRadio, setRadio] = useState(null);

  return (
    <>


      <section className={"flex flex-row h-fit gap-4 items-center justify-center mx-2 opacity-30 mt-2"}>

        <section
          className={"w-1/5 bg-content1 h-[98.4vh] p-1 rounded-md"}>

        </section>
        <section
          className={"w-3/5 bg-content1 h-[98.4vh] p-1 rounded-md overflow-scroll"}>
          <div className="w-full flex h-fit flex-col items-center justify-center mb-2 px-4 gap-4 ">
            <NextLink
              href="/"
              //style={{color: "#006FEE"}}
            >Go back</NextLink>
            {/*<h1>{Radio}</h1>*/}
            <InputText />

            <InputRadio question={RadioDummyData} setRadio={setRadio} />

            <InputCheck question={CheckDummyData} setCheck={setCheck} />

            <InputOrder {...OrderDummyData} />

          </div>
        </section>
        <section
          className={"w-1/5 bg-content1 p-1 h-[98.4vh] rounded-md"}>

        </section>
      </section>
    </>
  )


}
