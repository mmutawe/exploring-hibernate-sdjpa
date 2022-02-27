package com.mmutawe.explore.hibernate.sdjpa.jdbc.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Book {

    @Id
    private Long id;

    private String isbn;
    private String publisher;
    private Long authorId;

    public Book(String isbn, String publisher, Long authorId) {
        this.isbn = isbn;
        this.publisher = publisher;
        this.authorId = authorId;
    }
}
