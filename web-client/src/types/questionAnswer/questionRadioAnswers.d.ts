import { QuestionCheckboxesDTO } from "@/src/types/question/questionCheckboxes";

export interface QuestionRadioAnswersDTO {
  questionRadioId: string,
  options: [
    {
      questionRadioOptionsId: string,
      answer: string
    }
  ]
}