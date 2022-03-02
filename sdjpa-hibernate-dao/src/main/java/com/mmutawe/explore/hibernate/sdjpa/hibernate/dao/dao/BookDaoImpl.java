package com.mmutawe.explore.hibernate.sdjpa.hibernate.dao.dao;

import com.mmutawe.explore.hibernate.sdjpa.hibernate.dao.models.Book;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class BookDaoImpl implements BookDao {

    private final EntityManagerFactory entityManagerFactory;

    public BookDaoImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public Book getBookById(Long id) {

        EntityManager entityManager = getEntityManager();
        Book book = entityManager.find(Book.class, id);

        entityManager.close();
        return book;
    }

    @Override
    public Book getBookByTitle(String title) {
        EntityManager entityManager = getEntityManager();

//        TypedQuery<Book> typedQuery = entityManager
//                .createQuery("SELECT b FROM Book b WHERE b.title = :title", Book.class);
//        typedQuery.setParameter("title",title);

        TypedQuery<Book> typedQuery = entityManager
                .createNamedQuery("book_retrieve_by_title", Book.class);
        typedQuery.setParameter("title", title);

        Book resBook = typedQuery.getSingleResult();
        entityManager.close();
        return resBook;
    }

    @Override
    public Book getBookByTitleNative(String title) {
        EntityManager entityManager = getEntityManager();

        try {

            Query query = entityManager
                    .createNativeQuery("SELECT * FROM book b WHERE b.title = ?", Book.class);
            query.setParameter(1, title);

            return (Book) query.getSingleResult();

        } finally {
            entityManager.close();
        }
    }


    @Override
    public Book getBookByIsbn(String isbn) {

        EntityManager entityManager = getEntityManager();

        try {
//            Query query = entityManager.createQuery("SELECT b FROM Book b WHERE b.isbn= :isbn");
//            query.setParameter("isbn", isbn);
//            Book resbook = (Book) query.getSingleResult();

            TypedQuery<Book> typedQuery = entityManager
                    .createQuery("SELECT b FROM Book b WHERE b.isbn = :isbn", Book.class);
            typedQuery.setParameter("isbn", isbn);
            Book resBook = typedQuery.getSingleResult();
            return resBook;

        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Book> getAllBooks() {

        EntityManager entityManager = getEntityManager();

        try{

            TypedQuery<Book> typedQuery = entityManager
                    .createNamedQuery("book_retrieve_all", Book.class);

            List<Book> resultBooks = typedQuery.getResultList();

            return resultBooks;

        }finally {
            entityManager.close();
        }
    }

    @Override
    public Book saveOneBook(Book book) {
        EntityManager entityManager = getEntityManager();

        entityManager.getTransaction().begin();
        entityManager.persist(book);
        entityManager.flush();
        entityManager.getTransaction().commit();

        entityManager.close();
        return book;
    }

    @Override
    public Book updateBook(Book book) {

        EntityManager entityManager = getEntityManager();

        entityManager.getTransaction().begin();
        entityManager.merge(book);
        entityManager.flush();
        entityManager.getTransaction().commit();
        Book resBook = entityManager.find(Book.class, book.getId());
        entityManager.close();

        return resBook;
    }

    @Override
    public void deleteBook(Long id) {

        EntityManager entityManager = getEntityManager();

        entityManager.getTransaction().begin();
        Book book = entityManager.find(Book.class, id);
        entityManager.remove(book);
        entityManager.flush();
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    private EntityManager getEntityManager(){
        return entityManagerFactory.createEntityManager();
    }
}
