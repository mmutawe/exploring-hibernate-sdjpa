package com.mmutawe.explore.hibernate.sdjpa.hibernate.dao.integrationtests;

import com.mmutawe.explore.hibernate.sdjpa.hibernate.dao.dao.BookDao;
import com.mmutawe.explore.hibernate.sdjpa.hibernate.dao.models.Author;
import com.mmutawe.explore.hibernate.sdjpa.hibernate.dao.models.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ActiveProfiles("local")
@DataJpaTest
@ComponentScan(basePackages = {"com.mmutawe.explore.hibernate.sdjpa.hibernate.dao.dao"})
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
    void testGetBookByTitleNative(){
        Book resultBook = bookDao.getBookByTitleNative("Guide to Pro Dota2, 2th Edition");

        assertThat(resultBook).isNotNull();
    }

    @Test
    void testGetBookByIsbn(){
        Book resultBook = bookDao.getBookByIsbn("123-1234567890");

        assertThat(resultBook).isNotNull();
    }

    @Test
    void testGetAllBooks(){
        List<Book> resultBooks = bookDao.getAllBooks();

        assertThat(resultBooks).isNotNull();
        assertThat(resultBooks.size()).isGreaterThan(0);
    }

    @Test
    void testSaveOneBook(){
        Book book = new Book("01234", "publisher","title",5L);

        Book resultBook = bookDao.saveOneBook(book);

        assertThat(resultBook).isNotNull();
        assertThat(resultBook.getId()).isNotNull();
    }

    @Test
    void testUpdateBook(){

        Book book1 = new Book("01234", "publisher_1","title",5L);
        Book savedBook = bookDao.saveOneBook(book1);

        Book book2 = new Book("01234", "publisher_2","title",5L);
        book2.setId(savedBook.getId());

        Book updatedBook = bookDao.updateBook(book2);

        assertThat(updatedBook).isNotNull();
        assertThat(updatedBook.getPublisher())
                .isEqualTo(book2.getPublisher());
    }

    @Test
    void testDeleteBook(){

        Book book = new Book("012345", "publisher","title",5L);
        Book savedBook = bookDao.saveOneBook(book);

        Long bookId = savedBook.getId();

        bookDao.deleteBook(bookId);
        Book resultBook = bookDao.getBookById(bookId);

        assertThat(resultBook).isNull();
    }
}
