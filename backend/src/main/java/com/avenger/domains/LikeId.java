package com.avenger.domains;

import lombok.*;

import java.io.*;

@Data
@EqualsAndHashCode
public class LikeId implements Serializable {
    private Long user;
    private String post;

    public LikeId(){
    }

    public LikeId(Long userId, String postId){
        this.user = userId;
        this.post = postId;

    }
}
