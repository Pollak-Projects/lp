"use client"

import { Textarea } from "@nextui-org/input";
import { ChangeEvent, FC, useState } from "react";
import { QuestionTextAnswer } from "@/src/types/questionAnswer/questionTextAnswer";
import { QuestionText } from "@/src/types/question/questionText";


interface Props {
  questionText: QuestionText;
  sendAnswerData: (value: QuestionTextAnswer) => void;
}

const InputText: FC<Props> = ( { questionText, sendAnswerData}) => {

/*
  const handleSendTextAnswer = (e) => {
    sendAnswerData(e)
  }
*/
  const handleChange = (event: ChangeEvent<HTMLTextAreaElement>) => {
    sendAnswerData({
      questionTextId: questionText.id,
      answer: event.target.value
    })
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

        placeholder="Enter the answer"
        className={"max-w-ms mt-2 max-h-60 overflow-scroll text-justify"}
      />
    </section>
  );
}

export default InputText
