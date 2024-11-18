"use client";

import { Input } from "@nextui-org/input";
import { Button } from "@nextui-org/button";
import { ChangeEvent, useState } from "react";
import { PressEvent } from "@react-types/shared";
import { DragDropContext, Draggable, Droppable, DropResult } from "@hello-pangea/dnd";

import { QuestionPairCollectionPair } from "@/src/types/question/pairCollection/questionPairCollectionPair";
import { QuestionPairCollection } from "@/src/types/question/questionPairCollection";

export default function QuestionPairCollectionCreateList({
  questionPairCollections,
  onUpdateAction,
}: {
  questionPairCollections: Array<QuestionPairCollection>;
  onUpdateAction: (
    questionPairCollections: QuestionPairCollection[]
  ) => void;
}) {
  const handleQuestionPairCollectionTitleChange =
    (index: number) => (e: ChangeEvent<HTMLInputElement>) => {
      const updatedQuestionPairCollections = [...questionPairCollections];

      updatedQuestionPairCollections[index].title = e.target.value;
      onUpdateAction(updatedQuestionPairCollections);
    };

  const handleQuestionPairCollectionOptionsChange =
    (index: number) =>
      (questionPairCollectionOptions: QuestionPairCollectionPair[]) => {
      const updatedQuestionPairCollections = [...questionPairCollections];

      updatedQuestionPairCollections[index].options =
        questionPairCollectionOptions;

      onUpdateAction(updatedQuestionPairCollections);
    };

  const initialNewOption = {
    left: {
      content: "",
    },
    right: {
      content: "",
    },
  };

  const [newOption, setNewOption] = useState(initialNewOption);

  const handleNewOptionLeftContentChange = (
    e: ChangeEvent<HTMLInputElement>
  ) => {
    setNewOption({ ...newOption, left: { content: e.target.value } });
  };

  const handleNewOptionRightContentChange = (
    e: ChangeEvent<HTMLInputElement>
  ) => {
    setNewOption({ ...newOption, right: { content: e.target.value } });
  };

  const handleNewOptionCreation = (index: number) => (e: PressEvent) => {
    const updatedQuestionPairCollections = [...questionPairCollections];

    updatedQuestionPairCollections[index].options.push({
      id: "",
      belongsTo: "",
      createdBy: "",
      left: {
        id: "",
        content: newOption.left.content,
      },
      right: {
        id: "",
        content: newOption.right.content,
      },
    });
    onUpdateAction(updatedQuestionPairCollections);
    setNewOption(initialNewOption);
  };

  return (
    <>
      {questionPairCollections.length !== 0 && <h1>Pair Questions</h1>}
      <div className={"flex flex-col gap-y-[5dvh]"}>
        {questionPairCollections?.map((question, index) => {
          return (
            <div key={index}>
              <Input
                label="Question title"
                placeholder={question.title}
                type="text"
                onChange={handleQuestionPairCollectionTitleChange(index)}
              />
              <div className={"my-[5dvh]"}>
                <QuestionPairCollectionCreateListOptions
                  currentQuestionPairCollectionIndex={index}
                  questionPairCollectionOptions={question.options}
                  onUpdateAction={handleQuestionPairCollectionOptionsChange(
                    index
                  )}
                />
              </div>
              <div>
                <div className={"grid grid-cols-2"}>
                  <Input
                    label={"Left Option"}
                    placeholder={"Left Option"}
                    type="text"
                    onChange={handleNewOptionLeftContentChange}
                  />
                  <Input
                    label={"Right Option"}
                    placeholder={"Right Option"}
                    type="text"
                    onChange={handleNewOptionRightContentChange}
                  />
                </div>
                <Button onPress={handleNewOptionCreation(index)}>
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

function QuestionPairCollectionCreateListOptions({
  currentQuestionPairCollectionIndex,
  questionPairCollectionOptions,
  onUpdateAction,
}: {
  currentQuestionPairCollectionIndex: number;
  questionPairCollectionOptions: Array<QuestionPairCollectionPair>;
  onUpdateAction: (questionPair: QuestionPairCollectionPair[]) => void;
}) {
  const handleDragEndLeft = (result: DropResult) => {
    const updatedQuestionPairCollections = [...questionPairCollectionOptions];

    [
      updatedQuestionPairCollections[result.destination?.index!].left,
      updatedQuestionPairCollections[result.source.index].left,
    ] = [
      updatedQuestionPairCollections[result.source.index].left,
      updatedQuestionPairCollections[result.destination?.index!].left,
    ];

    onUpdateAction(updatedQuestionPairCollections);
  };
  const handleDragEndRight = (result: DropResult) => {
    const updatedQuestionPairCollections = [...questionPairCollectionOptions];

    [
      updatedQuestionPairCollections[result.destination?.index!].right,
      updatedQuestionPairCollections[result.source.index].right,
    ] = [
      updatedQuestionPairCollections[result.source.index].right,
      updatedQuestionPairCollections[result.destination?.index!].right,
    ];

    onUpdateAction(updatedQuestionPairCollections);
  };

  const handleQuestionPairCollectionOptionLeftContentChange =
    (optionIndex: number) => (e: ChangeEvent<HTMLInputElement>) => {
      const updatedQuestionPairCollections = [...questionPairCollectionOptions];

      updatedQuestionPairCollections[optionIndex].left.content = e.target.value;

      onUpdateAction(updatedQuestionPairCollections);
    };

  return (
    <>
      <div className={"grid grid-cols-2"}>
        <DragDropContext onDragEnd={handleDragEndLeft}>
          <Droppable
            droppableId={`QuestionPairCollectionOptionsLeftDroppableContainerFor-${currentQuestionPairCollectionIndex}`}
          >
            {(provided, snapshot) => (
              <div {...provided.droppableProps} ref={provided.innerRef}>
                {questionPairCollectionOptions.map((option, optionIndex) => {
                  return (
                    <Draggable
                      key={optionIndex}
                      draggableId={`QuestionPairCollectionOptionLeftFor-${currentQuestionPairCollectionIndex}-${optionIndex}`}
                      index={optionIndex}
                    >
                      {(provided, snapshot) => (
                        <div
                          ref={provided.innerRef}
                          {...provided.draggableProps}
                          {...provided.dragHandleProps}
                        >
                          <Input
                            label="Left Option"
                            value={option.left.content}
                            type="text"
                            onChange={handleQuestionPairCollectionOptionLeftContentChange(
                              optionIndex
                            )}
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
        <DragDropContext onDragEnd={handleDragEndRight}>
          <Droppable
            droppableId={`QuestionPairCollectionOptionsRightDroppableContainerFor-${currentQuestionPairCollectionIndex}`}
          >
            {(provided, snapshot) => (
              <div {...provided.droppableProps} ref={provided.innerRef}>
                {questionPairCollectionOptions.map((option, optionIndex) => {
                  return (
                    <Draggable
                      key={optionIndex}
                      draggableId={`QuestionPairCollectionOptionRightFor-${currentQuestionPairCollectionIndex}-${optionIndex}`}
                      index={optionIndex}
                    >
                      {(provided, snapshot) => (
                        <div
                          ref={provided.innerRef}
                          {...provided.draggableProps}
                          {...provided.dragHandleProps}
                        >
                          <Input
                            label="Right Option"
                            value={option.right.content}
                            type="text"
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
      </div>
    </>
  );
}
