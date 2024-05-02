import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Books } from '../../models/book';
import { BooksService } from '../../service/books.service';

@Component({
  selector: 'app-add-book',
  templateUrl: './add-book.component.html',
  styleUrl: './add-book.component.css'
})
export class AddBookComponent {

  book:Books = new Books();

  constructor(private bookService:BooksService,private router:Router){}

  addBook(){
    this.bookService.addBook(this.book).subscribe(data => {
      console.log(data);
      alert('Book added Successfully');
      this.router.navigate(['admin/books']);
    })
  }

}
