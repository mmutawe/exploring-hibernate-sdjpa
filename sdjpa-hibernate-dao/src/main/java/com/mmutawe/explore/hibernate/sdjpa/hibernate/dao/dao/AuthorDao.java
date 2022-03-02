package com.mmutawe.explore.hibernate.sdjpa.hibernate.dao.dao;


import com.mmutawe.explore.hibernate.sdjpa.hibernate.dao.models.Author;

import java.util.List;

public interface AuthorDao {
    Author getAuthorById(Long id);

    Author getAuthorByFullName(String firstName, String lastName);

    List<Author> getAllAuthors();

    List<Author> getAuthorsByLastNameLike(String lastName);

    Author getAuthorByFullNameCriteria(String firstName, String lastName);

    Author saveOneAuthor(Author author);

    Author updateAuthor(Author author);

    void deleteAuthor(Long id);
}
