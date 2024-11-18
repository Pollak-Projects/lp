"use client";
import React, { useState } from "react";
import { QuizData } from "@/src/types/question/quizData";
import NextLink from "next/link";
import { useGetAllQuizzes, useGetAllQuizzesById } from "@/src/data/crudQuiz";
import { CircularProgress } from "@nextui-org/progress";


export default function QuizSelectPage( userId:any) {

  const userQueryAll = useGetAllQuizzesById(userId);

  const query = useGetAllQuizzes()


  const renderQuiz = query.data.map((quiz: any) => {
    return (
      <>
      <button>
        <NextLink href={"quiz/" + quiz.id}>

          <div className={"flex flex-col bg-content2 w-[30dvw] rounded-lg h-[10dvh]"}>
            <span className={"text-2xl place-self-center"}>{quiz.name}</span>
            <span className={""}>{quiz.createdAt}</span>
            <span className={""}>{quiz.deadline}</span>
            <span className={"text-justify"}>{quiz.description}</span>
          </div>
        </NextLink>
      </button>
      </>
    )
  })




  return (
    <>
      {query.isLoading ? (
        <>
          <div className="pt-2 w-[40dvh] mr-auto text-center text-xl">
            <CircularProgress color={"danger"} label={"Loading..."} />
          </div>
        </>
      ) : query.isError ? (
        <p>Error: {query.error.message}</p>
      ) : (
      <section className={"w-fit bg-opacity-15"}>
        <span>Please select a Quiz you wish to fill in</span>
        <div className={"flex flex-row flex-wrap pl-5 justify-start"}>
          {renderQuiz}
        </div>

      </section>
)}

    </>
  )
}