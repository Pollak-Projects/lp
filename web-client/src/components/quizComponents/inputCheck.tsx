import { Checkbox, CheckboxGroup } from "@nextui-org/checkbox";
import { QuestionCheck } from "@/src/types/questionCheck";
import React, { useState } from "react";

export default function InputCheck({ question }: { question: QuestionCheck }) {
  const [isCheck, setCheck] = useState(question.value); // the answer gets returned back to the state from teh checkboxes

  // what the sigma?
  function setCheckState(index: number) {
    // flippity floppity value goin wompitiy
    let temp = isCheck;
    isCheck[index].answer = !isCheck[index].answer;
    setCheck(temp);
  }

  const list_elements = isCheck.map((val, index: number) => {
    return (
      <>
        <div>

          <Checkbox
            className={"p-3"}
            key={ val.title }
            value={ val.title }
            onChange={() =>
              // calls the function that flips the value inside the state
              { setCheckState(index) }
            }
          >
            { val.title }
          </Checkbox>
        </div>
      </>
    );
  });

  return (
    <>
      <section
        className={
          "bg-content2 rounded-md w-full pt-5 mb-2 mt-1 flex text-center flex-col align-middle place-items-center"
        }
      >
        <span className={"text-2xl"}>{ question.title }</span>
        <span>{ question.comment }</span>
        <div
          className={
            "border-t border-white-opacity70 w-full flex flex-col place-items-center m-1 py-5"
          }
        >
          <CheckboxGroup defaultValue={["error ", "false"]}>
            <div className={"grid grid-flow-col grid-rows-5 text-left gap-2"}>

              { list_elements }
            </div>
          </CheckboxGroup>
        </div>
      </section>
    </>
  );
}
