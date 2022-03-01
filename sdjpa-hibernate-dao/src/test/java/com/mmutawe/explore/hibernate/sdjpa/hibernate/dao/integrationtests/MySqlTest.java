package com.mmutawe.explore.hibernate.sdjpa.hibernate.dao.integrationtests;

import com.mmutawe.explore.hibernate.sdjpa.hibernate.dao.repositories.AuthorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@ActiveProfiles("local")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MySqlTest {

    @Autowired
    AuthorRepository authorRepository;

    @Test
    void testMySql(){
        long authorsCounts = authorRepository.count();
        assertThat(authorsCounts).isNotZero();
    }
}
