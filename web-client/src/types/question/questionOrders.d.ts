import { QuestionOrderOptionDTO } from "@/src/types/question/questionOrderOption";

export interface QuestionOrdersDTO {
  id: string;
  quiz: string;
  title: string;
  options: Array<QuestionOrderOptionDTO>;
  createdBy: string;
}
