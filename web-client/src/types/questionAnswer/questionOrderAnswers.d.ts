import { QuestionCheckboxesDTO } from "@/src/types/question/questionCheckboxes";

export interface QuestionOrderAnswersDTO {
  questionOrderId: string,
  options: [
    {
      questionOrderOptionsId: string,
      place: number
    }
  ]
}