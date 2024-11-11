export interface PairCollections {
  id: string,
  belongsTo: string,
  createdBy: string,
  left: {
    id: string,
    content: string
  },
  right: {
    id: string,
    content: string
  }
}