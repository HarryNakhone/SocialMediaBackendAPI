package com.avenger.domains.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRegisDto {
    private Long id;
    private String name;
    private String phone;
    private String email;
    private String username;
    private String password;
}
