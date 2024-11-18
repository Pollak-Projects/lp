import React from "react";
import { NavbarCustom } from "@/src/components/NavbarCustom";
import QuizGradePage from "@/src/components/QuizGradeComponents/QuizGradePage";

export default async function QuizGrade(props: { params: Promise<{ quizId: string }> }) {
  const params = await props.params;

  return (
    <>
      <NavbarCustom />
      <div className={"inline-flex h-full w-full"}>
        <QuizGradePage />
      </div>
    </>
  );
}