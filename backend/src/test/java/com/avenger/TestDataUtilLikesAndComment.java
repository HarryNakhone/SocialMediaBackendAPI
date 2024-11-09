package com.avenger;

import com.avenger.domains.entity.*;

import java.time.*;

public class TestDataUtilLikesAndComment {
    private TestDataUtilLikesAndComment(){}

    public static LikesEntity createTestLikesA(final UserEntity user, final PostEntity post) {
        return LikesEntity.builder()
                .user(user)
                .post(post)
                .build();
    }

    public static CommentsEntity createTestCommentA(final UserEntity user, final PostEntity post){
        LocalDateTime now =  LocalDateTime.now();
        return CommentsEntity.builder()
                .commentId(1L)
                .user(user)
                .post(post)
                .content("What's up")
                .createAt(now)
                .build();
    }
    public static CommentsEntity createTestCommentB(final UserEntity user, final PostEntity post){
        LocalDateTime now =  LocalDateTime.now();
        return CommentsEntity.builder()
                .commentId(2L)
                .user(user)
                .post(post)
                .content("Potter Rob")
                .createAt(now)
                .build();
    }
    public static CommentsEntity createTestCommentC(final UserEntity user, final PostEntity post){
        LocalDateTime now =  LocalDateTime.now();
        return CommentsEntity.builder()
                .commentId(3L)
                .user(user)
                .post(post)
                .content("How to stop the rain")
                .createAt(now)
                .build();
    }
    public static CommentsEntity createTestCommentD(final UserEntity user, final PostEntity post){
        LocalDateTime now =  LocalDateTime.now();
        return CommentsEntity.builder()
                .commentId(4L)
                .user(user)
                .post(post)
                .content("Nice Try")
                .createAt(now)
                .build();
    }
    public static CommentsEntity createTestCommentE(final UserEntity user, final PostEntity post){
        LocalDateTime now =  LocalDateTime.now();
        return CommentsEntity.builder()
                .commentId(5L)
                .user(user)
                .post(post)
                .content("Excellent")
                .createAt(now)
                .build();
    }

    public static CommentsEntity createTestCommentF(final UserEntity user, final PostEntity post){
        LocalDateTime now =  LocalDateTime.now();
        return CommentsEntity.builder()
                .commentId(6L)
                .user(user)
                .post(post)
                .content("Amazing")
                .createAt(now)
                .build();
    }
}
