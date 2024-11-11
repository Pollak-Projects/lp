export interface QuestionRadios {
  id: string,
  quiz: string,
  title: string,
  options: Array<
    {
      id: string,
      questionRadio: string,
      title: string,
      answer: boolean,
      createdBy: string
    }
  >,
  createdBy: string
}
