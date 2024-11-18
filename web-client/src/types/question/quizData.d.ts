import { QuestionCheckbox } from "@/src/types/question/questionCheckbox";
import { QuestionFile } from "@/src/types/question/questionFile";
import { QuestionOrderDTO } from "@/src/types/question/questionOrder";
import { QuestionPairCollection } from "@/src/types/question/questionPairCollection";
import { QuestionRadio } from "@/src/types/question/questionRadio";
import { QuestionText } from "@/src/types/question/questionText";

export interface QuizData {
  id: string;
  name: string;
  description: string;
  createdBy: string;
  createdAt: string;
  deadline: string;
  viewAfterSubmission: boolean;
  questionCheckboxes: Array<QuestionCheckbox>;
  questionFiles: Array<QuestionFile>;
  questionOrders: Array<QuestionOrderDTO>;
  questionPairCollection: Array<QuestionPairCollection>;
  questionRadios: Array<QuestionRadio>;
  questionTexts: Array<QuestionText>;
  quizAnswers: Array<string>;
}
