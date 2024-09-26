import { RadioQuestion } from "@/src/types/questionRadio";
import React, { SetStateAction } from "react";
import { Radio, RadioGroup } from "@nextui-org/radio";
import State from "sucrase/dist/types/parser/tokenizer/state";


export type InputRadioFields = {
  id: string;
  title: string;
  value: string;
  comment: string;
}

export default function InputRadio({ question, SetRadio } : {question: RadioQuestion, SetRadio: any}) {

  const list_elements=  question.value.map((val)=> {
        return (
                <Radio value={val}>{val}</Radio>
        )});

  return (
    <section className={"rounded-md border-1 mt-1 mx-12 flex text-center flex-nowrap flex-col align-middle place-items-center"}>
      <RadioGroup
        onValueChange={SetRadio}
        label={question.title}>
        { list_elements }
      </RadioGroup>
    </section>
  )
}