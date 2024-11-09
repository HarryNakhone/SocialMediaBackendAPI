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
public class PostEntityRepositoryIntergrationTests {

    private PostRepository underTest;

    private UserRepository userRepo;

    @Autowired
    public PostEntityRepositoryIntergrationTests(PostRepository underTest, UserRepository userRepo){
        this.underTest = underTest;
        this.userRepo = userRepo;

    }

    @Test
    public void testThatBookCanBeCreatedAndRecalled(){

        UserEntity user = TestDataUtil.createTestUserB();  // Save user to make sure that it exists in DB
        userRepo.save(user);

        PostEntity postEntity = TestDataUtil.createTestPost(user);  //Assign foreign key to Post
        underTest.save(postEntity);
        Optional<PostEntity> results = underTest.findById(postEntity.getPostId());
        assertThat(results).isPresent();
        assertThat(results.get()).isEqualTo(postEntity);
    }

    @Test
    public void testThatMultipleBooksCanBeCreatedAndRecalled(){
        UserEntity user1 = TestDataUtil.createTestUserB();
        userRepo.save(user1);
        UserEntity user2 = TestDataUtil.createTestUserD();
        userRepo.save(user2);
        UserEntity user3 = TestDataUtil.createTestUserE();
        userRepo.save(user3);                                              // Save user to make sure that it exists in DB

        PostEntity postEntity1 =   TestDataUtil.createTestPost(user1);

        underTest.save(postEntity1);

        PostEntity postEntity2 =   TestDataUtil.createTestPost2(user2);

        underTest.save(postEntity2);

        PostEntity postEntity3 =   TestDataUtil.createTestPost3(user3);    //Assign foreign key to Post

        underTest.save(postEntity3);
        Iterable <PostEntity> results = underTest.findAll();
        assertThat(results).hasSize(3).containsExactly(postEntity1, postEntity2, postEntity3 );
    }

    @Test
    public void testThatBookCanBeUpdateAndCanBeRecalled(){
        UserEntity user1 =  TestDataUtil.createTestUserB();
        userRepo.save(user1);
        PostEntity postEntity1 = TestDataUtil.createTestPost(user1);
        underTest.save(postEntity1);
        postEntity1.setTitle("MuayKuay");

        underTest.save(postEntity1);
        Optional<PostEntity> results = underTest.findById(postEntity1.getPostId());
        assertThat(results).isPresent();
        assertThat(results.get()).isEqualTo(postEntity1);

    }

    @Test
    public void testThatBookCanBeDeleted(){
        UserEntity user1 = TestDataUtil.createTestUserB();
        userRepo.save(user1);
        PostEntity postEntity1 = TestDataUtil.createTestPost(user1);

        underTest.save(postEntity1);
        underTest.deleteById(postEntity1.getPostId());
        Optional<PostEntity> results = underTest.findById(postEntity1.getPostId());
        assertThat(results).isEmpty();


    }


}
