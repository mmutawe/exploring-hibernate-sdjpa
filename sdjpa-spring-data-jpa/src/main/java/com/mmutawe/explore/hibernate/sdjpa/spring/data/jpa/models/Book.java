package com.mmutawe.explore.hibernate.sdjpa.spring.data.jpa.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@NamedQuery(name= "Book.findBookByTitleUsingJpaNamedQuery",
        query = "SELECT b FROM Book b WHERE b.title = :title"
)
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String isbn;
    private String publisher;
    private String title;
    private Long authorId;

    public Book(String isbn, String publisher, String title, Long authorId) {
        this.isbn = isbn;
        this.publisher = publisher;
        this.title = title;
        this.authorId = authorId;
    }
}
