"use client"

import { Textarea } from "@nextui-org/input";
import { ChangeEvent, FC, useState } from "react";
import { QuestionTextAnswer } from "@/src/types/questionAnswer/questionTextAnswer";
import { QuestionText } from "@/src/types/question/questionText";

interface Props {
  onAnswerChangeAction: (questionTextsAnswers: QuestionTextAnswer[]) => void;
  questionText: QuestionText;
  quizTextAnswerData: QuestionTextAnswer[];
  index: number;
}

export default function InputText ( { onAnswerChangeAction ,questionText, quizTextAnswerData, index}: Props ) {

  const handleTextAnswerChange = (event: ChangeEvent<HTMLInputElement>) => {
    const updatedQuestionTextAnswer: QuestionTextAnswer[] = quizTextAnswerData
    updatedQuestionTextAnswer[index].questionTextId = questionText.id;
    updatedQuestionTextAnswer[index].answer = event.target.value;
    onAnswerChangeAction(updatedQuestionTextAnswer)
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
        onChange={handleTextAnswerChange}
        placeholder="Enter the answer"
        className={"max-w-ms mt-2 max-h-60 overflow-scroll text-justify"}
      />
    </section>
  );
}
