package com.avenger.domains;

import lombok.*;

import java.io.*;

@Data
@EqualsAndHashCode

public class FollowId implements Serializable {
    private Long follower;
    private Long following;
    public FollowId(){

    }

    public FollowId(Long user1Id, Long user2Id){
        this.follower = user1Id;
        this.following = user2Id;


    }
}