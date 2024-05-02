import { Books } from "./book";
import { User } from "./user";



export class Cart {
    id!: number;
    quantity!: number;
    price!: number;
    book!: Books;
    user!:User;
}