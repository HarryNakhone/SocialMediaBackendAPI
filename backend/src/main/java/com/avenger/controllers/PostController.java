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

    private Mapper<PostEntity, PostDto> bookMapper;

    public PostController(PostServices postServices, Mapper<PostEntity, PostDto> bookMapper){
        this.postServices = postServices;
        this.bookMapper = bookMapper;

    }

    @PutMapping(path = "/posts/{pid}")
    public ResponseEntity<PostDto> createUpdateBooks(@PathVariable("pid") String pid,
                                                     @RequestBody PostDto postDto){

        PostEntity postEntity = bookMapper.mapFrom(postDto);
        boolean exists = postServices.isExists(pid);
        PostEntity savedPostEntity = postServices.createUpdateBook(pid, postEntity);

        if(exists){
            return new ResponseEntity<>(bookMapper.mapTo(savedPostEntity), HttpStatus.OK);

        }else {

            return new ResponseEntity<>(bookMapper.mapTo(savedPostEntity), HttpStatus.CREATED);
        }

    }


    /*********/
    @GetMapping(path = "/posts/get")
    public List<PostDto> listBooks(){
        List<PostEntity> books = postServices.findAll();
        return books.stream().map(bookMapper::mapTo).collect(Collectors.toList());
    }

    @GetMapping(path = "/posts/get/{pid}")
    public ResponseEntity<PostDto> getABook(@PathVariable("pid") String pid){
        Optional<PostEntity> oneBook = postServices.findOneBook(pid);
        return oneBook.map(bookEntity -> {
            PostDto postDto = bookMapper.mapTo(bookEntity);
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

        PostEntity postEntity = bookMapper.mapFrom(postDto);
        PostEntity savedEntity = postServices.partialUpdateB(pid, postEntity);
        return new ResponseEntity<>(bookMapper.mapTo(savedEntity),HttpStatus.OK);
    }

    @DeleteMapping(path = "/posts/delete/{pid}")
    public ResponseEntity deleteABook(@PathVariable("pid")String pid){
        postServices.delete(pid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
