"use client"
import React, { useState } from "react";

import {QuizData} from "@/src/types/question/quizData";

export default function Home() {

  //TODO: ONLY FOR TEST PURPOSES!! REMOVE ARRAY WHEN DONE WITH API REQUEST!! !IMPORTANT
  const QuizDataRequest: QuizData | Array<string>  = ["", ""]

const [quiz, SetQuiz] = useState(QuizDataRequest)

  function RenderQuizzes(){
      quiz.forEach((quizzes: any) => {
        return (
          <>
            <div className={"w-full h-fit"}>
              {/*not working*/}
              {quizzes.description}
            </div>
          </>
        )
      })
  }

  return (
    <>
      <section className={"flex flex-row justify-center bg-opacity-15"}>
        <span>Please select a Quiz you wish to fill in</span>
        <div className={"flex flex-row justify-center"}>

        </div>
      </section>

    </>
  )
}
