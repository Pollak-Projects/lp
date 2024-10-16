"use client";
import InputRadio from "@/src/components/quizComponents/inputRadio";
import InputCheck from "@/src/components/quizComponents/inputCheck";
import InputOrder from "@/src/components/quizComponents/inputOrder";
import InputText from "@/src/components/quizComponents/inputText";
import { QuestionRadio } from "@/src/types/questionRadio";
import { QuestionOrder } from "@/src/types/questionOrder";
import { QuestionCheck } from "@/src/types/questionCheck";
import { QuestionText } from "@/src/types/questionText";
import { v4 as uuidv4 } from "uuid";
import NextLink from "next/link";
import React from "react";
import { useAxios } from "@/src/lib/AxiosProvider";
import { useQuery } from "@tanstack/react-query";
import { QuestionPair } from "@/src/types/questionPair";
import InputPair from "@/src/components/quizComponents/inputPair";

const axios = useAxios();
const { isPending, error, data, isFetching } = useQuery({
  queryKey: ["test"],

  queryFn: async () => {
    const res = await axios.get("/api/v1/quiz/question/text");
    return res.data;
  },
});



export default function Home() {
  const TextDummyData: QuestionText = {
    value: {
      title:
        "This is such a long ass dummy question, that it needs to be displayed in two completely different set of lines",
      comment: "Please don't set your sister on fire",
    },
  };

  const RadioDummyData: QuestionRadio = {
    title:
      "This is again such a long ass dummy question, that it needs to be displayed in two completely different set of lines",
    value: [
      { title: "why", answer: false },
      { title: "just", answer: false },
      { title: "cause", answer: false },
      { title: "bad", answer: false },
      { title: "game", answer: false },
      { title: "I jest", answer: false },
    ],
    comment: "I would like to say something",
  };
  const CheckDummyData: QuestionCheck = {
    title: "This is again and again such a long ass dummy question",
    value: [
      { title: "some text", answer: false },
      { title: "why not again some text", answer: false },
      { title: "just how many do we need?", answer: false },
      { title: "nvm, I'll just put there two more", answer: false },
      { title: "so this is the first one", answer: false },
      { title: "and this is the second one, enjoy", answer: false },
    ],
    comment: "4 answers may be correct",
  };
  const OrderDummyData: QuestionOrder = {
    // database field: "place"  in QUESTION_ORDER_OPTIONS should be handled in the backend for security reasons
    title: "some title",
    value: [
      { id: uuidv4(), content: "This should be the first" },
      { id: uuidv4(), content: "Or should this be the first one" },
      { id: uuidv4(), content: "Maybe this is the one " },
      {
        id: uuidv4(),
        content:
          "This is such a long ass statement that this will surely be the right one you're looking for, no doubt",
      },
      { id: uuidv4(), content: "Short one but a strong one " },
      { id: uuidv4(), content: "This is the one" },
    ],
    comment: "Order them descending from top to bottom",
  };

  const PairDummyData: QuestionPair = {
    title: "Title of Pair component",
    value: {
      left: [
        {id: uuidv4(), content: "first pair"},
        {id: uuidv4(), content: "second pair but this is so long it requires multiple lines to display this fat text "},
        {id: uuidv4(), content: "third pair"},
        {id: uuidv4(), content: "fourth pair"},
        {id: uuidv4(), content: "fifth pair but this is so long it requires multiple lines to display this fat text"}
      ],
      right: [
        {id: uuidv4(), content: "first pair but this is so long it requires multiple lines to display this fat text"},
        {id: uuidv4(), content: "second pair"},
        {id: uuidv4(), content: "third pair but this is so long it requires multiple lines to display this fat text"},
        {id: uuidv4(), content: "fourth pair"},
        {id: uuidv4(), content: "fifth pair"}
      ]
    }
  }

  return (
    <>
      <section
        className={
          "flex flex-row h-fit gap-4 items-center justify-center mx-2 opacity-30 mt-2"
        }
      >
        <section
          className={"w-1/5 bg-content1 h-[98.4vh] p-1 rounded-md"}
        ></section>
        <section
          className={
            "w-3/5 bg-content1 h-[98.4vh] p-1 rounded-md overflow-scroll"
          }
        >
          <div className="w-full flex h-fit flex-col items-center justify-center mb-2 px-4 gap-4 ">
            <NextLink
              href="/"
              //style={{color: "#006FEE"}}
            >
              Go back
            </NextLink>
            {/*<h1>{Radio}</h1>*/}
            <InputText question={TextDummyData} />

            <InputRadio question={RadioDummyData} />

            <InputCheck question={CheckDummyData} />

            <InputOrder question={OrderDummyData} />

            <InputPair question={PairDummyData} />
          </div>
        </section>
        <section
          className={"w-1/5 bg-content1 p-1 h-[98.4vh] rounded-md"}
        ></section>
      </section>
    </>
  );
}
