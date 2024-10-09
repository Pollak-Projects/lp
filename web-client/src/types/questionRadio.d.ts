export interface QuestionRadio {
  title: string;
  value: Array<{
    title: string;
    answer: boolean;
  }>;
  comment: string;
}
