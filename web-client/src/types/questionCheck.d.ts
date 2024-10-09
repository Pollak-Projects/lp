export interface QuestionCheck {
  title: string;
  value: Array<{
    title: string;
    answer: boolean;
  }>;
  comment: string;
}
