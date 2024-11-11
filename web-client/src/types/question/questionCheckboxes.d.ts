export interface QuestionCheckboxes {
  id: string,
  quiz: string,
  title: string,
  options: [
    {
      id: string,
      questionCheckbox: string,
      name: string,
      answer: true,
      createdBy: string
    }
  ],
  createdBy: string
}
