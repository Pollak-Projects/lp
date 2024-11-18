"use client";

import React, { ChangeEvent } from "react";
import { Input } from "@nextui-org/input";

import { QuestionFile } from "@/src/types/question/questionFile";

export default function QuestionFileCreateList({
  questionFiles,
  onUpdateAction,
}: {
  questionFiles: Array<QuestionFile>;
  onUpdateAction: (questionFiles: QuestionFile[]) => void;
}) {
  const handleQuestionFileTitleChange =
    (index: number) => (e: ChangeEvent<HTMLInputElement>) => {
      const updatedQuestionFiles = [...questionFiles];

      updatedQuestionFiles[index].title = e.target.value;
      onUpdateAction(updatedQuestionFiles);
    };

  const handleQuestionFileIdChange =
    (index: number) => (e: ChangeEvent<HTMLInputElement>) => {
      const updatedQuestionFiles = [...questionFiles];

      updatedQuestionFiles[index].fileId = e.target.value;
      onUpdateAction(updatedQuestionFiles);
    };

  return (
    <>
      {questionFiles.length !== 0 && <h1>Text Questions</h1>}
      <div className={"flex flex-col gap-y-[5dvh]"}>
        {questionFiles?.map((question, index) => {
          return (
            <div key={index}>
              <Input
                label="Question title"
                placeholder={question.title}
                type="text"
                onChange={handleQuestionFileTitleChange(index)}
              />
              <Input
                label="File id"
                placeholder={question.fileId}
                type="text"
                onChange={handleQuestionFileIdChange(index)}
              />
            </div>
          );
        })}
      </div>
    </>
  );
}
