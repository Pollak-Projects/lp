export interface QuestionRadioAnswer {
  questionRadioId: string,
  points: number,
  options: [
    {
      questionRadioOptionsId: string,
      answer: string
    }
  ]
}