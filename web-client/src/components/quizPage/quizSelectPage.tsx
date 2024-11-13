"use client"
import { useState } from "react";
import { QuizDataDTO } from "@/src/types/question/quizData";


export default function QuizSelectPage({quizData, quizSelectResponse}:{quizData:Array<QuizDataDTO>, quizSelectResponse:Promise<unknown> }) {
  //TODO: ONLY FOR TEST PURPOSES!! REMOVE ARRAY WHEN DONE WITH API REQUEST!! !IMPORTANT
  const QuizDataRequest: QuizDataDTO | Array<string>  = ["", ""]

  console.log(quizSelectResponse)



  const [quiz, SetQuiz] = useState(quizData)

  const RenderQuizzes = quiz.map((quizzes: any) => {
    return (
      <>
        <div className={"w-full h-fit"}>
          <span className={"text-2xl place-self-center"}>{quizzes.name}</span>
          <span className={"text-sm italic"}>{quizzes.createdBy}</span>
          <span className={""}>{quizzes.createdAt}</span>
          <span className={""}>{quizzes.deadline}</span>
          <span className={"text-justify"}>{quizzes.description}</span>
        </div>
      </>
    )
  })

  return (
    <>
      <section className={"flex flex-col justify-center bg-opacity-15"}>
        <span>Please select a Quiz you wish to fill in</span>
        <div className={"flex flex-row justify-center"}>
          {RenderQuizzes}
        </div>
      </section>

    </>
  )
}