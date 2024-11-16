import { QuestionCheckboxesDTO } from "@/src/types/question/questionCheckboxes";
import { QuestionFilesDTO } from "@/src/types/question/questionFiles";
import { QuestionOrdersDTO } from "@/src/types/question/questionOrders";
import { QuestionPairCollectionsDTO } from "@/src/types/question/questionPairCollections";
import { QuestionRadiosDTO } from "@/src/types/question/questionRadios";
import { QuestionTextsDTO } from "@/src/types/question/questionTexts";

export interface QuizDataDTO {
  id: string;
  name: string;
  description: string;
  createdBy: string;
  createdAt: string;
  deadline: string;
  viewAfterSubmission: boolean;
  questionCheckboxes: Array<QuestionCheckboxesDTO>;
  questionFiles: Array<QuestionFilesDTO>;
  questionOrders: Array<QuestionOrdersDTO>;
  questionPairCollection: Array<QuestionPairCollectionsDTO>;
  questionRadios: Array<QuestionRadiosDTO>;
  questionTexts: Array<QuestionTextsDTO>;
  quizAnswers: Array<string>;
}
