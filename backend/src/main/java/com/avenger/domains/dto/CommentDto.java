package com.avenger.domains.dto;

import lombok.*;

import java.time.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentDto {
    private Long commentId;
    private Long userId;
    private String postId;
    private String content;
    private LocalDateTime createAt;
}
