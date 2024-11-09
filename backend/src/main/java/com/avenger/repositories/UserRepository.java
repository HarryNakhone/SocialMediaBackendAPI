package com.avenger.repositories;

import com.avenger.domains.entity.*;
import org.springframework.data.jpa.repository.*;

import org.springframework.data.repository.query.*;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Query("SELECT CASE WHEN count(e) > 0 THEN true ELSE false END FROM UserEntity e where e.username = :username")
    Boolean existsByUsername(@Param("username") String username);
}
