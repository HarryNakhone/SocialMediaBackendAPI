package com.avenger.domains.dto;

import lombok.*;

import java.time.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FollowDto {
    private Long followerId;
    private Long followingId;
    private LocalDateTime createAt;
}