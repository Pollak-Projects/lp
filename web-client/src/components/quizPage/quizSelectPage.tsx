"use client";
import React, { useState } from "react";
import { QuizData } from "@/src/types/question/quizData";
import NextLink from "next/link";
import { useGetAllQuizzes, useGetAllQuizzesById } from "@/src/data/crudQuiz";
import { CircularProgress } from "@nextui-org/progress";
import Loading from "@/src/app/(quiz)/loading";


export default function QuizSelectPage( userId:any) {

  const userQueryById = useGetAllQuizzesById(userId);

  const query = useGetAllQuizzes()

let renderQuizProbe

  if (query.isError) {
    return <div className={"flex flex-row justify-center min-h-[100vh] relative"}>
      <span className={"min-h-[100vh]"}>{query.error.message}</span>
    </div>
  }
  else if (query.isLoading) {
    return <div className={"flex flex-row justify-center min-h-[100vh] relative"}>
      <Loading color={"default"} />
    </div>
  }
  else if(query.data){
    renderQuizProbe = query.data.map((quiz: any) => {
      return (
        <button key={quiz.id}>
          <NextLink href={"quiz/" + quiz.id}>

            <div className={"flex flex-col bg-content2 w-[30dvw] rounded-lg " +
              "min-h-[12dvh]"}
            >
              <span className={"text-2xl place-self-center"}>{quiz.name}</span>
              {/* TODO: Implement name getting with keycloak
              <span className={""}>Assigned by:{quiz.createdBy}</span>*/}
              <span className={""}>{quiz.createdAt}</span>
              <span className={""}>{quiz.deadline}</span>
              <span className={"text-justify"}>{quiz.description}</span>
            </div>
          </NextLink>
        </button>
      )
  })
  }
  else{
    return <div className={"flex flex-row justify-center min-h-[100vh] relative"}>
      <Loading color={"default"} />
    </div>
  }
  //did the checking like this, because if you use a constant it will have a value, but if you were to map that value when the API gives bck nothing, yuo would get an exception, with this, the let won't have a value = no error
  const renderQuiz = renderQuizProbe



  return (
    <>
      <section className={"flex flex-col justify-center items-center w-full bg-opacity-15"}>
        <span className={"justify-self-center"}>Please select a Quiz you wish to fill out</span>
        <div className={"grid grid-cols-3 gap-5"}>
          {renderQuiz}
        </div>

      </section>

    </>
  )
}