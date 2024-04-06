package com.cts.onlinebookstore.controller;


import com.cts.onlinebookstore.model.Book;
import com.cts.onlinebookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController
{

    @Autowired
    private BookService bookService;

    @GetMapping("/allbooks")
    public ResponseEntity<List<Book>> getAllBooks()
    {
        List<Book> bookList = bookService.getAllBooks();
        return new ResponseEntity<>(bookList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id)
    {
        Book book = bookService.getBookById(id);
        return new ResponseEntity<>(book,HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Book> addBook(@RequestBody Book bookObj)
    {
        Book book = bookService.addBook(bookObj);
        return new ResponseEntity<>(book,HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id)
    {
        bookService.deleteBook(id);
    }

    @PutMapping("/update")
    public ResponseEntity<Book> updateBook(@RequestBody Book bookObj)
    {
        Book book = bookService.updateBook(bookObj);
        return new ResponseEntity<>(book,HttpStatus.OK);
    }
}
