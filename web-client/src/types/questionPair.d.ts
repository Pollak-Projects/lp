export interface QuestionPair {
  title: string;
  value:{
    left: Array<{
      id: string, content: string
    }>,
    right: Array<{
      id: string, content: string
    }>
  }
}