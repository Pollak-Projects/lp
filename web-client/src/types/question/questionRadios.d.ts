import { QuestionCheckboxesDTO } from "@/src/types/question/questionCheckboxes";

export interface QuestionRadiosDTO {
  id: string,
  quiz: string,
  title: string,
  options: Array<
    {
      id: string,
      questionRadio: string,
      title: string,
      answer: boolean,
      createdBy: string
    }
  >,
  createdBy: string
}
