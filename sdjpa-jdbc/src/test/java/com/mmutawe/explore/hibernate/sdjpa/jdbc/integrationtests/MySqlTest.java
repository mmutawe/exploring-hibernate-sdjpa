package com.mmutawe.explore.hibernate.sdjpa.jdbc.integrationtests;

import com.mmutawe.explore.hibernate.sdjpa.jdbc.repositories.BookRepository;
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
    BookRepository bookRepository;

    @Test
    void testMySql(){
        long booksCounts = bookRepository.count();
        assertThat(booksCounts).isNotZero();
    }
}
