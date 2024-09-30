import { QuestionRadio } from "@/src/types/questionRadio";
import React, { SetStateAction } from "react";
import { Radio, RadioGroup } from "@nextui-org/radio";
import State from "sucrase/dist/types/parser/tokenizer/state";


export default function InputRadio({ question, setRadio } : {question: QuestionRadio, setRadio: any}) {

  const list_elements =  question.value.map((val) => {
        return (
                <Radio value={val}>{val}</Radio>
        )});

  return (
    <section className={"rounded-md border-1 mb-2 mt-1 mx-12 flex text-center flex-nowrap flex-col align-middle place-items-center"}>
      <span className={"text-2xl"}>{question.title}</span>
      <span>{question.comment}</span>
      <div className={"border-t-1 w-full flex flex-col place-items-center my-1 py-1"}>
        <RadioGroup
          onValueChange={setRadio}>
          { list_elements }
        </RadioGroup>
      </div>
    </section>
  )
}