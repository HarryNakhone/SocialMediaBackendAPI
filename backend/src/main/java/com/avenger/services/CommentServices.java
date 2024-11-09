package com.avenger.services;

import com.avenger.domains.entity.*;

import java.util.*;

public interface CommentServices {

    CommentsEntity partialUpdate(Long id, CommentsEntity cmE) ;
    boolean isCommentExists(Long id);

    CommentsEntity createComment(CommentsEntity cm);

    CommentsEntity createCommentPostId(CommentsEntity cm, String id);
    void delete(Long id);

    List<CommentsEntity> findAll();

    List<CommentsEntity> findAllWithId(String id);
}
