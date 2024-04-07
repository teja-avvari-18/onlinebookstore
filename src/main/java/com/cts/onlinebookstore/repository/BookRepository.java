package com.cts.onlinebookstore.repository;

import com.cts.onlinebookstore.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Long>
{
    @Query("SELECT b FROM Book b WHERE b.authorName LIKE %:authorName%")
    List<Book> findBooksBasedOnAuthorName(String authorName);

    @Query("SELECT b FROM Book b WHERE b.genre LIKE :genre%")
    List<Book> findBooksBasedOnGenre(String genre);

    @Query("SELECT b FROM Book b WHERE b.title LIKE %:title%")
    List<Book> findBooksBasedOnTitle(String title);

    @Query("SELECT b FROM Book b WHERE b.price BETWEEN ?1 AND ?2")
    List<Book> findBooksBasedOnPriceRange(double price1,double price2);
}
