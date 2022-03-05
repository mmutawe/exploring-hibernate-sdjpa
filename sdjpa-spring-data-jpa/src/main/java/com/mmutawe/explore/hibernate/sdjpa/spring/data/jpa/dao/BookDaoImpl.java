package com.mmutawe.explore.hibernate.sdjpa.spring.data.jpa.dao;

import com.mmutawe.explore.hibernate.sdjpa.spring.data.jpa.models.Book;
import com.mmutawe.explore.hibernate.sdjpa.spring.data.jpa.repositories.BookRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookDaoImpl implements BookDao{

    private final BookRepository bookRepository;

    public BookDaoImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book getBookById(Long id) {
        return bookRepository.getById(id);
    }

    @Override
    public Book getBookByTitle(String title) {
        return bookRepository
                .findBookByTitle(title)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Book> getAllBooksByTitle(String title) {
        return bookRepository
                .findAllBooksByTitle(title)
                .collect(Collectors.toList());
    }

    @Override
    public Book getByTitle(String title) {
        return bookRepository.getByTitle(title);
    }

    @Override
    public Book saveOneBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    @Transactional
    public Book updateBook(Book book) {
        try{
            Book currBook = bookRepository.getById(book.getId());
            return bookRepository.save(currBook);
        } catch (EntityNotFoundException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
