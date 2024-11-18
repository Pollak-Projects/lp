import {
  QuestionPairCollectionPairAnswer
} from "@/src/types/questionAnswer/pairCollection/questionPairCollectionPairAnswer";

export interface QuestionPairCollectionAnswer {
  questionPairCollectionId: string,
  pairs: Array<QuestionPairCollectionPairAnswer>
}