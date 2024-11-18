export interface QuestionRadio {
  id: string;
  quiz: string;
  title: string;
  options: Array<{
    id: string;
    questionRadio: string;
    name: string;
    answer: boolean;
    createdBy: string;
  }>;
  createdBy: string;
}
