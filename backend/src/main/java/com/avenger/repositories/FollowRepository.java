package com.avenger.repositories;

import com.avenger.domains.FollowId;
import com.avenger.domains.entity.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface FollowRepository extends JpaRepository<FollowEntity, FollowId> {
    @Query("SELECT follow FROM FollowEntity follow WHERE follow.following.id = :followingId")
    List<FollowEntity> findAllFollowerWithId (@Param("followingId") Long followingId);

    @Query("SELECT follow FROM FollowEntity follow WHERE follow.follower.id = :followerId ")
    List<FollowEntity> findAllFollowingWithId (@Param("followerId") Long followerId);
}
