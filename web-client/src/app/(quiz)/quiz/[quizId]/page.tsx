import React from "react";

import QuizPage from "@/src/components/quizPage/quizPage";
import { QuizData } from "@/src/types/question/quizData";
import { auth } from "@/src/auth";


export default async function Quiz() {




  return (
    <>
      <QuizPage quizData={session?.user?.id} />

    </>
  );
}
