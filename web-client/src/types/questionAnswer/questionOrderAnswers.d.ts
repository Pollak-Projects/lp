export interface QuestionOrderAnswers {
  questionOrderId: string,
  options: [
    {
      questionOrderOptionsId: string,
      place: number
    }
  ]
}