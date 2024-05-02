import { Books } from "./book";
import { Orders } from "./orders";


export class OrderItems {
    id!: number;
    quantity!: number;
    price!: number;
    orders!: Orders;
    books!: Books;
}