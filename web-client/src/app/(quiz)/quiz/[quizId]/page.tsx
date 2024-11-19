import React from "react";

import QuizPage from "@/src/components/quizPage/quizPage";
import { QuizData } from "@/src/types/question/quizData";
import { auth } from "@/src/auth";


export default async function Quiz({
  params,
}:{
  params: Promise<{quizId: string}>}) {


  const session = await auth()

  const quizId = (await params).quizId


  return (
    <>
      <QuizPage quizId={quizId} />

    </>
  );
}
