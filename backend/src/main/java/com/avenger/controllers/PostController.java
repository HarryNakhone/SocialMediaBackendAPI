package com.avenger.controllers;

import com.avenger.domains.dto.*;
import com.avenger.domains.entity.*;
import com.avenger.mappers.*;
import com.avenger.services.*;
import lombok.extern.java.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.*;

@RestController
@Log
public class PostController {

    private PostServices postServices;

    private Mapper<PostEntity, PostDto> postMapper;

    public PostController(PostServices postServices, Mapper<PostEntity, PostDto> postMapper){
        this.postServices = postServices;
        this.postMapper = postMapper;

    }

    @PutMapping(path = "/posts/{pid}")
    public ResponseEntity<PostDto> createUpdateBooks(@PathVariable("pid") String pid,
                                                     @RequestBody PostDto postDto){

        PostEntity postEntity = postMapper.mapFrom(postDto);
        boolean exists = postServices.isExists(pid);
        PostEntity savedPostEntity = postServices.createUpdatePost(pid, postEntity);

        if(exists){
            return new ResponseEntity<>(postMapper.mapTo(savedPostEntity), HttpStatus.OK);

        }else {

            return new ResponseEntity<>(postMapper.mapTo(savedPostEntity), HttpStatus.CREATED);
        }

    }


    /*********/
    @GetMapping(path = "/posts/get")
    public List<PostDto> listBooks(){
        List<PostEntity> posts = postServices.findAll();
        return posts.stream().map(postMapper::mapTo).collect(Collectors.toList());
    }

    @GetMapping(path = "/posts/get/{pid}")
    public ResponseEntity<PostDto> getAPost(@PathVariable("pid") String pid){
        Optional<PostEntity> onePost = postServices.findOnePost(pid);
        return onePost.map(postEntity -> {
            PostDto postDto = postMapper.mapTo(postEntity);
            return new ResponseEntity<>(postDto, HttpStatus.OK);

        }).orElse(
                new ResponseEntity<>(HttpStatus.NOT_FOUND)
        );
    }
    @PatchMapping(path = "/posts/update/{pid}")
    public ResponseEntity<PostDto> partialUpdateBook(@PathVariable("pid") String pid, @RequestBody PostDto postDto){
        if (!postServices.isExists(pid)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        PostEntity postEntity = postMapper.mapFrom(postDto);
        PostEntity savedEntity = postServices.partialUpdateB(pid, postEntity);
        return new ResponseEntity<>(postMapper.mapTo(savedEntity),HttpStatus.OK);
    }

    @DeleteMapping(path = "/posts/delete/{pid}")
    public ResponseEntity deleteAPost(@PathVariable("pid")String pid){
        postServices.delete(pid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
