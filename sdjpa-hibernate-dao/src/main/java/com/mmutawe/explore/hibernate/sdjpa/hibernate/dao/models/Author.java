package com.mmutawe.explore.hibernate.sdjpa.hibernate.dao.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@NamedQueries({
        // no need for the SELECT statement
        //@NamedQuery(name = "author_retrieve_all", query = "SELECT a FROM Author a")
        @NamedQuery(name = "author_retrieve_all",
                query = "FROM Author"),
        @NamedQuery(name = "author_retrieve_by_full_name",
                query = "FROM Author a WHERE a.firstName= :first_name AND a.lastName= :last_name")
})
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

    public Author(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
