"use client"

import { Textarea } from "@nextui-org/input";
import { ChangeEvent, FC, useState } from "react";
import { QuestionTextAnswer } from "@/src/types/questionAnswer/questionTextAnswer";
import { QuestionText } from "@/src/types/question/questionText";
import { QuizAnswerData } from "@/src/types/questionAnswer/quizAnswerData";


interface Props {
  onAnswerChangeAction: (questionTextsAnswers: QuestionTextAnswer[]) => void;
  questionText: QuestionText;
  QuizAnswerData: QuestionTextAnswer;
  index: number;
}

export default function InputText ( { onAnswerChangeAction ,questionText, QuizAnswerData,index}: Props ) {



  const handleChange = (event: ChangeEvent<HTMLInputElement>) => {
    const questionTextAnswers = QuizAnswerData
    questionTextAnswers[index].questionTextId = questionText.id;
    questionTextAnswers[index].answer = event.target.value;
    onAnswerChangeAction(questionTextAnswers)
  }

  return (
    <section
      className={"bg-content2 rounded-xl w-full pt-5 mb-2 mt-1 mx-12 flex text-center flex-nowrap flex-col align-middle place-items-center"}
    >
      <span className={"text-2xl"}>{questionText.title}</span>
      <Textarea
        isRequired={true}
        style={{ background: "none" }}
        variant="bordered"
        onChange={handleChange}
        placeholder="Enter the answer"
        className={"max-w-ms mt-2 max-h-60 overflow-scroll text-justify"}
      />
    </section>
  );
}
