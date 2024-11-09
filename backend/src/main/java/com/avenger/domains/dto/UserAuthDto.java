package com.avenger.domains.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAuthDto {
    private String username;
    private String password;
}
