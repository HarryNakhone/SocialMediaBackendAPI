package com.avenger.domains.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
    @SequenceGenerator(name = "user_id_seq", sequenceName = "user_sequence", allocationSize = 1)
    private Long id;


    private String name;
    private String email;
    private String phone;
    private String password;
    private String username;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch =FetchType.LAZY)
    private List<PostEntity> posts;

    @JsonManagedReference
    public List<PostEntity> getPosts() {
        return posts;
    }
}
