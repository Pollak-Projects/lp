import { QuestionPairCollectionPair } from "@/src/types/question/pairCollection/questionPairCollectionPair";

export interface QuestionPairCollection {
  id: string;
  quiz: string;
  createdBy: string;
  title: string;
  options: Array<QuestionPairCollectionPair>;
}
