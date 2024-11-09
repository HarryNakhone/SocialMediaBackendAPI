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

@Log
@RestController
public class UserController {
    UserServices userServices;

    private Mapper<UserEntity, UserDto> userMapper;
    private Mapper<UserEntity, UserAuthDto> userAuthMapper;
    private Mapper<UserEntity, UserRegisDto> userRegisMapper;

    public UserController(UserServices authorService, Mapper<UserEntity, UserDto> userMapper,Mapper<UserEntity, UserAuthDto> userAuthMapper, Mapper<UserEntity, UserRegisDto> userRegisMapper  ){
        this.userServices = authorService;
        this.userMapper = userMapper;
        this.userAuthMapper =  userAuthMapper;
        this.userRegisMapper = userRegisMapper;
    }

    @PostMapping(path = "/users/login")
    public ResponseEntity<UserDto> loginUser (@RequestBody UserAuthDto user){   /// ReponseEntity Allows us to specifiy the HTTP status
        String username = user.getUsername();
        if(userServices.isUserExists(username)){
            return new ResponseEntity<>(HttpStatus.OK);

        }
        return new ResponseEntity<>(  HttpStatus.NOT_FOUND);  /// It is 201 (CREATED) in this case

    }
    @PostMapping(path = "/users/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody UserRegisDto user){
        UserEntity userEntity = userRegisMapper.mapFrom(user);
        if(userServices.isUserExists(userEntity.getUsername())){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        UserEntity savedUserEntity = userServices.createAuthor(userEntity);
        return new ResponseEntity<>( userMapper.mapTo(savedUserEntity), HttpStatus.CREATED);
    }

    @GetMapping(path = "/users/get")
    public List<UserDto> listAuthor(){
        List<UserEntity> authors = userServices.findAll();
        return authors.stream().map(userMapper::mapTo).collect(Collectors.toList());
    }

    @GetMapping(path = "/users/get/{id}")
    public ResponseEntity<UserDto> getAnAuthor(@PathVariable("id") Long id ){
        Optional<UserEntity> oneAuthor= userServices.findOne(id);
        return oneAuthor.map(authorEntity -> {
            UserDto userDto = userMapper.mapTo(authorEntity);
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

//    @PutMapping(path = "/users/update/{id}")
//    public ResponseEntity<UserDto> updateAnAuthor(@PathVariable("id") Long id, @RequestBody UserDto userDto){
//        if (!userServices.isExists(id)){
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//
//        userDto.setId(id);
//        UserEntity userEntity = userMapper.mapFrom(userDto);
//        UserEntity savedUserEntity = userServices.createAuthor(userEntity);
//        return new ResponseEntity<>(userMapper.mapTo(savedUserEntity), HttpStatus.OK);
//    }

    @PatchMapping(path = "/users/partial/{id}")
    public ResponseEntity<UserDto> partialUpdateAuthor(@PathVariable("id") Long id, @RequestBody UserDto userDto) {
        if (!userServices.isExists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        log.info(String.valueOf(userDto));
        UserEntity userEntity = userMapper.mapFrom(userDto);
        UserEntity updatedAuthor = userServices.partialUpdateAuthor(id, userEntity);

        return new ResponseEntity<>(userMapper.mapTo(updatedAuthor), HttpStatus.OK
        );
    }

    @DeleteMapping(path = "/users/delete/{id}")
    public ResponseEntity<UserDto> deleteAnAuthor(@PathVariable("id") Long id){
    userServices.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
