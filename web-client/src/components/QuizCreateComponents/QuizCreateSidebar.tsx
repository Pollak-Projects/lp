"use client";
import { Button } from "@nextui-org/button";
import { Dispatch, SetStateAction } from "react";
import { PressEvent } from "@react-types/shared";

import { QuizDataDTO } from "@/src/types/question/quizData";

export default function QuizCreateSidebar({
  onCreateAction,
}: {
  onCreateAction: Dispatch<SetStateAction<QuizDataDTO>>;
}) {
  const handleQuestionCheckboxCreation = (e: PressEvent) => {
    onCreateAction((prevQuizData) => ({
      ...prevQuizData,
      questionCheckboxes: [
        ...prevQuizData.questionCheckboxes,
        {
          id: "",
          quiz: "",
          title: "Title",
          options: [],
          createdBy: "",
          createdAt: "",
        },
      ],
    }));
    console.log(onCreateAction);
  };
  const handleQuestionFileCreation = (e: PressEvent) => {
    onCreateAction((prevQuizData) => ({
      ...prevQuizData,
      questionFiles: [
        ...prevQuizData.questionFiles,
        {
          id: "",
          quiz: "",
          title: "Title",
          fileId: "",
          createdBy: "",
          createdAt: "",
        },
      ],
    }));
  };

  const handleQuestionOrderCreation = (e: PressEvent) => {
    onCreateAction((prevQuizData) => ({
      ...prevQuizData,
      questionOrders: [
        ...prevQuizData.questionOrders,
        {
          id: "",
          quiz: "",
          title: "Title",
          options: [],
          createdBy: "",
          createdAt: "",
        },
      ],
    }));
    console.log(onCreateAction);
  };

  const handleQuestionPairCollectionCreation = (e: PressEvent) => {
    onCreateAction((prevQuizData) => ({
      ...prevQuizData,
      questionPairCollection: [
        ...prevQuizData.questionPairCollection,
        {
          id: "",
          quiz: "",
          title: "Title",
          options: [],
          createdBy: "",
          createdAt: "",
        },
      ],
    }));
    console.log(onCreateAction);
  };

  const handleQuestionRadioCreation = (e: PressEvent) => {
    onCreateAction((prevQuizData) => ({
      ...prevQuizData,
      questionRadios: [
        ...prevQuizData.questionRadios,
        {
          id: "",
          quiz: "",
          title: "Title",
          options: [],
          createdBy: "",
          createdAt: "",
        },
      ],
    }));
    console.log(onCreateAction);
  };

  const handleQuestionTextCreation = (e: PressEvent) => {
    onCreateAction((prevQuizData) => ({
      ...prevQuizData,
      questionTexts: [
        ...prevQuizData.questionTexts,
        {
          id: "",
          quiz: "",
          title: "Title",
          answer: "answer",
          createdBy: "",
          createdAt: "",
        },
      ],
    }));
    console.log(onCreateAction);
  };

  return (
    <>
      <div className={"flex flex-col gap-y-[2dvh]"}>
        <Button onPress={handleQuestionCheckboxCreation}>
          Add checkbox question
        </Button>
        <Button onPress={handleQuestionFileCreation}>Add file question</Button>
        <Button onPress={handleQuestionOrderCreation}>
          Add order question
        </Button>
        <Button onPress={handleQuestionPairCollectionCreation}>
          Add pair collection question
        </Button>
        <Button onPress={handleQuestionRadioCreation}>
          Add radio question
        </Button>
        <Button onPress={handleQuestionTextCreation}>Add text question</Button>
      </div>
    </>
  );
}
