import React from "react";

import { NavbarCustom } from "@/src/components/NavbarCustom";
import QuizAnswerSelectPage from "@/src/components/QuizAnswerSelectComponents/QuizAnswerSelectPage";
import { getAllQuizAnswersByUserId } from "@/src/app/actions/QuizAnswerActions";
import { QuizAnswerData } from "@/src/types/questionAnswer/quizAnswerData";

export default async function QuizAnswerSelect() {
  const quizAnswers: QuizAnswerData[] = await getAllQuizAnswersByUserId();

  return (
    <div>
      <NavbarCustom />
      {quizAnswers[0] === undefined ? (
        <p>No quiz answers found</p>
      ) : (
        <main className={"inline-flex h-full w-full"}>
          <QuizAnswerSelectPage quizAnswers={quizAnswers} />
        </main>
      )}
    </div>
  );
}
