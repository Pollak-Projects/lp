"use client";

import React, { ChangeEvent, useState } from "react";
import { Input } from "@nextui-org/input";
import { Button } from "@nextui-org/button";
import { DragDropContext, Draggable, Droppable, DropResult } from "@hello-pangea/dnd";

import { QuestionOrderDTO } from "@/src/types/question/questionOrder";
import { QuestionOrderOption } from "@/src/types/question/questionOrderOption";

export default function QuestionOrderCreateList({
  questionOrders,
  onUpdateAction,
}: {
  questionOrders: Array<QuestionOrderDTO>;
  onUpdateAction: (questionOrders: QuestionOrderDTO[]) => void;
}) {
  const handleQuestionOrderTitleChange =
    (index: number) => (e: ChangeEvent<HTMLInputElement>) => {
      const updatedQuestionOrders = [...questionOrders];

      updatedQuestionOrders[index].title = e.target.value;
      onUpdateAction(updatedQuestionOrders);
    };

  const handleQuestionOrderOptionNameChange =
    (index: number, optionIndex: number) =>
    (e: ChangeEvent<HTMLInputElement>) => {
      const updatedQuestionOrders = [...questionOrders];

      updatedQuestionOrders[index].options[optionIndex].name = e.target.value;
      onUpdateAction(updatedQuestionOrders);
    };

  const initialNewOption = { name: "", place: 0 };

  const [newOption, setNewOption] = useState(initialNewOption);

  const handleNewOptionNameChange = (e: ChangeEvent<HTMLInputElement>) => {
    setNewOption({ ...newOption, name: e.target.value });
  };

  const handleNewOptionCreation =
    (index: number, optionsLength: number) => () => {
      const updatedQuestionOrders = [...questionOrders];

      updatedQuestionOrders[index].options.push({
        id: "",
        questionOrder: "",
        name: newOption.name,
        place: optionsLength + 1,
        createdBy: "",
      });
      onUpdateAction(updatedQuestionOrders);
      setNewOption(initialNewOption);
    };

  const handleQuestionOrderOptionsChange =
    (index: number) => (questionOrdersOptions: QuestionOrderOption[]) => {
      const updatedQuestionOrders = [...questionOrders];

      updatedQuestionOrders[index].options = questionOrdersOptions;
      onUpdateAction(updatedQuestionOrders);
    };

  return (
    <>
      {questionOrders.length !== 0 && <h1>Order Questions</h1>}
      <div className={"flex flex-col gap-y-[5dvh]"}>
        {questionOrders?.map((question, index) => {
          return (
            <div key={index}>
              <Input
                label="Question title"
                placeholder={question.title}
                type="text"
                onChange={handleQuestionOrderTitleChange(index)}
              />
              <div>
                <QuestionOrderCreateListOptions
                  currentQuestionOrderIndex={index}
                  questionOrdersOptions={question.options}
                  onUpdateAction={handleQuestionOrderOptionsChange(index)}
                />
              </div>
              <div>
                <Input
                  label="New Option"
                  placeholder={"New Option"}
                  type="text"
                  onChange={handleNewOptionNameChange}
                />
                <Button
                  onPress={handleNewOptionCreation(
                    index,
                    question.options.length
                  )}
                >
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

// TODO move this to a separate file
function QuestionOrderCreateListOptions({
  currentQuestionOrderIndex,
  questionOrdersOptions,
  onUpdateAction,
}: {
  currentQuestionOrderIndex: number;
  questionOrdersOptions: Array<QuestionOrderOption>;
  onUpdateAction: (questionOrders: QuestionOrderOption[]) => void;
}) {
  const handleQuestionOrderOptionNameChange =
    (optionIndex: number) => (e: ChangeEvent<HTMLInputElement>) => {
      const updatedQuestionOrders = [...questionOrdersOptions];

      updatedQuestionOrders[optionIndex].name = e.target.value;
      onUpdateAction(updatedQuestionOrders);
    };

  const handleDragEnd = (result: DropResult) => {
    const updatedQuestionOrders = [...questionOrdersOptions];

    [
      updatedQuestionOrders[result.destination?.index!],
      updatedQuestionOrders[result.source.index],
    ] = [
      updatedQuestionOrders[result.source.index],
      updatedQuestionOrders[result.destination?.index!],
    ];

    onUpdateAction(updatedQuestionOrders);
  };

  return (
    <>
      <DragDropContext onDragEnd={handleDragEnd}>
        <Droppable
          droppableId={`QuestionOrderDroppableContainerFor-${currentQuestionOrderIndex}`}
        >
          {(provided, snapshot) => (
            <div {...provided.droppableProps} ref={provided.innerRef}>
              {questionOrdersOptions.map((option, optionIndex) => {
                return (
                  <Draggable
                    key={optionIndex}
                    draggableId={`QuestionOrderOptionDraggableFor-${currentQuestionOrderIndex}-${optionIndex}`}
                    index={optionIndex}
                  >
                    {(provider, snapshot) => (
                      <div
                        ref={provider.innerRef}
                        {...provider.draggableProps}
                        {...provider.dragHandleProps}
                      >
                        Swap
                        <Input
                          data-optionindex={optionIndex}
                          label="Option"
                          value={option.name}
                          type="text"
                          onChange={handleQuestionOrderOptionNameChange(optionIndex)}
                        />
                      </div>
                    )}
                  </Draggable>
                );
              })}
              {provided.placeholder}
            </div>
          )}
        </Droppable>
      </DragDropContext>
    </>
  );
}
