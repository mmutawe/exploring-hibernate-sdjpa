package com.mmutawe.explore.hibernate.sdjpa.jdbc.template.dao;

import com.mmutawe.explore.hibernate.sdjpa.jdbc.template.models.Author;
import com.mmutawe.explore.hibernate.sdjpa.jdbc.template.models.Book;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AuthorMapper implements RowMapper {
    @Override
    public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
        rs.next();

        Author author = new Author();
        author.setId(rs.getLong("id"));
        author.setFirstName(rs.getString("first_name"));
        author.setLastName(rs.getString("last_name"));

        if (rs.getString("isbn") != null){
            author.setBooks(new ArrayList<>());
            author.getBooks().add(mapBook(rs));
        }

        while (rs.next()){
            author.getBooks().add(mapBook(rs));
        }

        return author;
    }

    private Book mapBook(ResultSet rs) {
        Book book = new Book();
        try {
            book.setAuthorId(rs.getLong(1));
            book.setIsbn(rs.getString(5));
            book.setTitle(rs.getString(7));
            book.setAuthorId(rs.getLong(1));
            book.setPublisher(rs.getString(6));

            return book;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
