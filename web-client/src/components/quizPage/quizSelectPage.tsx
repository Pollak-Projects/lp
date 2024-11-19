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


  if (query.isError) return query.error.message

  if(query.isLoading) return <Loading color={"default"}/>

  const renderQuiz = query.data.map((quiz: any) => {
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