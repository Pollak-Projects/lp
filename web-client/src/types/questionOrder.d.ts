export interface QuestionOrder {
  title: string,
  value: Array<{
    id: string;
    content: string;
  }>
  comment: string;
}