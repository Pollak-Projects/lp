"use client";

import React, { ChangeEvent, useState } from "react";
import { Input } from "@nextui-org/input";

import QuizCreateSidebar from "@/src/components/QuizCreateComponents/QuizCreateSidebar";
import { QuizDataDTO } from "@/src/types/question/quizData";
import QuestionTextCreateList from "@/src/components/QuizCreateComponents/QuestionTextCreateList";
import QuestionCheckboxCreateList from "@/src/components/QuizCreateComponents/QuestionCheckboxCreateList";
import { QuestionTextsDTO } from "@/src/types/question/questionTexts";
import { QuestionCheckboxesDTO } from "@/src/types/question/questionCheckboxes";
import QuestionFileCreateList from "@/src/components/QuizCreateComponents/QuestionFileCreateList";
import { QuestionFilesDTO } from "@/src/types/question/questionFiles";
import QuestionOrderCreateList from "@/src/components/QuizCreateComponents/QuestionOrderCreateList";
import { QuestionOrdersDTO } from "@/src/types/question/questionOrders";
import QuestionRadioCreateList from "@/src/components/QuizCreateComponents/QuestionRadioCreateList";
import { QuestionRadiosDTO } from "@/src/types/question/questionRadios";
import QuestionPairCollectionCreateList from "@/src/components/QuizCreateComponents/QuestionPairCollectionCreateList";
import { QuestionPairCollectionsDTO } from "@/src/types/question/questionPairCollections";

export default function QuizCreatePage() {
  const initialQuizData: QuizDataDTO = {
    id: "",
    name: "",
    description: "",
    createdAt: "",
    createdBy: "",
    deadline: "",
    viewAfterSubmission: true,
    questionCheckboxes: [],
    questionFiles: [],
    questionOrders: [],
    questionPairCollection: [],
    questionRadios: [],
    questionTexts: [],
    quizAnswers: [],
  };

  const [quizData, setQuizData] = useState<QuizDataDTO>(initialQuizData);

  const handleQuizNameChange = (
    e: ChangeEvent<HTMLInputElement> | undefined
  ) => {
    setQuizData({ ...quizData, name: e!.target.value });
  };

  const handleQuizDescriptionChange = (
    e: ChangeEvent<HTMLInputElement> | undefined
  ) => {
    setQuizData({ ...quizData, description: e!.target.value });
  };

  const handleQuizDeadlineChange = (
    e: ChangeEvent<HTMLInputElement> | undefined
  ) => {
    setQuizData({ ...quizData, deadline: e!.target.value });
  };

  const handleQuizViewAfterSubmissionChange = (
    e: ChangeEvent<HTMLInputElement> | undefined
  ) => {
    setQuizData({ ...quizData, viewAfterSubmission: e!.target.checked });
  };

  const handelQuestionTextsChange = (questionTexts: QuestionTextsDTO[]) => {
    setQuizData((prevQuizData) => ({
      ...prevQuizData,
      questionTexts,
    }));
  };

  const handleQuestionCheckboxesChange = (
    questionCheckboxes: QuestionCheckboxesDTO[]
  ) => {
    setQuizData((prevQuizData) => ({
      ...prevQuizData,
      questionCheckboxes,
    }));
  };

  const handleQuestionFilesChange = (questionFiles: QuestionFilesDTO[]) => {
    setQuizData((prevQuizData) => ({
      ...prevQuizData,
      questionFiles,
    }));
  };

  const handleQuestionOrdersChange = (questionOrders: QuestionOrdersDTO[]) => {
    setQuizData((prevQuizData) => ({
      ...prevQuizData,
      questionOrders,
    }));
  };

  const handleQuestionRadiosChange = (questionRadios: QuestionRadiosDTO[]) => {
    setQuizData((prevQuizData) => ({
      ...prevQuizData,
      questionRadios,
    }));
  };

  const handleQuestionPairCollectionsChange = (
    questionPairCollections: QuestionPairCollectionsDTO[]
  ) => {
    setQuizData((prevQuizData) => ({
      ...prevQuizData,
      questionPairCollection: questionPairCollections,
    }));
  };

  return (
    <>
      <div className={"flex-grow"}>
        <div>
          <h1>Quiz info</h1>
          <Input
            label="Quiz name"
            placeholder={"Quiz name"}
            type="text"
            onChange={handleQuizNameChange}
          />
          <Input
            label="Quiz description"
            placeholder={"Quiz description"}
            type="text"
            onChange={handleQuizDescriptionChange}
          />
          <Input
            label="Quiz deadline"
            placeholder={" "}
            type="datetime-local"
            onChange={handleQuizDeadlineChange}
          />
          <Input
            label="View after submission"
            placeholder={" "}
            type="checkbox"
            onChange={handleQuizViewAfterSubmissionChange}
          />
        </div>
        <div>
          <QuestionTextCreateList
            questionTexts={quizData.questionTexts}
            onUpdateAction={handelQuestionTextsChange}
          />
        </div>
        <div>
          <QuestionCheckboxCreateList
            questionCheckboxes={quizData.questionCheckboxes}
            onUpdateAction={handleQuestionCheckboxesChange}
          />
        </div>
        <div>
          <QuestionFileCreateList
            questionFiles={quizData.questionFiles}
            onUpdateAction={handleQuestionFilesChange}
          />
        </div>
        <div>
          <QuestionOrderCreateList
            questionOrders={quizData.questionOrders}
            onUpdateAction={handleQuestionOrdersChange}
          />
        </div>
        <div>
          <QuestionPairCollectionCreateList
            questionPairCollections={quizData.questionPairCollection}
            onUpdateAction={handleQuestionPairCollectionsChange}
          />
        </div>
        <div>
          <QuestionRadioCreateList
            questionRadios={quizData.questionRadios}
            onUpdateAction={handleQuestionRadiosChange}
          />
        </div>
      </div>
      <div className={"w-[25dvw]"}>
        <QuizCreateSidebar onCreateAction={setQuizData} />
      </div>
    </>
  );
}
