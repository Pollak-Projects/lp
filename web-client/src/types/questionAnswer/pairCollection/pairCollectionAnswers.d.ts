import { QuestionCheckboxesDTO } from "@/src/types/question/questionCheckboxes";

export interface PairCollectionAnswersDTO {
  questionPairCollectionPairId: string,
  left: {
    content: string
  },
  right: {
    content: string
  }
}