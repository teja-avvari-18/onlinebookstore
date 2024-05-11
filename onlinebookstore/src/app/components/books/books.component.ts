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
  filteredBooks: Books[] = [];
  cartDTO: CartDTO = new CartDTO();
  cartDTOS : {[bookId:number]:number}={};
  bookQuantities :{[bookId:number]:number} = {};

  constructor(private bookService:BooksService,private cartService:CartService){}

  uId=Number(localStorage.getItem('id'));
  ngOnInit():void{
    this.bookService.getAllBooks().subscribe((data:Books[])=> {
      this.books = data;
      this.filteredBooks=data;
    })
  }

  // addToCart(quantity:number,bookId:number,userId:number) {
  //   this.cartDTO.bookId=bookId;
  //   this.cartDTO.userId=userId;
  //   // this.cartDTO.quantity=quantity;
  //   this.cartService.addToCart(this.cartDTO).subscribe(data => {
  //    console.log(data);
  //    alert("Book added to cart");
  //   })
  // }

  addToCart(quantity: number, bookId: number, userId: number) {
    let book = this.filteredBooks.find(item => item.id === bookId);
    if (book) { 
      this.cartDTO.bookId = book.id;
      this.cartDTO.userId = userId;
      this.cartDTO.quantity = quantity;
      this.cartService.addToCart(this.cartDTO).subscribe(data => {
        console.log(data);
        alert("Added to cart");
      });
    } else {
      console.error('Product not found');
    }
  }
}
