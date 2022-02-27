package com.mmutawe.explore.hibernate.sdjpa.jdbc.dao;

import com.mmutawe.explore.hibernate.sdjpa.jdbc.models.Author;

public interface AuthorDao {
    Author getAuthorById(Long id);

    Author getAuthorByFullName(String firstName, String lastName);

    Author saveOneAuthor(Author author);

    Author updateAuthor(Author author);

    void deleteAuthor(Long id);
}
