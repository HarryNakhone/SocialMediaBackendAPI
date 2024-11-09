package com.avenger.services.impl;


import com.avenger.domains.entity.*;
import com.avenger.repositories.*;
import com.avenger.services.*;
import org.springframework.stereotype.*;

import java.util.*;
import java.util.stream.*;

@Service
public class CommentServiceImpl implements CommentServices {

    private CommentRepository commentRepo;

    private PostRepository postRepo;

    public CommentServiceImpl(CommentRepository commentRepo, PostRepository postRepo){
        this.commentRepo = commentRepo;
        this.postRepo = postRepo;
    }

    @Override
    public CommentsEntity partialUpdate(Long id, CommentsEntity cmE) {
        return commentRepo.findById(id).map(existingComment -> {
           // Optional.ofNullable(userEntity.getName()).ifPresent(existingUser::setName);
            Optional.ofNullable(cmE.getContent()).ifPresent(existingComment::setContent);
            return commentRepo.save(existingComment);
        }).orElseThrow(() -> new RuntimeException("Comment is not exist"));
    }

    @Override
    public boolean isCommentExists(Long id) {

        return commentRepo.existsById(id);
    }

    @Override
    public CommentsEntity createComment(CommentsEntity comment) {
        return commentRepo.save(comment);
    }

    @Override
    public CommentsEntity createCommentPostId(CommentsEntity comment, String id) {
       if (!postRepo.existsById(id)){
           throw new IllegalArgumentException("Post not found with ID: " + id);
       }
        return commentRepo.save(comment );
    }

    @Override
    public void delete(Long id) {
        commentRepo.deleteById(id);

    }
    @Override
    public List<CommentsEntity> findAll() {   //// findAll() return Iterable Object, we used StreamSupport to turn that into List using toList()
        return StreamSupport.stream(commentRepo.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public List<CommentsEntity> findAllWithId(String id) {
        return StreamSupport.stream(commentRepo.findAllCWID(id).spliterator(), false).collect(Collectors.toList());
    }
}
