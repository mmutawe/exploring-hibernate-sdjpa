package com.mmutawe.explore.hibernate.sdjpa.hibernate.dao.dao;


import com.mmutawe.explore.hibernate.sdjpa.hibernate.dao.models.Book;

public interface BookDao {
    Book getBookById(Long id);

    Book getBookByTitle(String title);

    Book saveOneBook(Book book);

    Book updateBook(Book book);

    void deleteBook(Long id);
}
