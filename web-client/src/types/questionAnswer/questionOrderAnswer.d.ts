export interface QuestionOrderAnswer {
  questionOrderId: string,
  options: [
    {
      questionOrderOptionsId: string,
      place: number
    }
  ]
}