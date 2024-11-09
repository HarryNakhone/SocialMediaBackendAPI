package com.avenger.services;

import com.avenger.domains.*;
import com.avenger.domains.entity.*;
import org.springframework.stereotype.*;

import java.util.*;


public interface LikeServices {

//    void delete(String postId, Long userId);
//
//    LikesEntity createLikes(LikesEntity likesEntity);
//
//    boolean isLikeExist(String postId, Long userId);

    boolean isLikeExist(String postId, Long userId);

    LikesEntity createLikes(LikesEntity likes);

    void delete(String postId, Long userId);

//    List<LikesEntity> findAllLikesWithId(String id);
}

