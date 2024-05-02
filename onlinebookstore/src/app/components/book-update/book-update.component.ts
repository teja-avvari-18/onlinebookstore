import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Books } from '../../models/book';
import { BooksService } from '../../service/books.service';

@Component({
  selector: 'app-book-update',
  templateUrl: './book-update.component.html',
  styleUrl: './book-update.component.css'
})
export class BookUpdateComponent {

  books: Books= new Books();

  bookId!:number;

  constructor(private bookservice:BooksService,private route:ActivatedRoute,private router:Router){}

 

  ngOnInit(): void {
    this.bookId=this.route.snapshot.params['id'];
    this.bookservice.getBookBasedOnBookID(this.bookId).subscribe(data => {
      this.books = data;
    })
  }

  updateBook(){
    this.bookservice.updateBook(this.books).subscribe(data => {
      this.books = data;
      console.log(data);
      this.router.navigate(['admin/books']);
    });
  }


  

}
