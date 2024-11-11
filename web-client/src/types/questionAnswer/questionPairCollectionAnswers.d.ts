import { PairCollectionAnswers } from "@/src/types/questionAnswer/pairCollection/pairCollectionAnswers";

export interface QuestionPairCollectionAnswers {
     questionPairCollectionId:  string,
     pairs: Array<PairCollectionAnswers>
  }