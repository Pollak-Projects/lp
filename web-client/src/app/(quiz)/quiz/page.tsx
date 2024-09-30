"use client";
import NextLink from "next/link";
import InputText from "@/src/components/quizComponents/inputText";
import InputRadio from "@/src/components/quizComponents/inputRadio";
import { QuestionRadio} from "@/src/types/questionRadio";
import { useState } from "react";
import InputCheck from "@/src/components/quizComponents/inputCheck";
import { QuestionCheck } from "@/src/types/questionCheck";
import InputOrder from "@/src/components/quizComponents/inputOrder";
import { QuestionOrder } from "@/src/types/questionOrder";


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
  }
  const CheckDummyData: QuestionCheck = {
    "title": "This is again and again such a long ass dummy question, that it needs to be displayed in two completely different set of lines ",
    "value": [
      "some text",
      "why not again some text",
      "just how many do we need?",
      "nvm, I'll just put there two more",
      "so this is the first one",
      "and this is the second one, enjoy"
    ],
    "comment": "4 answers may be correct"
  }
  const OrderDummyData: QuestionOrder = {
    "title": "Just how many more long ass dummy questions do you need to believe that this element is responsive? For real mate, this is the fourth one!!!",
    "value": [
      "This should be the first one",
      "Or this should be the first one",
      "Or maybe this should be the first one?",
      "Who knows, maybe this is the one?",
      "Nah, trust me bro, this is far the longest option, surely this is the first one of them all, no?",
      "Just a short one"
    ],
    "comment": "Order them from top to bottom descending"
  }

  const [Order, setOrder] = useState(null);
  const [Check, setCheck] = useState(null);
  const [Radio, setRadio] = useState(null);
  return (
    <>


      <section className={"flex flex-row h-fit gap-4 items-center justify-center mx-2 opacity-30 mt-2"}>

        <section
          className={"w-1/5 bg-content1 h-[98.4vh] p-1 rounded-md"}>

        </section>
        <section
          className={"w-3/5 bg-content1 h-fit p-1 rounded-md"}>
          <div className="w-full flex h-fit flex-col items-center justify-center mb-2 ">
            <NextLink
              href="/"
              //style={{color: "#006FEE"}}
            >Go back</NextLink>
          </div>
          <InputText />
          {/*<h1>{Radio}</h1>*/}
          <InputRadio question={RadioDummyData} setRadio={setRadio} />

          <InputCheck question={CheckDummyData} setCheck={setCheck} />

          <InputOrder question={Order} setOrder={setOrder} />
        </section>
        <section
          className={"w-1/5 bg-content1 p-1 h-[98.4vh] rounded-md"}>

        </section>
      </section>
    </>
  );
}
