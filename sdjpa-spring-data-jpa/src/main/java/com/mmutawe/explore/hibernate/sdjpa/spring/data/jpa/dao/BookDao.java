package com.mmutawe.explore.hibernate.sdjpa.spring.data.jpa.dao;

import com.mmutawe.explore.hibernate.sdjpa.spring.data.jpa.models.Book;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookDao {
    Book getBookById(Long id);

    Book getBookByTitle(String title);

    Book getByTitle(String title);

    List<Book> getAllBooksByTitle(String title);

    List<Book> getAllBooks();

    List<Book> getAllBooks(Pageable pageable);

    List<Book> getAllBooks(int pageSize, int offset);

    List<Book> getAllBooksSortedByTitle(int page, int size, SortType sortType);


    Book saveOneBook(Book book);

    Book updateBook(Book book);

    void deleteBook(Long id);
}