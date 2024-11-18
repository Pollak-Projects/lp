export interface QuestionCheckboxAnswer {
  questionCheckboxId: string,
  points: number,
  options: [
    {
      questionCheckboxOptionsId: string,
      answer: true
    }
  ]
}