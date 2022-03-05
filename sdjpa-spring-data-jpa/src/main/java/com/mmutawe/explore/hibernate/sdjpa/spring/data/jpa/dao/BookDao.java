package com.mmutawe.explore.hibernate.sdjpa.spring.data.jpa.dao;

import com.mmutawe.explore.hibernate.sdjpa.spring.data.jpa.models.Book;

import java.util.List;

public interface BookDao {
    Book getBookById(Long id);

    Book getBookByTitle(String title);

    Book getByTitle(String title);

    List<Book> getAllBooksByTitle(String title);

    Book saveOneBook(Book book);

    Book updateBook(Book book);

    void deleteBook(Long id);
}