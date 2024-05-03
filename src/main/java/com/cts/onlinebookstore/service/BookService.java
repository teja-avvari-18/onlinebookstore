package com.cts.onlinebookstore.service;

import com.cts.onlinebookstore.exception.ResourceNotFoundException;
import com.cts.onlinebookstore.model.Book;
import com.cts.onlinebookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService
{

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks()
    {
        List<Book> bookList = bookRepository.findAll();
        if(bookList.isEmpty())
        {
            throw new ResourceNotFoundException("No books are present");
        }
        return bookList;
    }



    public Book getBookById(Long id)
    {
        Optional<Book> book = bookRepository.findById(id);
        try
        {
            return book.get();
        }
        catch (Exception e)
        {
            throw new ResourceNotFoundException("The book you are searching for does not exist");
        }
    }

    public Book addBook(Book book)
    {
        if(book !=null)
        {
                return bookRepository.save(book);
        }
        else
        {
            throw new ResourceNotFoundException("All the book details are not given");
        }


    }

    public void deleteBook(Long id)
    {
        try
        {
            bookRepository.findById(id).get();
            bookRepository.deleteById(id);
        }
        catch (Exception e)
        {
            throw new ResourceNotFoundException("The book with id :"+id+" doesn't exist.");
        }
    }

    public Book  updateBook(Book book)
    {
        try
        {
            Book book1 = bookRepository.findById(book.getId()).get();
            book1.setStockAvailable(book.getStockAvailable());
            book1.setAuthorName(book.getAuthorName());
            book1.setPrice(book.getPrice());
            book1.setTitle(book.getTitle());
            book1.setGenre(book.getGenre());
            book1.setYear(book.getYear());
            return bookRepository.save(book1);
        }
        catch (Exception e)
        {
          throw new ResourceNotFoundException("The book you are trying to update doesn't exist");
        }
    }

    public List<Book> searchBooksBasedOnAuthorName(String authorName)
    {
        List<Book> bookList = bookRepository.findBooksBasedOnAuthorName(authorName);
        if(bookList.isEmpty())
        {
            throw new ResourceNotFoundException("The books you are searching of the author "+authorName+" are not present");
        }
        return bookList;
    }

    public List<Book> searchBooksBasedOnGenre(String genre)
    {
        List<Book> bookList = bookRepository.findBooksBasedOnGenre(genre);
        if(bookList.isEmpty())
        {
            throw new ResourceNotFoundException("There are no books for the genre "+genre);
        }
        return bookList;
    }

    public List<Book> searchBooksBasedOnTitle(String title)
    {
        List<Book> bookList = bookRepository.findBooksBasedOnTitle(title);
        if(bookList.isEmpty())
        {
            throw new ResourceNotFoundException("There are no books based on the title "+title);
        }
        return bookList;
    }

    public List<Book> searchBooksBasedOnPriceRange(double price1,double price2)
    {
        List<Book> bookList = bookRepository.findBooksBasedOnPriceRange(price1,price2);
        if(bookList.isEmpty())
        {
            throw new ResourceNotFoundException("There are no books in the price range you are searching for");
        }
        return bookList;
    }
}
