import { PairCollectionsDTO } from "@/src/types/question/pairCollection/pairCollections";

export interface QuestionPairCollectionsDTO {
  id: string;
  quiz: string;
  createdBy: string;
  title: string;
  options: Array<PairCollectionsDTO>;
}
