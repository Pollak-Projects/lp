import { QuestionTexts } from "@/src/types/question/questionTexts";
import { Textarea } from "@nextui-org/input";
import { useState } from "react";

export default function InputText({ question }: { question: QuestionTexts }) {
  const [isText, setText] = useState(question);

  let getText = (e: string) => {
    // returns the answer from the text input field
  };

  return (
    <section
      className={"bg-content2 rounded-xl w-full pt-5 mb-2 mt-1 mx-12 flex text-center flex-nowrap flex-col align-middle place-items-center"}
    >
      <span className={"text-2xl"}>{isText.title}</span>
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
