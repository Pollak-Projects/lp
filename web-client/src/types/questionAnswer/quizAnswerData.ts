import { QuestionCheckboxAnswer } from "@/src/types/questionAnswer/questionCheckboxAnswer";
import { QuestionRadioAnswer } from "@/src/types/questionAnswer/questionRadioAnswer";
import { QuestionFileAnswer } from "@/src/types/questionAnswer/questionFileAnswer";
import { QuestionOrderAnswer } from "@/src/types/questionAnswer/questionOrderAnswer";
import { QuestionPairCollectionAnswer } from "@/src/types/questionAnswer/questionPairCollectionAnswer";
import { QuestionTextAnswer } from "@/src/types/questionAnswer/questionTextAnswer";

export interface QuizAnswerData {
  id: string;
  // This exists in the database, but is not needed to create the entity
  // DO NOT ADD THIS TO THE ENTITY WHEN CREATING A NEW ENTITY
  createdBy: string;
  questionCheckboxAnswers: Array<QuestionCheckboxAnswer>;
  questionFileAnswers: Array<QuestionFileAnswer>;
  questionOrderAnswers: Array<QuestionOrderAnswer>;
  questionPairCollectionAnswers: Array<QuestionPairCollectionAnswer>;
  questionRadioAnswers: Array<QuestionRadioAnswer>;
  questionTextAnswers: Array<QuestionTextAnswer>;
}