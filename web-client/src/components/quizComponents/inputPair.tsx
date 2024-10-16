import { QuestionPair } from "@/src/types/questionPair";
import { useState } from "react";


export default function InputPair({question}:{question: QuestionPair}) {

const [isPair, setPair] = useState(question.value)

  return(
    <>

    </>
  )
}