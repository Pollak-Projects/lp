import React from "react";
import { NavbarCustom } from "@/src/components/NavbarCustom";
import QuizGradePage from "@/src/components/QuizGradeComponents/QuizGradePage";
import { QuizAnswerData } from "@/src/types/questionAnswer/quizAnswerData";
import { getQuizAnswerById } from "@/src/app/actions/QuizAnswerActions";

export default async function QuizGrade({ params }: { params: Promise<{ quizAnswerId: string }> }) {
  const quizAnswerId = (await params).quizAnswerId;

  const quizAnswer: QuizAnswerData = await getQuizAnswerById(quizAnswerId);

  return (
    <>
      <NavbarCustom />
      <main className={"inline-flex h-full w-full"}>
        <QuizGradePage quizAnswer={quizAnswer} />
      </main>
    </>
  );
}