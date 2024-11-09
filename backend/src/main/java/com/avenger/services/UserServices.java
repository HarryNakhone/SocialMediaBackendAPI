package com.avenger.services;

import com.avenger.domains.entity.*;

import java.util.*;

public interface UserServices {
    UserEntity createAuthor(UserEntity author);

    List<UserEntity> findAll();

    Optional<UserEntity> findOne(Long id);

    boolean isExists(Long id);
    boolean isUserExists(String username);
    UserEntity partialUpdateAuthor(Long id, UserEntity userEntity);

    void delete(Long id);
}
