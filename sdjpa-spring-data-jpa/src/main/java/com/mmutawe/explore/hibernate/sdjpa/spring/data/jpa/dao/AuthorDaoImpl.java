package com.mmutawe.explore.hibernate.sdjpa.spring.data.jpa.dao;

import com.mmutawe.explore.hibernate.sdjpa.spring.data.jpa.models.Author;
import com.mmutawe.explore.hibernate.sdjpa.spring.data.jpa.repositories.AuthorRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Component
public class AuthorDaoImpl implements AuthorDao{

    private final AuthorRepository authorRepository;

    public AuthorDaoImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author getAuthorById(Long id) {
        return authorRepository.getById(id);
    }

    @Override
    public Author getAuthorByFullName(String firstName, String lastName) {
        return authorRepository
                .findAuthorByFirstNameAndLastName(firstName, lastName)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Author saveOneAuthor(Author author) {
        return authorRepository.save(author);
    }

    // * Transactional: This is going to allow the spring data repository actions to work
    //   within a transaction.
    // * By Default Spring data repositories do an implicit transaction; so, if we don't
    //   do transaction, every call to the repository going to be running in its own
    //   transactional context.
    @Override
    @Transactional
    public Author updateAuthor(Author author) {

        try {
            authorRepository.getById(author.getId());
            return authorRepository.save(author);

        } catch (EntityNotFoundException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }
}
