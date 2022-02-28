package com.mmutawe.explore.hibernate.sdjpa.jdbc.template.integrationtest;

import com.mmutawe.explore.hibernate.sdjpa.jdbc.template.dao.BookDao;
import com.mmutawe.explore.hibernate.sdjpa.jdbc.template.models.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ActiveProfiles("local")
@DataJpaTest
@ComponentScan(basePackages = {"com.mmutawe.explore.hibernate.sdjpa.jdbc.template.dao"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookDaoTest {

    @Autowired
    BookDao bookDao;

    @Test
    void testGetBookById(){
        Book resultBook = bookDao.getBookById(1L);

        assertThat(resultBook).isNotNull();
    }

    @Test
    void testGetBookByTitle(){
        Book resultBook = bookDao.getBookByTitle("Guide to Pro Dota2, 2th Edition");

        assertThat(resultBook).isNotNull();
    }

    @Test
    void testSaveOneBook(){
        Book book = new Book("123-1234567898", "Meepo3 W33haa", "Guide to Pro Dota2, 3rd Edition",3L);

        Book resultBook = bookDao.saveOneBook(book);

        assertThat(resultBook).isNotNull();
    }

    @Test
    void testUpdateAuthor(){

        Book book1 = new Book("123-1234567899", "Meepo3 W33haa", "Guide to Pro Dota2, 4rd Edition",3L);
        Book savedBook = bookDao.saveOneBook(book1);

        Book book2 = new Book("123-1234567898", "Meepo4 W33haa", "Guide to Pro Dota2, 3rd Edition",3L);
        book2.setId(savedBook.getId());

        Book updatedBook = bookDao.updateBook(book2);

        assertThat(updatedBook).isNotNull();
        assertThat(updatedBook.getPublisher())
                .isEqualTo(book2.getPublisher());
    }

    @Test
    void testDeleteAuthor(){

        Book book = new Book();
        Book savedBook = bookDao.saveOneBook(book);

        Long bookId = savedBook.getId();

        bookDao.deleteBook(bookId);
        assertThrows(EmptyResultDataAccessException.class, () -> bookDao.getBookById(bookId));
    }
}
