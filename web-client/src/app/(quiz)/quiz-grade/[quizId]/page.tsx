import React from "react";
import { NavbarCustom } from "@/src/components/NavbarCustom";
import QuizGradePage from "@/src/components/QuizGradeComponents/QuizGradePage";

export default async function QuizGrade({ params }: { params: Promise<{ quizId: string }> }) {
  const quizId = (await params).quizId;

  return (
    <>
      <NavbarCustom />
      <main className={"inline-flex h-full w-full"}>
        <QuizGradePage />
      </main>
    </>
  );
}