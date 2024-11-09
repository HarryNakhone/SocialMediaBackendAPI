package com.avenger.services;

import com.avenger.domains.entity.FollowEntity;

import java.util.*;

public interface FollowServices {
    boolean isUserFollowing(Long followerId, Long followingId);
    FollowEntity createFollow(FollowEntity followEntity);
    void delete(Long followerId, Long followingId);

   List<FollowEntity> findAllWithId(Long id);

   List<FollowEntity> findAllFollowingWithId(Long id);

}