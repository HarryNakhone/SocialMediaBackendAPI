package com.avenger.domains.entity;

import com.avenger.domains.FollowId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder()
@IdClass(FollowId.class)
@Table(name="follow")
@Entity
public class FollowEntity {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "follower_id", nullable = false)
    public UserEntity follower;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "following_id", nullable = false)
    public UserEntity following;

    @Column(name = "create_at", nullable = false)
    public LocalDateTime createAt;
}