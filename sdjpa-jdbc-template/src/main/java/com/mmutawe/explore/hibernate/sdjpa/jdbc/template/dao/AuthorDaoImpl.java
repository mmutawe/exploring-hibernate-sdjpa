package com.mmutawe.explore.hibernate.sdjpa.jdbc.template.dao;

import com.mmutawe.explore.hibernate.sdjpa.jdbc.template.models.Author;
import org.springframework.stereotype.Component;

@Component
public class AuthorDaoImpl implements AuthorDao{
    @Override
    public Author getAuthorById(Long id) {
        return null;
    }

    @Override
    public Author getAuthorByFullName(String firstName, String lastName) {
        return null;
    }

    @Override
    public Author saveOneAuthor(Author author) {
        return null;
    }

    @Override
    public Author updateAuthor(Author author) {
        return null;
    }

    @Override
    public void deleteAuthor(Long id) {

    }
}
