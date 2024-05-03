import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Books } from '../../models/book';
import { BooksService } from '../../service/books.service';

@Component({
  selector: 'app-adminbooks',
  templateUrl: './adminbooks.component.html',
  styleUrl: './adminbooks.component.css'
})
export class AdminbooksComponent {

  books: Books[] = [];


  constructor(private bookService:BooksService,private router:Router){}

  ngOnInit():void{
    this.bookService.getAllBooks().subscribe((data:Books[])=> {
      this.books = data;
    })
  }


  goToUpdate(id:number){
    this.router.navigate(['books/update',id]);
  }

  deleteBook(id:number){
    this.bookService.deleteBookBasedOnBookID(id).subscribe(data =>{
      alert("Book Deleted Successfully");
      this.router.navigate(['admin']);
    })
  }


  goToAddBook(){
    this.router.navigate(['books/add']);
  }



}
