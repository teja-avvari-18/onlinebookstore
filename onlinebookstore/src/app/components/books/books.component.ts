import { Component, OnInit } from '@angular/core';
import { Books } from '../../models/book';
import { Cart } from '../../models/cart';
import { CartDTO } from '../../models/cartdto';
import { BooksService } from '../../service/books.service';
import { CartService } from '../../service/cart.service';

@Component({
  selector: 'app-books',
  templateUrl: './books.component.html',
  styleUrl: './books.component.css'
})
export class BooksComponent implements OnInit {

  books: Books[] = [];
  cartDTO: CartDTO = new CartDTO();

  constructor(private bookService:BooksService,private cartService:CartService){}

  uId=Number(localStorage.getItem('id'));
  ngOnInit():void{
    this.bookService.getAllBooks().subscribe((data:Books[])=> {
      this.books = data;
    })
  }

  addToCart(quantity:number,bookId:number,userId:number) {
    this.cartDTO.bookId=bookId;
    this.cartDTO.userId=userId;
    // this.cartDTO.quantity=quantity;
    this.cartService.addToCart(this.cartDTO).subscribe(data => {
     console.log(data);
     alert("Book added to cart");
    })

  }

}
