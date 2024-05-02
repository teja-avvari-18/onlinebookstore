import { Cart } from "./cart";
import { OrderItems } from "./order-items";


export class Books {
    id!: number;
    title!: string;
    authorName!: string;
    genre!: string;
    year!: number;
    price!: number;
    stockAvailable!: number;

    cart: Cart[] = [];
    orderItems: OrderItems[] = [];
}