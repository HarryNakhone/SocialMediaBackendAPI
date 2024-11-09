package com.avenger.mappers.impl;

import com.avenger.domains.dto.*;
import com.avenger.domains.entity.*;
import com.avenger.mappers.*;
import com.avenger.repositories.*;
import org.modelmapper.*;
import org.springframework.stereotype.*;

@Component
public class CommentMapperImpl implements Mapper<CommentsEntity, CommentDto> {

    private ModelMapper modelMapper;
    private UserRepository userRepo;

    private PostRepository postRepo;

    public CommentMapperImpl(ModelMapper modelMapper, UserRepository userRepo, PostRepository postRepo){
        this.postRepo = postRepo;
        this.modelMapper = modelMapper;
        this.userRepo = userRepo;


        modelMapper.typeMap(CommentsEntity.class, CommentDto.class).addMappings(mapper ->{
            mapper.map(src -> src.getUser().getId(), CommentDto::setUserId);
            mapper.map(src -> src.getPost().getPostId(), CommentDto::setPostId);
        });


    }

    @Override
    public CommentDto mapTo(CommentsEntity commentsEntity) {
        return modelMapper.map(commentsEntity, CommentDto.class);
    }

    @Override
    public CommentsEntity mapFrom(CommentDto commentDto) {
//        UserEntity userEntity = userRepo.findById(commentDto.getUserId())
//                .orElseThrow(() -> new IllegalArgumentException("User Not found with ID: " + commentDto.getUserId()));
//        PostEntity postEntity = postRepo.findById(commentDto.getPostId())
//                .orElseThrow(() -> new IllegalArgumentException("Post Not found with ID: " + commentDto.getPostId()));
//
//        CommentsEntity commentsEntity = new CommentsEntity();
//        commentsEntity.setCommentId(commentDto.getCommentId());
//        commentsEntity.setUser(userEntity);
//        commentsEntity.setPost(postEntity);
//        commentsEntity.setContent(commentDto.getContent());
//        commentsEntity.setCreateAt(commentDto.getCreateAt());
//        return commentsEntity;

        UserEntity userEntity = userRepo.findById(commentDto.getUserId()).orElseThrow(() -> new IllegalArgumentException("User not found with this (Comment) ID: " + commentDto.getUserId()));

        PostEntity postEntity = postRepo.findById(commentDto.getPostId()).orElseThrow(() -> new IllegalArgumentException("Post not found with this (Comment) ID:" + commentDto.getPostId()));

        CommentsEntity commentEn = new CommentsEntity();
        commentEn.setUser(userEntity);
        commentEn.setPost(postEntity);
        commentEn.setContent(commentDto.getContent());
       // commentEn.setCreateAt(commentDto.getCreateAt());

        return commentEn;
    }
}
