package com.avenger.services;

import com.avenger.domains.entity.*;

import java.util.*;

public interface PostServices {
    PostEntity createUpdateBook(String isbn, PostEntity postEntity);

    List<PostEntity> findAll();

    Optional<PostEntity> findOneBook(String isbn);

    boolean isExists(String isbn);


    PostEntity partialUpdateB (String isbn, PostEntity postEntity);

    void delete(String isbn);
}
