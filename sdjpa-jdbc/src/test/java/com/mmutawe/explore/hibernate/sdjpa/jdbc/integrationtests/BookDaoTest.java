package com.mmutawe.explore.hibernate.sdjpa.jdbc.integrationtests;

import com.mmutawe.explore.hibernate.sdjpa.jdbc.dao.BookDAO;
import com.mmutawe.explore.hibernate.sdjpa.jdbc.models.Author;
import com.mmutawe.explore.hibernate.sdjpa.jdbc.models.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ActiveProfiles("local")
@DataJpaTest
@ComponentScan(basePackages = {"com.mmutawe.explore.hibernate.sdjpa.jdbc.dao"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookDaoTest {
    @Autowired
    BookDAO bookDao;

    @Test
    void testGetBookById(){
        Book resultBook = bookDao.getBookById(1L);

        assertThat(resultBook).isNotNull();
    }

    @Test
    void testGetBookByTitle(){
        Book resultBook = bookDao.getBookByTitle("How to win Ti, 1st Edition");

        assertThat(resultBook).isNotNull();
    }

    @Test
    void testSaveOneBook(){
        Book book = new Book("123-1234567895", "Meepo2 W33haa", "GG,WP in 20 min", null);

        Book resultBook = bookDao.saveOneBook(book);

        assertThat(resultBook).isNotNull();
    }

    @Test
    void testUpdateBook(){

        Author author1 = new Author();
        author1.setId(1L);
        Book book1 = new Book("123-1234567896", "Meepo2 W33haa", "GG,WP in 20 min, 2nd Edition", author1);
        Book savedBook = bookDao.saveOneBook(book1);

        Book book2 = new Book("123-1234567896", "Meepo1 W33haa", "GG,WP in 20 min, 2nd Edition", author1);
        book2.setId(savedBook.getId());

        Book updatedBook = bookDao.updateBook(book2);

        assertThat(updatedBook).isNotNull();
        assertThat(updatedBook.getPublisher())
                .isEqualTo(book2.getPublisher());
    }

    @Test
    void testDeleteBook(){

        Book book = new Book("123-1234567897", "Meepo3 W33haa", "Tower deleted, 4th Edition", null);
        Book savedBook = bookDao.saveOneBook(book);

        Long bookId = savedBook.getId();

        bookDao.deleteBook(bookId);
        Book resultBook = bookDao.getBookById(bookId);

        assertThat(resultBook).isNull();
    }

}
