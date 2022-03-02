package com.mmutawe.explore.hibernate.sdjpa.hibernate.dao.dao;


import com.mmutawe.explore.hibernate.sdjpa.hibernate.dao.models.Book;

import java.util.List;

public interface BookDao {
    Book getBookById(Long id);

    Book getBookByTitle(String title);

    Book getBookByTitleNative(String title);

    Book getBookByIsbn(String isbn);

    List<Book> getAllBooks();

    Book saveOneBook(Book book);

    Book updateBook(Book book);

    void deleteBook(Long id);
}
