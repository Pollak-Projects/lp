export interface QuestionRadioAnswer {
  questionRadioId: string,
  options: [
    {
      questionRadioOptionsId: string,
      answer: string
    }
  ]
}