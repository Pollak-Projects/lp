"use client"
import NextLink from "next/link";
import QuizSidebar from "@/src/components/quizComponents/quizSidebar";
import React, { JSX, useState } from "react";
import { QuestionFileAnswer } from "@/src/types/questionAnswer/questionFileAnswer";
import { QuestionTextAnswer } from "@/src/types/questionAnswer/questionTextAnswer";
import { useSession } from "next-auth/react";
import { QuestionRadioAnswer } from "@/src/types/questionAnswer/questionRadioAnswer";
import { QuestionOrderAnswer } from "@/src/types/questionAnswer/questionOrderAnswer";
import { QuestionCheckboxAnswer } from "@/src/types/questionAnswer/questionCheckboxAnswer";
import { QuestionPairCollectionAnswer } from "@/src/types/questionAnswer/questionPairCollectionAnswer";
import {
  QuestionPairCollectionPairAnswer
} from "@/src/types/questionAnswer/pairCollection/questionPairCollectionPairAnswer";
import { QuizData } from "@/src/types/question/quizData";
import { Textarea } from "@nextui-org/input";
import { useGetQuizById } from "@/src/data/crudQuiz";
import Loading from "@/src/app/(quiz)/loading";
import InputText from "@/src/components/quizComponents/inputText";
import { QuizAnswerData } from "@/src/types/questionAnswer/quizAnswerData";
import QuizSubmit from "@/src/components/quizComponents/quizSubmit";
import QuizTitle from "@/src/components/quizComponents/quizTitle";
import { sendError } from "next/dist/server/api-utils";

export default function QuizPage({
  quizId,
}: {
  quizId: string }){

  const initialQuizData: QuizAnswerData = {
    id: "",
    questionCheckboxAnswers: [],
    questionFileAnswers: [],
    questionOrderAnswers: [],
    questionPairCollectionAnswers: [],
    questionRadioAnswers: [],
    questionTextAnswers: [],
  };

  const [answerData, setAnswerData] = useState<QuizAnswerData>(initialQuizData);

  const [toRenderComponentList, setToRenderComponentList] = useState(Array<JSX.Element>);
  const [loading, setLoading] = useState(true);



  const queryByQuizId = useGetQuizById(quizId)

  if (queryByQuizId.isError) return queryByQuizId.error.message

  if(queryByQuizId.isLoading) return <Loading color={"default"}/>


  const handleTextAnswerDataChange = (value: QuestionTextAnswer) => {
    //setAnswerData()
  }


  const getQuestionTexts: JSX.Element = queryByQuizId.data.questionTexts.map((questionText:any)=>{
    return <InputText questionText={questionText} sendAnswerData={handleTextAnswerDataChange} />
  })

  toRenderComponentList.push(getQuestionTexts)


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
  )
}