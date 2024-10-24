"use client";
import { QuestionRadio } from "@/src/types/questionRadio";
import { Radio, RadioGroup } from "@nextui-org/radio";
import React, { useState } from "react";

export default function InputRadio({ question }: { question: QuestionRadio }) {
  const [isRadio, setRadio] = useState(question.value);

  const getRadioAnswer = (e: string) => {
    // returns the answer from the radio buttons
  };

  const list_elements = isRadio.map((val, index: number) => {
    return (
      <Radio key={val.title} value={index.toString()}>
        {val.title}
      </Radio>
    );
  });

  return (
    <section
      className={"bg-content2 rounded-xl w-full pt-5 mb-2 mt-1 mx-12 flex text-center flex-nowrap flex-col align-middle place-items-center"}
    >
      <span className={"text-2xl"}>{question.title}</span>
      <span>{question.comment}</span>
      <div
        className={"border-t border-white-opacity70 w-full flex flex-col place-items-center my-1 py-3"}>
        <RadioGroup onValueChange={getRadioAnswer}>{list_elements}</RadioGroup>
        {/*calling the generated radios of the component*/}
      </div>
    </section>
  );
}
