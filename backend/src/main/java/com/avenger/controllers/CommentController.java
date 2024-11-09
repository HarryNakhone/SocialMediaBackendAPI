package com.avenger.controllers;

import com.avenger.domains.dto.*;
import com.avenger.domains.entity.*;
import com.avenger.mappers.*;
import com.avenger.services.*;
import lombok.extern.java.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import java.time.*;
import java.util.*;
import java.util.stream.*;

@RestController
@Log
public class CommentController {


    /***** ALL comments based on a post ID,  add comment based on post ID*****/
    CommentServices commentService;

    Mapper<CommentsEntity, CommentDto> mapper;
    public CommentController(CommentServices commentService, Mapper<CommentsEntity, CommentDto > mapper){
        this.commentService = commentService;
        this.mapper = mapper;
    }

    @GetMapping(path= "/comment/all")
    public List<CommentDto> listComments(){
        List<CommentsEntity> comments = commentService.findAll();
        return comments.stream().map(mapper::mapTo).collect(Collectors.toList());
    }

    @GetMapping(path= "/comment/all/{id}")
    public List<CommentDto> listCommentsPostId(@PathVariable("id") String id){
        List<CommentsEntity> comments = commentService.findAllWithId(id);
        return comments.stream().map(mapper::mapTo).collect(Collectors.toList());
    }

    @PostMapping(path = "/comment")
    public ResponseEntity<CommentDto> addComment (@RequestBody CommentDto cmDto){
        CommentsEntity commentsEntity = mapper.mapFrom(cmDto);
        commentsEntity.setCreateAt(LocalDateTime.now());
        CommentsEntity savedComment = commentService.createComment(commentsEntity);

        return new ResponseEntity<>(mapper.mapTo(savedComment), HttpStatus.CREATED);
    }

    @PostMapping(path= "/comment/post/{id}")
    public ResponseEntity<CommentDto> addCommentWithId (@RequestBody CommentDto cmDto, @PathVariable("id") String id){
        cmDto.setPostId(id);
        CommentsEntity commentsEntity = mapper.mapFrom(cmDto);
        commentsEntity.setCreateAt(LocalDateTime.now());
        CommentsEntity savedComment = commentService.createCommentPostId(commentsEntity,id );

        return new ResponseEntity<>(mapper.mapTo(savedComment), HttpStatus.CREATED);
    }

    @PatchMapping(path = "/comment/{id}")
    public ResponseEntity<CommentDto> partialChange (@PathVariable("id") Long cmId, @RequestBody CommentDto cmDto){
        if (!commentService.isCommentExists(cmId)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        cmDto.setCommentId(cmId);
        CommentsEntity commentEn = mapper.mapFrom(cmDto);
        CommentsEntity savedComment = commentService.partialUpdate(cmId, commentEn);
        return new ResponseEntity<>(mapper.mapTo(savedComment), HttpStatus.OK);

    }

}
