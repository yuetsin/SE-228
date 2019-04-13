export interface Book {
  cover_url: string
  title: string
  isbn: string
  author: string
  description: string
  uuid: string
  storage: number
  price: number
  coupon_price: number
  type: string
}

export function initBook (obj: Object) {
  return obj as Book
}
