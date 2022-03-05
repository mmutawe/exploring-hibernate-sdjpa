package com.mmutawe.explore.hibernate.sdjpa.spring.data.jpa.repositories;

import com.mmutawe.explore.hibernate.sdjpa.spring.data.jpa.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;
import org.springframework.scheduling.annotation.Async;

import java.util.Optional;
import java.util.concurrent.Future;
import java.util.stream.Stream;

public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findBookByTitle(String title);

    @Nullable
    Book getByTitle(String title);

    Stream<Book> findAllBooksByTitle(String title);

    @Async
    Future<Book> queryByTitle(String title);

    @Query("SELECT b FROM Book b WHERE b.title = ?1")
    Book findBookByTitleWithQuery(String title);

    @Query("SELECT b FROM Book b WHERE b.title = :title")
    Book findBookByTitleWithQueryUsingNameParam(@Param("title") String title);

    @Query(value = "SELECT * FROM book WHERE title = ?1", nativeQuery = true)
    Book findBookByTitleWithNativeQuery(String title);


    Book findBookByTitleUsingJpaNamedQuery(@Param("title") String title);
}
