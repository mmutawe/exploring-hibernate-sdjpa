package com.mmutawe.explore.hibernate.sdjpa.spring.data.jpa.repositories;

import com.mmutawe.explore.hibernate.sdjpa.spring.data.jpa.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findAuthorByFirstNameAndLastName(String firstName, String lastName);
}
