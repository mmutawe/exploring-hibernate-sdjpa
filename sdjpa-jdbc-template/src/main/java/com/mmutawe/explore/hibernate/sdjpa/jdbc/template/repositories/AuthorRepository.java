package com.mmutawe.explore.hibernate.sdjpa.jdbc.template.repositories;

import com.mmutawe.explore.hibernate.sdjpa.jdbc.template.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
