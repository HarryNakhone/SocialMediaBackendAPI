package com.avenger.repositories;

import com.avenger.domains.entity.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface CommentRepository extends JpaRepository<CommentsEntity,Long> {



    /*** List comments based on post Id *****/

    @Query("SELECT comment FROM CommentsEntity comment WHERE comment.post.postId = :postId")
    List<CommentsEntity> findAllCWID (@Param("postId") String postId);
}
