import { QuestionText } from "@/src/types/questionText";
import { Textarea } from "@nextui-org/input";
import { useState } from "react";

export default function InputText({ question }: { question: QuestionText }) {
  const [isText, setText] = useState(question.value);

  let getText = (e: string) => {
    // returns the answer from the text input field
  };

  return (
    <section
      className={
        "rounded-md w-full border-1 mb-2 mt-1 mx-12 flex text-center flex-nowrap flex-col align-middle place-items-center"
      }
    >
      <span className={"text-2xl"}>{isText.title}</span>
      <span>{isText.comment}</span>
      <Textarea
        isRequired={true}
        style={{ background: "none" }}
        variant="bordered"
        onValueChange={getText}
        placeholder="Enter the answer"
        className={"max-w-ms mt-2 max-h-60 overflow-scroll text-justify"}
      />
    </section>
  );
}
