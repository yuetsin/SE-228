export interface Book {
  cover: string
  bookName: string
  author: string
  details: string
  uuid: string
  remain_storage: number
}

export function initBook (obj: Object) {
  return obj as Book
}
