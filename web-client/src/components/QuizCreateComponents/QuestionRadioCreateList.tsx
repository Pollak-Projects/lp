"use client";

import React, { ChangeEvent, useState } from "react";
import { Input } from "@nextui-org/input";
import { Button } from "@nextui-org/button";

import { QuestionRadiosDTO } from "@/src/types/question/questionRadios";

export default function QuestionRadioCreateList({
  questionRadios,
  onUpdateAction,
}: {
  questionRadios: Array<QuestionRadiosDTO>;
  onUpdateAction: (questionRadios: QuestionRadiosDTO[]) => void;
}) {
  const handleQuestionRadioTitleChange =
    (index: number) => (e: ChangeEvent<HTMLInputElement>) => {
      const updatedQuestionRadios = [...questionRadios];

      updatedQuestionRadios[index].title = e.target.value;
      onUpdateAction(updatedQuestionRadios);
    };

  const handleQuestionRadioOptionNameChange =
    (index: number, optionIndex: number) =>
    (e: ChangeEvent<HTMLInputElement>) => {
      const updatedQuestionRadios = [...questionRadios];

      updatedQuestionRadios[index].options[optionIndex].name = e.target.value;
      onUpdateAction(updatedQuestionRadios);
    };

  const handleQuestionRadioOptionAnswerChange =
    (index: number, optionIndex: number) =>
    (e: ChangeEvent<HTMLInputElement>) => {
      const updatedQuestionRadios = [...questionRadios];

      updatedQuestionRadios[index].options[
        previousCorrectAnswerOptionIndex
      ].answer = false;

      updatedQuestionRadios[index].options[optionIndex].answer =
        e.target.checked;
      onUpdateAction(updatedQuestionRadios);

      setPreviousCorrectAnswerOptionIndex(optionIndex);
    };

  const initialNewOption = { name: "", answer: false };

  const [newOption, setNewOption] = useState(initialNewOption);

  const [
    previousCorrectAnswerOptionIndex,
    setPreviousCorrectAnswerOptionIndex,
  ] = useState(0);

  const handleQuestionRadioNewOptionNameChange = (
    e: ChangeEvent<HTMLInputElement>
  ) => {
    setNewOption({ ...newOption, name: e.target.value });
  };

  const handleQuestionRadioNewOptionCreation = (index: number) => () => {
    const updatedQuestionRadios = [...questionRadios];

    updatedQuestionRadios[index].options.push({
      id: "",
      questionRadio: "",
      name: newOption.name,
      answer: newOption.answer,
      createdBy: "",
    });
    onUpdateAction(updatedQuestionRadios);
    setNewOption(initialNewOption);
  };

  return (
    <>
      {questionRadios.length !== 0 && <h1>Radio Questions</h1>}
      <div className={"flex flex-col gap-y-[5dvh]"}>
        {questionRadios?.map((question, index) => {
          return (
            <div key={index}>
              <Input
                placeholder={question.title}
                type="text"
                onChange={handleQuestionRadioTitleChange(index)}
              />
              {question.options.map((option, optionIndex) => {
                return (
                  <div key={optionIndex}>
                    <Input
                      placeholder={option.name}
                      type="text"
                      onChange={handleQuestionRadioOptionNameChange(
                        index,
                        optionIndex
                      )}
                    />
                    <Input
                      checked={option.answer}
                      type="radio"
                      onChange={handleQuestionRadioOptionAnswerChange(
                        index,
                        optionIndex
                      )}
                    />
                  </div>
                );
              })}
              <div>
                <Input
                  label={"New Option"}
                  placeholder={"New Option"}
                  type="text"
                  onChange={handleQuestionRadioNewOptionNameChange}
                />
                <Button onClick={handleQuestionRadioNewOptionCreation(index)}>
                  Create new option
                </Button>
              </div>
            </div>
          );
        })}
      </div>
    </>
  );
}
