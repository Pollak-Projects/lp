export interface QuestionCheckboxesDTO {
  id: string,
  quiz: string,
  title: string,
  options: Array<
    {
      id: string,
      questionCheckbox: string,
      name: string,
      answer: boolean,
      createdBy: string
    }>
  ,
  createdBy: string

}
