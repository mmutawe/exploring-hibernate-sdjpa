package com.mmutawe.explore.hibernate.sdjpa.jdbc.template.dao;

import com.mmutawe.explore.hibernate.sdjpa.jdbc.template.models.Author;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class AuthorDaoImpl implements AuthorDao{

    private final JdbcTemplate jdbcTemplate;

    public AuthorDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Author getAuthorById(Long id) {

//        return jdbcTemplate.query(
//                "SELECT author.id AS id, first_name, last_name, " +
//                        "book.id AS book_id, book.isbn, book.publisher, book.title " +
//                        "FROM author " +
//                        "LEFT OUTER JOIN book ON author.id = book.author_id " +
//                        "WHERE author.id = ?",
//                new AuthorExtractor(),
//                id);
        String sql = "select author.id as id, first_name, last_name, book.id as book_id, book.isbn, book.publisher, book.title from author\n" +
                "left outer join book on author.id = book.author_id where author.id = ?";

        return jdbcTemplate.query(sql, new AuthorExtractor(), id);
    }

    /*
    @Override
    public Author getAuthorById(Long id) {

//        try {
            return jdbcTemplate.queryForObject("SELECT * FROM author WHERE id = ?", getRowMapper(), id);
//        } catch (EmptyResultDataAccessException e){
//            e.printStackTrace();
//            return null;
//        }
    }
    */
    @Override
    public Author getAuthorByFullName(String firstName, String lastName) {

        return jdbcTemplate.queryForObject(
                "SELECT * FROM author WHERE first_name = ? AND last_name = ?",
                getRowMapper(),
                firstName, lastName);
    }

    @Override
    public Author saveOneAuthor(Author author) {

        jdbcTemplate.update(
                "INSERT INTO author(first_name, last_name) VALUES(?, ?)",
                author.getFirstName(), author.getLastName());

        Long createdId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
        return this.getAuthorById(createdId);
    }

    @Override
    public Author updateAuthor(Author author) {

        jdbcTemplate.update(
                "UPDATE author SET first_name = ?, last_name = ? WHERE id = ?",
                author.getFirstName(), author.getLastName(), author.getId());

        return this.getAuthorById(author.getId());
    }

    @Override
    public void deleteAuthor(Long id) {
        jdbcTemplate.update("DELETE FROM author WHERE id = ?", id);
    }

    private RowMapper<Author> getRowMapper(){
        return new AuthorMapper();
    }
}
