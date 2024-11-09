package com.avenger.services.impl;

import com.avenger.domains.entity.*;
import com.avenger.repositories.*;
import com.avenger.services.*;
import lombok.extern.java.*;
import org.springframework.stereotype.*;

import java.util.*;
import java.util.stream.*;

@Log
@Service
public class UserServiceImpl implements UserServices {
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public UserEntity createAuthor(UserEntity userEntity){
        return userRepository.save(userEntity);
    }

    @Override
    public List<UserEntity> findAll() {
        return StreamSupport.stream(userRepository    //// findAll() return Iterable Object, we used StreamSupport to turn that into List using toList()
                .findAll()
                .spliterator(),
                false).collect(Collectors.toList());
    }

    @Override
    public Optional<UserEntity> findOne(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public boolean isExists(Long id){
       return  userRepository.existsById(id);
    }

    @Override
    public boolean isUserExists(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public UserEntity partialUpdateAuthor(Long id, UserEntity userEntity) {
        userEntity.setId(id);
        log.info(String.valueOf(userEntity));

        return userRepository.findById(id).map(existingUser -> {
            Optional.ofNullable(userEntity.getName()).ifPresent(existingUser::setName);
            Optional.ofNullable(userEntity.getEmail()).ifPresent(existingUser::setEmail);
            Optional.ofNullable(userEntity.getPhone()).ifPresent(existingUser::setPhone);
            Optional.ofNullable(userEntity.getUsername()).ifPresent(existingUser::setUsername);
            Optional.ofNullable(userEntity.getPassword()).ifPresent(existingUser::setPassword);
            log.info(String.valueOf(existingUser));
            return userRepository.save(existingUser);
        }).orElseThrow(() -> new RuntimeException("Author does not exists"));
    }

    @Override
    public void delete(Long id){
        userRepository.deleteById(id);
    }

}
