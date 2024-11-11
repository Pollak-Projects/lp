import { PairCollections } from "@/src/types/question/pairCollection/pairCollections";

export interface QuestionPairCollections {
  id: string,
  quiz: string,
  createdBy: string,
  title: string,
  pairs: Array<PairCollections>
}