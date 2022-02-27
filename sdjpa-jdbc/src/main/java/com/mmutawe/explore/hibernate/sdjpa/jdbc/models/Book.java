package com.mmutawe.explore.hibernate.sdjpa.jdbc.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

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
    private String title;
//    private Long authorId;
    @Transient
    private Author author;

    public Book(String isbn, String publisher, String title, Author author) {
        this.isbn = isbn;
        this.publisher = publisher;
        this.title = title;
        this.author = author;
    }
}
