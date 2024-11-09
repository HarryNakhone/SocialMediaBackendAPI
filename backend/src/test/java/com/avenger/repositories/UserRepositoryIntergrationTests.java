package com.avenger.repositories;

import com.avenger.*;

import com.avenger.domains.entity.*;
import com.avenger.repositories.*;
import jakarta.transaction.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.test.annotation.*;
import org.springframework.test.context.junit.jupiter.*;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
public class UserRepositoryIntergrationTests {

    private UserRepository underTest;

    @Autowired
    public UserRepositoryIntergrationTests(UserRepository underTest){
        this.underTest = underTest;

    }
    @Test

    public void testThatAuthorCanBeCreatedAndRecalled(){
        UserEntity author = TestDataUtil.createTestUserB();
        underTest.save(author);
        Optional<UserEntity> result = underTest.findById(author.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(author);
    }

    @Test
    public void testThatMultipleAuthorCanBeCreatedAndRecalled(){
        UserEntity author1 = TestDataUtil.createTestUserB();
        underTest.save(author1);

        UserEntity author2 = TestDataUtil.createTestUserD();
        underTest.save(author2);
        UserEntity author3 = TestDataUtil.createTestUserE();
        underTest.save(author3);
        Iterable<UserEntity> result = underTest.findAll();
        assertThat(result)
                .hasSize(3).
                containsExactly(author1, author2, author3);
    }

    @Test
    public void testThatTheAuthoorsCanBeUpdatedAndRecalled(){
        UserEntity author1 = TestDataUtil.createTestUserB();
        underTest.save(author1);
        author1.setName("Herrikenz");
        underTest.save(author1);
        Optional<UserEntity> results = underTest.findById(author1.getId());
        assertThat(results).isPresent();
        assertThat(results.get()).isEqualTo(author1);
    }

    @Test
    public void testThatTheAuthorsCanBeDeleted(){
        UserEntity author = TestDataUtil.createTestUserB();
        underTest.save(author);
        underTest.deleteById(author.getId());
        Optional<UserEntity> results = underTest.findById(author.getId());
        assertThat(results).isEmpty();

    }

}
