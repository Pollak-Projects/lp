import { PairCollectionAnswers } from "@/src/types/questionAnswer/pairCollection/pairCollectionAnswers";
import { QuestionCheckboxesDTO } from "@/src/types/question/questionCheckboxes";

export interface QuestionPairCollectionAnswersDTO {
     questionPairCollectionId:  string,
     pairs: Array<PairCollectionAnswers>
  }