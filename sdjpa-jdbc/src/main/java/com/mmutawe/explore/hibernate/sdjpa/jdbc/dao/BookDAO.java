package com.mmutawe.explore.hibernate.sdjpa.jdbc.dao;

import com.mmutawe.explore.hibernate.sdjpa.jdbc.models.Book;

public interface BookDAO {
    Book getBookById(Long id);

    Book getBookByTitle(String title);

    Book saveOneBook(Book book);

    Book updateBook(Book book);

    void deleteBook(Long id);
}
