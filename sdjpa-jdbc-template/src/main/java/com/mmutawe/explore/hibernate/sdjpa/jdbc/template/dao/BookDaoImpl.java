package com.mmutawe.explore.hibernate.sdjpa.jdbc.template.dao;

import com.mmutawe.explore.hibernate.sdjpa.jdbc.template.models.Book;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class BookDaoImpl implements BookDao{

    private final JdbcTemplate jdbcTemplate;

    public BookDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Book getBookById(Long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM book WHERE id = ?", getRowMapper(), id);
    }

    @Override
    public Book getBookByTitle(String title) {
        return jdbcTemplate.queryForObject("SELECT * FROM book WHERE title = ?", getRowMapper(), title);
    }

    @Override
    public Book saveOneBook(Book book) {

        jdbcTemplate.update(
                "INSERT INTO book(isbn,publisher, title, author_id) VALUES(?,?,?,?)",
                book.getIsbn(), book.getPublisher(), book.getTitle(), book.getAuthorId());

        Long savedBookId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);

        return this.getBookById(savedBookId);
    }

    @Override
    public Book updateBook(Book book) {
        jdbcTemplate.update(
                "UPDATE book SET isbn=?, publisher=?, title=?, author_id=? WHERE id=?",
                book.getIsbn(), book.getPublisher(), book.getTitle(), book.getAuthorId(), book.getId());

        return this.getBookById(book.getId());
    }

    @Override
    public void deleteBook(Long id) {
        jdbcTemplate.update("DELETE FROM book WHERE id = ?", id);
    }

    private RowMapper<Book> getRowMapper(){
        return new BookMapper();
    }
}
