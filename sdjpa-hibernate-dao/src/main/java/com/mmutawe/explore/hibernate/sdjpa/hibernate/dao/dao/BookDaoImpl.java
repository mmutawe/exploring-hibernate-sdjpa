package com.mmutawe.explore.hibernate.sdjpa.hibernate.dao.dao;

import com.mmutawe.explore.hibernate.sdjpa.hibernate.dao.models.Book;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

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

        TypedQuery<Book> typedQuery = entityManager
                .createQuery("SELECT b FROM Book b WHERE b.title = :title", Book.class);
        typedQuery.setParameter("title",title);
        Book resBook = typedQuery.getSingleResult();
        entityManager.close();
        return resBook;
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
