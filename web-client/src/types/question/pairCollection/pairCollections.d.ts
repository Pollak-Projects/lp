import { QuestionCheckboxesDTO } from "@/src/types/question/questionCheckboxes";

export interface PairCollectionsDTO {
  id: string,
  belongsTo: string,
  createdBy: string,
  left: {
    id: string,
    content: string
  },
  right: {
    id: string,
    content: string
  }
}