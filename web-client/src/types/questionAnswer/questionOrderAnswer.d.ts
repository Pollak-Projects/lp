export interface QuestionOrderAnswer {
  questionOrderId: string,
  points: number,
  options: [
    {
      questionOrderOptionsId: string,
      place: number
    }
  ]
}