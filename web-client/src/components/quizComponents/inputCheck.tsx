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
      <Checkbox
        key={val.title}
        value={val.title}
        onChange={() =>
          // calls the function that flips the value inside the state
          {
            setCheckState(index);
          }
        }
      >
        {val.title}
      </Checkbox>
    );
  });

  return (
    <>
      <section
        className={
          "rounded-md w-full border-1 mb-2 mt-1 mx-12 flex text-center flex-nowrap flex-col align-middle place-items-center"
        }
      >
        <span className={"text-2xl"}>{question.title}</span>
        <span>{question.comment}</span>
        <div
          className={
            "border-t-1 w-full flex flex-col place-items-center my-1 py-1"
          }
        >
          <CheckboxGroup defaultValue={["Error: ", "false"]}>
            {list_elements}
          </CheckboxGroup>
        </div>
      </section>
    </>
  );
}
