package com.avenger.repositories;

import com.avenger.domains.*;
import com.avenger.domains.entity.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.*;
import org.springframework.data.repository.query.*;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface LikeRepository extends JpaRepository<LikesEntity, LikeId> {
    @Query("SELECT l FROM LikesEntity l WHERE l.user.id = :userId and l.post.postId = :postId")
    Optional<LikesEntity> findByUserIdAndPostId(@Param("userId") Long userId, @Param("postId") String postId);
}
