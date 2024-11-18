import { QuestionOrderOption } from "@/src/types/question/questionOrderOption";

export interface QuestionOrderDTO {
  id: string;
  quiz: string;
  title: string;
  options: Array<QuestionOrderOption>;
  createdBy: string;
}
