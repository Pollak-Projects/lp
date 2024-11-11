export interface QuestionRadios {
  id: string,
  quiz: string,
  title: string,
  options: [
    {
      id: string,
      questionRadio: string,
      title: string,
      answer: true,
      createdBy: string
    }
  ],
  createdBy: string
}
