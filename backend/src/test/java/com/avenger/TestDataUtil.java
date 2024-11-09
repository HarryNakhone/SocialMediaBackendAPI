package com.avenger;


import com.avenger.domains.dto.*;
import com.avenger.domains.entity.*;

public class TestDataUtil {
    private TestDataUtil(){}

    public static UserEntity createTestUserB() {
        return UserEntity.builder()
                .id(1L)
                .name("justjames dog")
                .email("bbbbb@BBB")
                .phone("991212122")
                .username("justjjj123")
                .password("jamesdogg")
                .build();
    }

    public static UserDto createTestUserDto() {
        return UserDto.builder()
                .id(2L)
                .name("HEEMAN")
                .email("llll@LLLLL")
                .phone("102108103")

                .build();
    }
//    public static UserEntity createTestAuthorC() {
//        return UserEntity.builder()
//                .id(2L)
//                .name("Jo Kendog")
//                .age(23)
//                .build();
//    }
    public static UserEntity createTestUserD() {
        return UserEntity.builder()
                .id(2L)
                .name("Guo zheYang")
                .email("mkkkk@lll")
                .phone("21234114")
                .username("molester")
                .password("lordofjek000")
                .build();
    }
    public static UserEntity createTestUserE() {
        return UserEntity.builder()
                .id(3L)
                .name("Heeman Jek")
                .email("@DDDD")
                .phone("212131313")
                .username("heekuay155")
                .password("heeku0900")
                .build();
    }

    public static PostEntity createTestPost(final UserEntity user) {
        return PostEntity.builder()
                .postId("12355")
                .postContent("Heema")
                .title("kuay")
                .user(user)
                .build();
    }

    public static PostEntity createTestPost2(final UserEntity user) {
        return PostEntity.builder()
                .postId("5555")
                .postContent("Jek")
                .title("Dog")
                .user(user)
                .build();
    }

    public static PostEntity createTestPost3(final UserEntity user) {
        return PostEntity.builder()
                .postId("0923")
                .postContent("Lol")
                .title("Prog")
                .user(user)
                .build();
    }
//    public static PostDto createTestBookDto(final UserDto author) {
//        return PostDto.builder()
//                .isbn("010101")
//                .title("Kuay")
//                .author(author)
//                .build();
//    }
//
//
//    public static PostEntity createTestBookB(UserEntity author) {
//        return PostEntity.builder()
//                .isbn("77779")
//                .title("Pokemon")
//                .author(author)
//                .build();
//    }
//
//    public static PostEntity createTestBookC(UserEntity author) {
//        return PostEntity.builder()
//                .isbn("88498")
//                .title("Kuay")
//                .author(author)
//                .build();
//    }
//
//    public static PostEntity createTestBookD(UserEntity author) {
//        return PostEntity.builder()
//                .isbn("99999")
//                .title("Jekky")
//                .author(author)
//                .build();
//    }
//
//    public static PostEntity createTestBookE(UserEntity author) {
//        return PostEntity.builder()
//                .isbn("555")
//                .title("humtai")
//                .author(author)
//                .build();
//    }

}
