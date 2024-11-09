package com.avenger.services;

import com.avenger.domains.entity.*;

import java.util.*;

public interface PostServices {
    PostEntity createUpdatePost(String isbn, PostEntity postEntity);

    List<PostEntity> findAll();

    Optional<PostEntity> findOnePost(String isbn);

    boolean isExists(String isbn);


    PostEntity partialUpdateB (String isbn, PostEntity postEntity);

    void delete(String isbn);
}
