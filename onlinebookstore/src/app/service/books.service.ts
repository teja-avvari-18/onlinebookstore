import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Books } from '../models/book';

@Injectable({
  providedIn: 'root'
})
export class BooksService {

  baseBooksUrl="http://localhost:8080/books/allbooks";
  baseAddBookUrl = "http://localhost:8080/books/add";
  baseUpdateBookUrl="http://localhost:8080/books/update";
  baseURL="http://localhost:8080/books";

  constructor(private http:HttpClient) { }

  getAllBooks():Observable<Books[]>{
    return this.http.get<Books[]>(`${this.baseBooksUrl}`);
  }

  addBook(book:Books):Observable<Books>{
    return this.http.post<Books>(`${this.baseAddBookUrl}`,book);
  }

  updateBook(book:Books):Observable<Books>{
    return this.http.put<Books>(`${this.baseUpdateBookUrl}`,book);
  }

  getBookBasedOnBookID(id:number):Observable<Books>{
    return this.http.get<Books>(`${this.baseURL}/${id}`);
  }

  deleteBookBasedOnBookID(id:number):Observable<Object> {
    return this.http.delete<Books>(`${this.baseURL}/${id}`);

  }




}
