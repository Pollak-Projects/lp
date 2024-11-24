"use client";

import { QuizAnswerData } from "@/src/types/questionAnswer/quizAnswerData";

// TODO do this with all components
export default function QuizGradePage({quizAnswer}:{quizAnswer: QuizAnswerData}) {
  return (
    <>
      <h1>Quiz Grade Page</h1>
      <p>Quiz Answer: {JSON.stringify(quizAnswer)}</p>
    </>
  );
};