"use client";

import { Input } from "@nextui-org/input";
import React, { ChangeEvent } from "react";
import { QuestionTextsDTO } from "@/src/types/question/questionTexts";

export default function QuestionTextCreateList({
  questionTexts,
  onUpdateAction,
}: {
  questionTexts: Array<QuestionTextsDTO>;
  onUpdateAction: (questionTexts: QuestionTextsDTO[]) => void;
}) {
  const handleQuestionTextTitleChange =
    (index: number) => (e: ChangeEvent<HTMLInputElement>) => {
      const updatedQuestionTexts = [...questionTexts];

      updatedQuestionTexts[index].title = e.target.value;
      onUpdateAction(updatedQuestionTexts);
      console.log(index);
    };

  const handleQuestionTextAnswerChange =
    (index: number) => (e: ChangeEvent<HTMLInputElement>) => {
      const updatedQuestionTexts = [...questionTexts];

      updatedQuestionTexts[index].answer = e.target.value;
      onUpdateAction(updatedQuestionTexts);
      console.log(index);
    };

  return (
    <>
      {questionTexts.length !== 0 && <h1>Text Questions</h1>}
      <div className={"flex flex-col gap-y-[5dvh]"}>
        {questionTexts?.map((question, index) => {
          return (
            <div key={index}>
              <Input
                label="Question title"
                placeholder={question.title}
                type="text"
                onChange={handleQuestionTextTitleChange(index)}
              />
              <Input
                label="Question answer"
                placeholder={question.answer}
                type="text"
                onChange={handleQuestionTextAnswerChange(index)}
              />
            </div>
          );
        })}
      </div>
    </>
  );
}
