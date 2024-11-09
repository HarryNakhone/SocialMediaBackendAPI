package com.avenger.domains.dto;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostDto {

    private String postId;

    private String name;

    private String title;

    private Long userId;

}
