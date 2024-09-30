import { QuestionCheck } from "@/src/types/questionCheck";
import { Checkbox, CheckboxGroup } from "@nextui-org/checkbox";
import { useState } from "react";


export default function InputCheck({question, setCheck} : {question: QuestionCheck, setCheck: any}) {

  const list_elements = question.value.map((val) => {
    return (
      <Checkbox value={val}>{val}</Checkbox>
    )
  })

  //const [isInvalid, setIsInvalid] = useState(true)



  return (
    <>
    <section className={"rounded-md border-1 mb-2 mt-1 mx-12 flex text-center flex-nowrap flex-col align-middle place-items-center"}>
      <span className={"text-2xl"}>{question.title}</span>
      <span>{question.comment}</span>
      <div className={"border-t-1 w-full flex flex-col place-items-center my-1 py-1"}>
        <CheckboxGroup
          defaultValue={["",""]}
          /*isInvalid={isInvalid}
          onValueChange={(value) => {
          setIsInvalid(value.length < 1) allows you to make all red before selecting any element. cool stuff
        }}*/
        >
          {list_elements}
        </CheckboxGroup>

      </div>
    </section>
    </>
  )
}