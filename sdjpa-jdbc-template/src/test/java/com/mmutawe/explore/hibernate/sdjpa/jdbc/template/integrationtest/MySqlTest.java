package com.mmutawe.explore.hibernate.sdjpa.jdbc.template.integrationtest;

import com.mmutawe.explore.hibernate.sdjpa.jdbc.template.repositories.AuthorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ActiveProfiles("local")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MySqlTest {

    @Autowired
    AuthorRepository authorRepository;

    @Test
    void testMySql(){
        long booksCounts = authorRepository.count();
        assertThat(booksCounts).isNotZero();
    }
}
