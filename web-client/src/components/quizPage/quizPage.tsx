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
import { QuestionText } from "@/src/types/question/questionText";

export default function QuizPage({
  quizId,
}: {
  quizId: string }){

  const initialQuizAnswerData: QuizAnswerData = {
    id: "",
    createdBy: "",
    questionCheckboxAnswers: [],
    questionFileAnswers: [],
    questionOrderAnswers: [],
    questionPairCollectionAnswers: [],
    questionRadioAnswers: [],
    questionTextAnswers: [],
  };

  const initialQuizData: QuizData = {
    createdAt: "",
    createdBy: "",
    deadline: "",
    description: "",
    id: "",
    name: "",
    questionCheckboxes: [],
    questionFiles: [],
    questionOrders: [],
    questionPairCollection: [],
    questionRadios: [],
    questionTexts: [],
    quizAnswers: [],
    viewAfterSubmission: false
  }

  const [answerData, setAnswerData] = useState<QuizAnswerData>(initialQuizAnswerData);

  const [loading, setLoading] = useState(true);


  const [quizData, setQuizData] = useState<QuizData>(initialQuizData);

  const queryByQuizId = useGetQuizById(quizId)

  if (queryByQuizId.isError) return queryByQuizId.error.message
  if(queryByQuizId.isLoading) return <Loading color={"default"}/>

  setQuizData({ ...queryByQuizId.data })

  const shuffle = <T,>(array: Array<T>) => {
    for (let i = array.length - 1; i > 0; i--) {
      const j = Math.floor(Math.random() * (i + 1));
      [array[i], array[j]] = [array[j], array[i]];
    }
    return array;
  };

  const shuffledQuestionText = shuffle(quizData.questionTexts)
  setQuizData({...quizData, questionTexts: shuffledQuestionText})

  const shuffledQuestionRadios = shuffle(quizData.questionRadios)
  setQuizData({...quizData, questionRadios: shuffledQuestionRadios})

  const shuffledQuestionCheckboxes = shuffle(quizData.questionCheckboxes)
  setQuizData({...quizData, questionCheckboxes: shuffledQuestionCheckboxes})

  const shuffledQuestionOrders = shuffle(quizData.questionOrders)
  setQuizData({...quizData, questionOrders: shuffledQuestionOrders})

  const shuffledQuestionPairCollection = shuffle(quizData.questionPairCollection)
  setQuizData({...quizData, questionPairCollection: shuffledQuestionPairCollection})

  const shuffledQuestionFiles = shuffle(quizData.questionFiles)
  setQuizData({...quizData, questionFiles: shuffledQuestionFiles})




  const handleQuestionTextAnswerChange = (questionTextAnswers: QuestionTextAnswer[]) => {
    setAnswerData((prevState)=>({
      ...prevState,
        questionTextAnswers
    }))
  }




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

            {
                shuffledQuestionText.map((questionText: QuestionText, index: number )=>{
                return <InputText onAnswerChangeAction={handleQuestionTextAnswerChange}
                                  quizTextAnswerData={answerData.questionTextAnswers}
                                  questionText={questionText}
                                  index={index}
                                  />
              })
            }

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