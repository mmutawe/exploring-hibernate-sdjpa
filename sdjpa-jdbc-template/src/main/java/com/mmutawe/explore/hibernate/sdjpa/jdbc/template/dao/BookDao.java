package com.mmutawe.explore.hibernate.sdjpa.jdbc.template.dao;

import com.mmutawe.explore.hibernate.sdjpa.jdbc.template.models.Book;

public interface BookDao {
    Book getBookById(Long id);

    Book getBookByTitle(String title);

    Book saveOneBook(Book book);

    Book updateBook(Book book);

    void deleteBook(Long id);
}
