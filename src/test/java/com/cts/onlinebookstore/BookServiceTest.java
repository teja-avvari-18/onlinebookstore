package com.cts.onlinebookstore;



import com.cts.onlinebookstore.exception.ResourceNotFoundException;
import com.cts.onlinebookstore.model.Book;
import com.cts.onlinebookstore.repository.BookRepository;
import com.cts.onlinebookstore.service.BookService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@SpringBootTest
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllBooks() {
        List<Book> bookList = new ArrayList<>();
        when(bookRepository.findAll()).thenReturn(bookList);

        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            bookService.getAllBooks();
        });
    }

    @Test
    public void testGetBookById() {
        Long bookId = 1L;
        Book book = new Book();
        book.setId(bookId);
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));

        Book foundBook = bookService.getBookById(bookId);

        Assertions.assertEquals(book, foundBook);
    }



    @Test
    public void testUpdateBook_ExistingBook() {
        Long bookId = 1L;
        Book existingBook = new Book();
        existingBook.setId(bookId);
        existingBook.setTitle("Existing Book");
        existingBook.setAuthorName("Existing Author");

        when(bookRepository.findById(bookId)).thenReturn(Optional.of(existingBook));
        when(bookRepository.save(any(Book.class))).thenReturn(existingBook);

        existingBook.setTitle("Updated Title");
        existingBook.setAuthorName("Updated Author");

        Book updatedBook = bookService.updateBook(existingBook);

        Assertions.assertEquals(existingBook.getId(), updatedBook.getId());
        Assertions.assertEquals(existingBook.getTitle(), updatedBook.getTitle());
        Assertions.assertEquals(existingBook.getAuthorName(), updatedBook.getAuthorName());
    }

    @Test
    public void testDeleteBook_ExistingBook() {
        Long bookId = 1L;
        Book existingBook = new Book();
        existingBook.setId(bookId);

        when(bookRepository.findById(bookId)).thenReturn(Optional.of(existingBook));

        bookService.deleteBook(bookId);

        verify(bookRepository, times(1)).deleteById(bookId);
    }




}