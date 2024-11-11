export interface QuestionOrders {
  id: string,
  quiz: string,
  title: string,
  options: [
    {
      id: string,
      questionOrder: string,
      title: string,
      place: 0,
      createdBy: string
    }
  ],
  createdBy: string
}
