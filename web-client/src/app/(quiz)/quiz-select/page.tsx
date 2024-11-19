import React from "react";

import { QuizData } from "@/src/types/question/quizData";
import QuizSelectPage from "@/src/components/quizPage/quizSelectPage";
import { auth } from "@/src/auth";
import { JWT } from "next-auth/jwt";
import { useGetAllQuizzes } from "@/src/data/crudQuiz";
import { CircularProgress } from "@nextui-org/progress";


export default async function Home() {



  const session = (await auth())


  return (
    <>

        <>
          <QuizSelectPage userId={session?.user?.id} />

        </>


    </>
  )
}
