package com.avenger.repositories;


import com.avenger.*;
import com.avenger.domains.entity.*;
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
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD )
public class CommentEntityRepositoryIntergrationTest {

    private CommentRepository commentRepo;

    private PostRepository postRepo;

    private UserRepository userRepo;

    @Autowired
    public CommentEntityRepositoryIntergrationTest(CommentRepository commentRepo, PostRepository postRepo, UserRepository userRepo){
        this.commentRepo = commentRepo;
        this.postRepo = postRepo;
        this.userRepo = userRepo;
    }

    @Test
    @Transactional
    public void testThatCommentCanBeCreatedAndRecalled(){
        UserEntity userEntity = TestDataUtil.createTestUserB();
        userRepo.save(userEntity);

        PostEntity postEntity = TestDataUtil.createTestPost(userEntity);
        postRepo.save(postEntity);

        CommentsEntity commentEntity = TestDataUtilLikesAndComment.createTestCommentA(userEntity, postEntity);
        commentRepo.save(commentEntity);
        Optional<CommentsEntity> result = commentRepo.findById(commentEntity.getCommentId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(commentEntity);
    }

    @Test
    @Transactional
    public void testThatMultipleCommentThatBeCreatedAndRecalled(){
        UserEntity userEntity1 = TestDataUtil.createTestUserB();
        userRepo.save(userEntity1);

        PostEntity postEntity1 = TestDataUtil.createTestPost(userEntity1);
        postRepo.save(postEntity1);

        UserEntity userEntity2 = TestDataUtil.createTestUserD();
        userRepo.save(userEntity2);

        UserEntity userEntity3 = TestDataUtil.createTestUserE();
        userRepo.save(userEntity3);


        PostEntity postEntity2 = TestDataUtil.createTestPost2(userEntity3);
        postRepo.save(postEntity2);


        CommentsEntity commentsEntity1 = TestDataUtilLikesAndComment.createTestCommentA(userEntity1, postEntity1);
        commentRepo.save(commentsEntity1);
        CommentsEntity commentsEntity2 = TestDataUtilLikesAndComment.createTestCommentB(userEntity1, postEntity1);
        commentRepo.save(commentsEntity2);
        CommentsEntity commentsEntity3 = TestDataUtilLikesAndComment.createTestCommentC(userEntity1, postEntity1);
        commentRepo.save(commentsEntity3);
        CommentsEntity commentsEntity4 = TestDataUtilLikesAndComment.createTestCommentD(userEntity2, postEntity1);
        commentRepo.save(commentsEntity4);
        CommentsEntity commentsEntity5 = TestDataUtilLikesAndComment.createTestCommentE(userEntity2, postEntity2);
        commentRepo.save(commentsEntity5);
        CommentsEntity commentsEntity6 = TestDataUtilLikesAndComment.createTestCommentF(userEntity1, postEntity2);
        commentRepo.save(commentsEntity6);

        Iterable<CommentsEntity> result = commentRepo.findAll();

        assertThat(result).hasSize(6);
    }

    @Test
    public void testThatCommentCanBeDeleted(){

        UserEntity userEntity = TestDataUtil.createTestUserB();
        userRepo.save(userEntity);

        PostEntity postEntity = TestDataUtil.createTestPost(userEntity);
        postRepo.save(postEntity);

        CommentsEntity commentEntity = TestDataUtilLikesAndComment.createTestCommentA(userEntity, postEntity);
        commentRepo.save(commentEntity);
        commentRepo.deleteById(commentEntity.getCommentId());
        Optional<CommentsEntity> result1 = commentRepo.findById(commentEntity.getCommentId());
        assertThat(result1).isEmpty();

    }
    @Test
    @Transactional
    public void testThatCommentCanBeUpdated(){
        UserEntity userEntity = TestDataUtil.createTestUserB();
        userRepo.save(userEntity);

        PostEntity postEntity = TestDataUtil.createTestPost(userEntity);
        postRepo.save(postEntity);

        CommentsEntity commentEntity = TestDataUtilLikesAndComment.createTestCommentA(userEntity, postEntity);
        commentRepo.save(commentEntity);

        commentEntity.setContent("Hello Man");
        commentRepo.save(commentEntity);

        Optional<CommentsEntity> result1 = commentRepo.findById(commentEntity.getCommentId());
        assertThat(result1.get()).isEqualTo(commentEntity);
    }




}
