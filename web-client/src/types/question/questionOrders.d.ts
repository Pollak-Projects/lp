export interface QuestionOrders {
  id: string,
  quiz: string,
  title: string,
  options: Array<
    {
      id: string,
      questionOrder: string,
      title: string,
      place: number,
      createdBy: string
    }
  >,
  createdBy: string
}
