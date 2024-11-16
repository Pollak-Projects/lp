"use client";
import { ChangeEvent, useState } from "react";
import { Input } from "@nextui-org/input";
import { Button } from "@nextui-org/button";
import { PressEvent } from "@react-types/shared";

import { QuestionCheckboxesDTO } from "@/src/types/question/questionCheckboxes";

export default function QuestionCheckboxCreateList({
  questionCheckboxes,
  onUpdateAction,
}: {
  questionCheckboxes: Array<QuestionCheckboxesDTO>;
  onUpdateAction: (questionCheckboxes: QuestionCheckboxesDTO[]) => void;
}) {
  const handleQuestionCheckboxTitleChange =
    (index: number) => (e: ChangeEvent<HTMLInputElement>) => {
      const updatedQuestionCheckboxes = [...questionCheckboxes];

      updatedQuestionCheckboxes[index].title = e.target.value;
      onUpdateAction(updatedQuestionCheckboxes);
    };

  const handleQuestionCheckboxOptionNameChange =
    (index: number, optionIndex: number) =>
    (e: ChangeEvent<HTMLInputElement>) => {
      const updatedQuestionCheckboxes = [...questionCheckboxes];

      updatedQuestionCheckboxes[index].options[optionIndex].name =
        e.target.value;
      onUpdateAction(updatedQuestionCheckboxes);
    };

  const handleQuestionCheckboxOptionAnswerChange =
    (index: number, optionIndex: number) =>
    (e: ChangeEvent<HTMLInputElement>) => {
      const updatedQuestionCheckboxes = [...questionCheckboxes];

      updatedQuestionCheckboxes[index].options[optionIndex].answer =
        e.target.checked;

      onUpdateAction(updatedQuestionCheckboxes);
    };

  let initialNewOption = {
    name: "",
    answer: false,
  };

  const [newOption, setNewOption] = useState(initialNewOption);

  const handleQuestionCheckboxNewOptionNameChange = (
    e: ChangeEvent<HTMLInputElement>
  ) => {
    setNewOption({ ...newOption, name: e.target.value });
  };

  const handleQuestionCheckboxNewOptionAnswerChange = (
    e: ChangeEvent<HTMLInputElement>
  ) => {
    setNewOption({ ...newOption, answer: e.target.checked });
  };

  const handleNewOptionCreation = (index: number) => (e: PressEvent) => {
    const updatedQuestionCheckboxes = [...questionCheckboxes];

    updatedQuestionCheckboxes[index].options.push({
      id: "",
      questionCheckbox: "",
      name: newOption.name,
      answer: newOption.answer,
      createdBy: "",
    });

    onUpdateAction(updatedQuestionCheckboxes);
    setNewOption(initialNewOption);
  };

  return (
    <>
      {questionCheckboxes.length !== 0 && <h1>Checkbox Questions</h1>}
      <div className={"flex flex-col gap-y-[5dvh]"}>
        {questionCheckboxes?.map((question, index) => {
          return (
            <div key={index}>
              <div>
                <Input
                  label="Question title"
                  placeholder={question.title}
                  type="text"
                  onChange={handleQuestionCheckboxTitleChange(index)}
                />
              </div>
              <div className={"flex flex-col"}>
                {question.options.map((option, optionIndex) => {
                  return (
                    <div key={optionIndex} className={"flex "}>
                      <Input
                        label="Option"
                        placeholder={option.name}
                        type="text"
                        onChange={handleQuestionCheckboxOptionNameChange(
                          index,
                          optionIndex
                        )}
                      />
                      <Input
                        checked={option.answer}
                        label="Answer"
                        type="checkbox"
                        onChange={handleQuestionCheckboxOptionAnswerChange(
                          index,
                          optionIndex
                        )}
                      />
                    </div>
                  );
                })}
              </div>
              <div>
                <Input
                  label="New Option"
                  placeholder={"New Option"}
                  type="text"
                  onChange={handleQuestionCheckboxNewOptionNameChange}
                />
                <Input
                  label="Answer"
                  placeholder={"New option answer"}
                  type="checkbox"
                  onChange={handleQuestionCheckboxNewOptionAnswerChange}
                />
                <Button onPress={handleNewOptionCreation(index)}>
                  Add new option
                </Button>
              </div>
            </div>
          );
        })}
      </div>
    </>
  );
}
