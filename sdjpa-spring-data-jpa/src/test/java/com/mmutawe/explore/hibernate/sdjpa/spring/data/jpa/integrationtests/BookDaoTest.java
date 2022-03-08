package com.mmutawe.explore.hibernate.sdjpa.spring.data.jpa.integrationtests;

import com.mmutawe.explore.hibernate.sdjpa.spring.data.jpa.dao.BookDao;
import com.mmutawe.explore.hibernate.sdjpa.spring.data.jpa.dao.SortType;
import com.mmutawe.explore.hibernate.sdjpa.spring.data.jpa.models.Book;
import com.mmutawe.explore.hibernate.sdjpa.spring.data.jpa.repositories.BookRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.PageRequest;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.EntityNotFoundException;

import java.util.List;
import java.util.concurrent.Future;

import static com.mmutawe.explore.hibernate.sdjpa.spring.data.jpa.dao.SortType.ASC;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ActiveProfiles("local")
@DataJpaTest
@ComponentScan(basePackages = {"com.mmutawe.explore.hibernate.sdjpa.spring.data.jpa.dao"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookDaoTest {

    @Autowired
    BookDao bookDao;

    @Autowired
    BookRepository bookRepository;

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
    void testGetByTitleEmptyResult(){
        Book resultBook = bookDao.getByTitle("NOT_AVAILABLE");
        assertThat(resultBook).isNull();
    }

    @Test
    void testGetBookByTitleNotFound(){
        assertThrows(EntityNotFoundException.class,
                () -> bookDao.getBookByTitle("NOT_FOUND"));
    }

    @Test
    void TestGetAllBooksByTitle(){
        List<Book> books = bookDao.getAllBooksByTitle("GG,WP in 20 min");
        assertThat(books.size()).isGreaterThan(1);
    }

    @Test
    @SneakyThrows
    void TestGetFutureBooksByTitle(){
        Future<Book> bookFuture = bookRepository.queryByTitle("How to win Ti, 1st Edition");

        Book book = bookFuture.get();

        assertNotNull(book);
    }

    @Test
    void TestFindBookByTitleWithQuery(){
        Book book = bookRepository.findBookByTitleWithQuery("How to win Ti, 1st Edition");
        assertThat(book).isNotNull();
    }

    @Test
    void TestFindBookByTitleWithQueryUsingNameParam(){
        Book book = bookRepository.findBookByTitleWithQueryUsingNameParam("How to win Ti, 1st Edition");
        assertThat(book).isNotNull();
    }

    @Test
    void TestFindBookByTitleUsingJpaNamedQuery(){
        Book book = bookRepository.findBookByTitleUsingJpaNamedQuery("How to win Ti, 1st Edition");
        assertThat(book).isNotNull();
    }

    @Test
    void TestFindBookByTitleWithNativeQuery(){
        Book book = bookRepository.findBookByTitleWithNativeQuery("How to win Ti, 1st Edition");
        assertThat(book).isNotNull();
    }

    @Test
    void TestGetAllBooks(){
        List<Book> books = bookDao.getAllBooks();

        assertThat(books).isNotNull();
        assertThat(books.size()).isGreaterThan(1);
    }

    @Test
    void TestGetAllBooksWithPageable(){
        List<Book> books = bookDao.getAllBooks(PageRequest.of(1,3));

        assertThat(books).isNotNull();
        assertThat(books.size()).isEqualTo(3);
    }

    @Test
    void TestGetAllBooksWithPageAndOffset(){
        List<Book> books = bookDao.getAllBooks(3,3);

        assertThat(books).isNotNull();
        assertThat(books.size()).isGreaterThan(1);
    }

    @Test
    void TestGetAllBooksSortedByTitle(){
        List<Book> books = bookDao.getAllBooksSortedByTitle(1,5, ASC);

        assertThat(books).isNotNull();
        assertThat(books.size()).isEqualTo(5);
    }

    @Test
    void testSaveOneBook(){
        Book book = new Book("01234567899","Dark Magician", "EZ WIN", 4L);

        Book resultBook = bookDao.saveOneBook(book);

        assertThat(resultBook).isNotNull();
    }

    @Test
    void testUpdateBook(){

        Book book1 = new Book(); // add book desc
        Book savedBook = bookDao.saveOneBook(book1);

        Book book2 = new Book(); // add book desc
        book2.setId(savedBook.getId());

        Book updatedBook = bookDao.updateBook(book2);

        assertThat(updatedBook).isNotNull();
        assertThat(updatedBook.getPublisher())
                .isEqualTo(book2.getPublisher());
    }

    @Test
    void testDeleteBook(){

        Book book = new Book(); // add book desc
        Book savedBook = bookDao.saveOneBook(book);

        Long bookId = savedBook.getId();
        bookDao.deleteBook(bookId);

        assertThrows(JpaObjectRetrievalFailureException.class,
                () -> bookDao.getBookById(bookId));
    }

}
