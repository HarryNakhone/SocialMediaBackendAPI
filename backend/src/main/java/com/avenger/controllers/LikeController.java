package com.avenger.controllers;


import com.avenger.domains.*;
import com.avenger.domains.dto.*;
import com.avenger.domains.entity.*;
import com.avenger.mappers.*;
import com.avenger.services.*;
import lombok.extern.java.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

@RestController
@Log
public class LikeController {
    private LikeServices likeServices;

    private Mapper<LikesEntity, LikeDto> mapper;

    public LikeController( LikeServices likeServices, Mapper<LikesEntity, LikeDto>mapper){
        this.likeServices = likeServices;
        this.mapper = mapper;

    }

    @PostMapping(path = "/likes")
    public ResponseEntity<LikeDto> addLike (@RequestBody LikeDto lDto){

        LikesEntity likesEntity = mapper.mapFrom(lDto);
//        log.info(lDto.toString());
//        log.info(likesEntity.toString());
        Long userId =likesEntity.getUser().getId();
        log.info(String.valueOf(userId));
        String postId = likesEntity.getPost().getPostId();
        log.info(postId);

        LikesEntity savedLike = likeServices.createLikes(likesEntity);
        return new ResponseEntity<>(mapper.mapTo(savedLike), HttpStatus.CREATED);
    }

//    @GetMapping(path= "/likes/all/{id}")
//    public List<LikeDto> getAllLikesWithId(@PathVariable("id") String postId){
//
//            List<LikesEntity> likes = likeServices.findAllLikesWithId(postId);
//            return likes.stream().map(mapper::mapTo).collect(Collectors.toList());
//
//    }

    @DeleteMapping(path = "/likes/delete")
    public ResponseEntity<Void> removeLike(@RequestParam String postId, @RequestParam Long userId){
        if (likeServices.isLikeExist(postId, userId)) {
            likeServices.delete(postId, userId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
