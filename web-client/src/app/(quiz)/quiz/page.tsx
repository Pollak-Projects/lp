"use client";
import NextLink from "next/link";
import React, { useState } from "react";

import QuizSidebar from "@/src/components/quizComponents/quizSidebar";

import { QuestionTextAnswers } from "@/src/types/questionAnswer/questionTextAnswers";
import { QuestionRadioAnswers } from "@/src/types/questionAnswer/questionRadioAnswers";
import { QuestionPairCollectionAnswers } from "@/src/types/questionAnswer/questionPairCollectionAnswers";
import { PairCollectionAnswers} from "@/src/types/questionAnswer/pairCollection/pairCollectionAnswers";
import { QuestionOrderAnswers } from "@/src/types/questionAnswer/questionOrderAnswers";
import { QuestionFileAnswers } from "@/src/types/questionAnswer/questionFileAnswers";
import { QuestionCheckboxAnswers } from "@/src/types/questionAnswer/questionCheckboxAnswers";
import { useSession } from "next-auth/react";
import { auth } from "@/src/auth";


export default function Quiz() {

  /*
  const QuizDummyData: QuizData = {
    title: "My ass is a dummy quiz",
    description: "this is some dumb ss dummy quiz, please do not pay any attention whatsoever to the typos in this page as there is a lot of them, if you find them all you will get candy",
    assigner: "Mr. Gary Coleman",
    deadline: "2001-09-11 8:35"
  }
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
      { title: "cause", answer: false }, // WHA!!??
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
    },
    comment: "Pair them. Even a monkey could do it"
  }

   */

  await auth()

  const [QuizFileAnswers, setQuizFileAnswers] = useState(Array<QuestionFileAnswers>);
  const [QuizTextAnswers, setQuizTextAnswers] = useState(Array<QuestionTextAnswers>);

  console.log(useSession())
  const [QuizRadioAnswers, setQuizRadioAnswers] = useState(Array<QuestionRadioAnswers>);
  const [QuizOrderAnswers, setQuizOrderAnswers] = useState(Array<QuestionOrderAnswers>);
  const [QuizCheckboxAnswers, setQuizCheckboxAnswers] = useState(Array<QuestionCheckboxAnswers>);

  const [QuizPairCollectionAnswers, setQuizPairCollectionAnswers] = useState(Array<QuestionPairCollectionAnswers>);
  const [PairCollectionAnswers, setPairCollectionAnswers] = useState(Array<PairCollectionAnswers>);




  return (
    <>
      <section
        className={
          "flex flex-row h-fit mx-1 gap-4 py-1 items-center justify-center "
        }
      >
        <section
          className={"w-1/5 flex flex-col gap-2 bg-content1-opacity15 h-[99vh] p-1 rounded-md"}
        >
          {/*
          <QuizTitle QuizDummyData={QuizDummyData} />

          <QuizSubmit QuizDummyData={QuizDummyData} />
          */}
        </section>
        <section
          className={
            "w-3/5 bg-content1-opacity15 h-[99vh] p-1 rounded-md overflow-scroll"
          }
        >
          <div className="w-full flex h-fit flex-col items-center justify-center mb-2 px-4 gap-4 ">
            <NextLink
              href="/public"
              //style={{color: "#006FEE"}}
            >
              Go back
            </NextLink>
            {/*

            <InputText question={TextDummyData}/>

            <InputRadio question={RadioDummyData} />

            <InputCheck question={CheckDummyData} />

            <InputOrder question={OrderDummyData} />

            <InputPair question={PairDummyData} />
            */}
          </div>
        </section>
        <section
          className={"w-1/5 bg-content1-opacity15 p-1 h-[99vh] rounded-md"}
        >
          <QuizSidebar />
        </section>

      </section>
    </>
  );
}
