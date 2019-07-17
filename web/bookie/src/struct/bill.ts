import { Book } from './book'

export interface Bill {
  item: Book
  amount: number
  address: string
}
