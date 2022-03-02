package com.mmutawe.explore.hibernate.sdjpa.hibernate.dao.dao;

import com.mmutawe.explore.hibernate.sdjpa.hibernate.dao.models.Author;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

@Component
public class AuthorDaoImpl implements AuthorDao {

    private final EntityManagerFactory entityManagerFactory;

    public AuthorDaoImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public Author getAuthorById(Long id) {
        EntityManager entityManager = getEntityManager();
        Author author = entityManager.find(Author.class, id);
        entityManager.close();
        return author;
    }

    @Override
    public Author getAuthorByFullName(String firstName, String lastName) {

        EntityManager entityManager = getEntityManager();
//        TypedQuery<Author> typedQuery = entityManager
//                .createQuery("SELECT a FROM Author a " +
//                        "WHERE a.firstName = :first_name and a.lastName= :last_name", Author.class);
        TypedQuery<Author> typedQuery = entityManager
                .createNamedQuery("author_retrieve_by_full_name", Author.class);
        typedQuery.setParameter("first_name", firstName);
        typedQuery.setParameter("last_name", lastName);
        Author resAuthor = typedQuery.getSingleResult();
        entityManager.close();
        return resAuthor;
    }

    @Override
    public Author getAuthorByFullNameCriteria(String firstName, String lastName) {

        EntityManager entityManager = getEntityManager();

        try {

            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Author> criteriaQuery = criteriaBuilder.createQuery(Author.class);

            Root<Author> root = criteriaQuery.from(Author.class);

            ParameterExpression<String> firstNameParam = criteriaBuilder.parameter(String.class);
            ParameterExpression<String> lastNameParam = criteriaBuilder.parameter(String.class);

            Predicate firstNamePredicate = criteriaBuilder.equal(root.get("firstName"), firstNameParam);
            Predicate lastNamePredicate = criteriaBuilder.equal(root.get("lastName"), lastNameParam);

            criteriaQuery.select(root).where(criteriaBuilder.and(firstNamePredicate, lastNamePredicate));

            TypedQuery<Author> typedQuery = entityManager.createQuery(criteriaQuery);
            typedQuery.setParameter(firstNameParam, firstName);
            typedQuery.setParameter(lastNameParam, lastName);

            return typedQuery.getSingleResult();

        } finally {
            entityManager.close();
        }
    }

        @Override
    public List<Author> getAllAuthors() {

        EntityManager entityManager = getEntityManager();
        try {

            TypedQuery<Author> typedQuery = entityManager
                    .createNamedQuery("author_retrieve_all", Author.class);

            return typedQuery.getResultList();

        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Author> getAuthorsByLastNameLike(String lastName) {

        EntityManager entityManager = getEntityManager();

        try {
            Query query = entityManager
                    .createQuery("SELECT a FROM Author a WHERE a.lastName LIKE :last_name");
            query.setParameter("last_name", "%" + lastName + "%");
            List<Author> authors = query.getResultList();

            return authors;

        } finally {
            entityManager.close();
        }
    }

    @Override
    public Author saveOneAuthor(Author author) {
        EntityManager entityManager = getEntityManager();

//        entityManager.joinTransaction();
        entityManager.getTransaction().begin();

        entityManager.persist(author); // by default the author will get the id updated
        entityManager.flush(); // this will force hibernate to flush the transactions to the db

        entityManager.getTransaction().commit();

        entityManager.close();
        return author;
    }

    @Override
    public Author updateAuthor(Author author) {
        EntityManager entityManager = getEntityManager();

        entityManager.getTransaction().begin();
        entityManager.merge(author); // merge by itself, will make the update in the hibernate context but not the db
        entityManager.flush();
        entityManager.getTransaction().commit();
        Author resAuthor = entityManager.find(Author.class, author.getId());

        entityManager.close();
        return resAuthor;
    }

    @Override
    public void deleteAuthor(Long id) {
        EntityManager entityManager = getEntityManager();

        entityManager.getTransaction().begin();
        Author author = entityManager.find(Author.class, id);
        entityManager.remove(author);
        entityManager.flush();
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    private EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }
}
