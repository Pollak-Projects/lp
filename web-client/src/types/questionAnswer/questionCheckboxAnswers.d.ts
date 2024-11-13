import { QuestionCheckboxesDTO } from "@/src/types/question/questionCheckboxes";

export interface QuestionCheckboxAnswersDTO {
  questionCheckboxId: string,
  options: [
    {
      questionCheckboxOptionsId: string,
      answer: true
    }
  ]
}