export interface Book {
  coverUrl: string
  title: string
  isbn: string
  author: string
  description: string
  uuid: string
  storage: number
  price: number
  couponPrice: number
  type: string
}

export function initBook (obj: Object) {
  return obj as Book
}
