import { QuestionCheckboxes } from "@/src/types/question/questionCheckboxes";
import { QuestionFiles } from "@/src/types/question/questionFiles";
import { QuestionOrders } from "@/src/types/question/questionOrders";
import { QuestionPairCollections } from "@/src/types/question/questionPairCollections";
import { QuestionRadios } from "@/src/types/question/questionRadios";
import { QuestionTexts } from "@/src/types/question/questionTexts";

export interface QuizData {
  id: string,
  name: string,
  description: string,
  createdBy: string,
  createdAt: string,
  deadline: string,
  viewAfterSubmission: true,
  questionCheckboxes: Array<QuestionCheckboxes>,
  questionFiles: Array<QuestionFiles>,
  questionOrders: Array<QuestionOrders>,
  questionPairCollection: Array<QuestionPairCollections>,
  questionRadios: Array<QuestionRadios>,
  questionTexts: Array<QuestionTexts>,
  quizAnswers: Array<string>
}