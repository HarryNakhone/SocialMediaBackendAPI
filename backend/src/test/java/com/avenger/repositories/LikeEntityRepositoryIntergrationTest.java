package com.avenger.repositories;

import com.avenger.*;
import com.avenger.domains.*;
import com.avenger.domains.entity.*;
import jakarta.transaction.*;
import lombok.extern.java.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.test.annotation.*;
import org.springframework.test.context.junit.jupiter.*;

import java.util.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
@Log
@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class LikeEntityRepositoryIntergrationTest {

    private LikeRepository likeRepository;

    private PostRepository postRepository;

    private UserRepository userRepository;


    @Autowired
    public LikeEntityRepositoryIntergrationTest(LikeRepository likeRepository, UserRepository userRepository, PostRepository postRepository){
        this.likeRepository = likeRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;

    }

    @Test
    @Transactional
    public void testThatLikeCanBeSaved(){
        UserEntity user = TestDataUtil.createTestUserB();
        userRepository.save(user);
        PostEntity post = TestDataUtil.createTestPost(user);
        postRepository.save(post);
        LikesEntity likesEntity = TestDataUtilLikesAndComment.createTestLikesA(user, post);
        likeRepository.save(likesEntity);
        LikeId like = new LikeId(likesEntity.getUser().getId(), likesEntity.getPost().getPostId());
        Optional<LikesEntity> result = likeRepository.findById(like);
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(likesEntity);
    }

    @Transactional
    @Test
    public void testThatMultipleLikeCanBeSaved() {

        UserEntity user1 = TestDataUtil.createTestUserB();
        userRepository.save(user1);

        UserEntity user2 = TestDataUtil.createTestUserD();
        userRepository.save(user2);


        UserEntity user3 = TestDataUtil.createTestUserE();
        userRepository.save(user3);


        PostEntity post = TestDataUtil.createTestPost(user1);
        postRepository.save(post);

        LikesEntity likesEntity1 = TestDataUtilLikesAndComment.createTestLikesA(user1, post);
        likeRepository.save(likesEntity1);

        LikesEntity likesEntity2 = TestDataUtilLikesAndComment.createTestLikesA(user2, post);
        likeRepository.save(likesEntity2);

        LikesEntity likesEntity3 = TestDataUtilLikesAndComment.createTestLikesA(user3, post);
        likeRepository.save(likesEntity3);

    // Ensure all changes are synchronized
        Iterable<LikesEntity> result = likeRepository.findAll();

        assertThat(result).hasSize(3).containsExactly(likesEntity1, likesEntity2, likesEntity3);
    }

    @Test
    @Transactional
    public void testThatLikeCanDelete(){
        UserEntity user1 = TestDataUtil.createTestUserB();
        userRepository.save(user1);

        PostEntity post1 = TestDataUtil.createTestPost(user1);
        postRepository.save(post1);

        LikesEntity likesEntity = TestDataUtilLikesAndComment.createTestLikesA(user1, post1);
        likeRepository.save(likesEntity);

        LikeId likeid = new LikeId(likesEntity.getUser().getId(), likesEntity.getPost().getPostId());
        Optional<LikesEntity> result = likeRepository.findById(likeid);
        assertThat(result).isPresent();

        likeRepository.deleteById(likeid);
        Optional<LikesEntity> empty = likeRepository.findById(likeid);
        assertThat(empty).isEmpty();

    }



}
