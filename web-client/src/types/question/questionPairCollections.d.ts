import { PairCollectionsDTO } from "@/src/types/question/pairCollection/pairCollections";
import { QuestionCheckboxesDTO } from "@/src/types/question/questionCheckboxes";

export interface QuestionPairCollectionsDTO {
  id: string,
  quiz: string,
  createdBy: string,
  title: string,
  pairs: Array<PairCollectionsDTO>
}