package com.mmutawe.explore.hibernate.sdjpa.hibernate.dao.integrationtests;

import com.mmutawe.explore.hibernate.sdjpa.hibernate.dao.dao.AuthorDao;
import com.mmutawe.explore.hibernate.sdjpa.hibernate.dao.models.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ActiveProfiles("local")
@DataJpaTest
@ComponentScan(basePackages = {"com.mmutawe.explore.hibernate.sdjpa.hibernate.dao.dao"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AuthorDaoTest {
    @Autowired
    AuthorDao authorDao;

    @Test
    void testGetAuthorById(){
        Author resultAuthor = authorDao.getAuthorById(1L);

        assertThat(resultAuthor).isNotNull();
    }

    @Test
    void testGetAuthorByFullName(){
        Author resultAuthor = authorDao.getAuthorByFullName("abc","abcd");

        assertThat(resultAuthor).isNotNull();
    }

    @Test
    void testSaveOneAuthor(){
        Author author = new Author("Kuriboh_4","YuGiOh_4");

        Author resultAuthor = authorDao.saveOneAuthor(author);

        assertThat(resultAuthor).isNotNull();
        assertThat(resultAuthor.getId()).isNotNull();
    }

    @Test
    void testUpdateAuthor(){

        Author author1 = new Author("author_fn1","author_ln1");
        Author savedAuthor = authorDao.saveOneAuthor(author1);

        Author author2 = new Author("author_fn2","author_ln2");
        author2.setId(savedAuthor.getId());

        Author updatedAuthor = authorDao.updateAuthor(author2);

        assertThat(updatedAuthor).isNotNull();
        assertThat(updatedAuthor.getFirstName())
                .isEqualTo(author2.getFirstName());
        assertThat(updatedAuthor.getLastName())
                .isEqualTo(author2.getLastName());
    }

    @Test
    void testDeleteAuthor(){

        Author author = new Author("Kuriboh","YuGiOh");
        Author savedAuthor = authorDao.saveOneAuthor(author);

        Long authorId = savedAuthor.getId();

        authorDao.deleteAuthor(authorId);
        Author resultAuthor = authorDao.getAuthorById(authorId);

        assertThat(resultAuthor).isNull();
    }
}
