//package com.avenger.controllers;
//
//import com.fasterxml.jackson.databind.*;
//import com.avenger.*;
//import com.avenger.domains.dto.*;
//import com.avenger.domains.entity.*;
//import com.avenger.services.*;
//import org.junit.jupiter.api.*;
//import org.junit.jupiter.api.extension.*;
//import org.springframework.beans.factory.annotation.*;
//import org.springframework.boot.test.autoconfigure.web.servlet.*;
//import org.springframework.boot.test.context.*;
//import org.springframework.http.*;
//import org.springframework.test.annotation.*;
//import org.springframework.test.context.junit.jupiter.*;
//import org.springframework.test.web.servlet.*;
//import org.springframework.test.web.servlet.request.*;
//import org.springframework.test.web.servlet.result.*;
//
//@SpringBootTest
//@ExtendWith(SpringExtension.class)
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
//@AutoConfigureMockMvc
//public class AuthorControInterTests {
//    private MockMvc mockMvc;
//
//    private UserServices userServices;
//
//    private ObjectMapper objectMapper;  // ObjMapper for turning Java Object into JSON
//
//    @Autowired
//    public AuthorControInterTests(MockMvc mockMvc, ObjectMapper objectMapper, UserServices userServices){
//        this.mockMvc= mockMvc;
//        this.objectMapper = objectMapper;
//        this.userServices = userServices;
//
//    }
//
//    @Test
//    public void testThatCreateAuthorSuccessfullyReturnHttp201Created() throws Exception {
//        UserEntity testAuthorB = TestDataUtil.createTestAuthorB();
//        testAuthorB.setId(null);
//        String authorJson = objectMapper.writeValueAsString(testAuthorB);
//
//        mockMvc.perform(
//                MockMvcRequestBuilders.post("/authors")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(authorJson)
//        ).andExpect(
//                MockMvcResultMatchers.status().isCreated()
//
//        );
//    }
//
//    @Test
//    public void testThatCreateAuthorSuccessfullyReturnSavedAuthor() throws Exception {
//        UserEntity testAuthorB = TestDataUtil.createTestAuthorB();
//        testAuthorB.setId(null);
//        String authorJson = objectMapper.writeValueAsString(testAuthorB);
//
//        mockMvc.perform(
//                MockMvcRequestBuilders.post("/authors")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(authorJson)
//        ).andExpect(
//                MockMvcResultMatchers.jsonPath("$.id").isNumber()
//
//        ).andExpect(
//                MockMvcResultMatchers.jsonPath("$.name").value("justjames dog")
//        ).andExpect(
//                MockMvcResultMatchers.jsonPath("$.age").value(80)
//        );
//    }
//
//    @Test
//    public void testThatListAuthorReturnCorrectHTTPs() throws Exception {
//        mockMvc.perform(
//                MockMvcRequestBuilders.get("/authors/get")
//                        .contentType(MediaType.APPLICATION_JSON)
//        ).andExpect(
//                MockMvcResultMatchers.status().isOk()
//
//        );
//    }
//
//    @Test
//    public void testThatListAuthorReturnProperList() throws Exception {
//        UserEntity userEntity = TestDataUtil.createTestAuthorC();
//        userServices.createAuthor(userEntity);
//
//        mockMvc.perform(
//                MockMvcRequestBuilders.get("/authors/get")
//                        .contentType(MediaType.APPLICATION_JSON)
//        ).andExpect(
//                MockMvcResultMatchers.jsonPath("$[0].id").isNumber()
//
//        ).andExpect(
//                MockMvcResultMatchers.jsonPath("$[0].name").value("Jo Kendog")
//        ).andExpect(
//                MockMvcResultMatchers.jsonPath("$[0].age").value(23)
//        );
//    }
//
//    @Test
//    public void testThatFindOneAuthorReturn400HTTPsIfNotFound() throws Exception {
//        UserEntity userEntity = TestDataUtil.createTestAuthorC();
//        userServices.createAuthor(userEntity);
//
//        mockMvc.perform(
//                MockMvcRequestBuilders.get("/authors/get/99")
//                        .contentType(MediaType.APPLICATION_JSON)
//        ).andExpect(
//                MockMvcResultMatchers.status().isNotFound()
//        );
//
//    }
//
//    @Test
//    public void testThatFindOneAuthorReturnCorrectResult() throws Exception {
//        UserEntity userEntity = TestDataUtil.createTestAuthorC();
//        userServices.createAuthor(userEntity);
//
//        mockMvc.perform(
//                MockMvcRequestBuilders.get("/authors/get/1")
//                        .contentType(MediaType.APPLICATION_JSON)
//        ).andExpect(
//                MockMvcResultMatchers.jsonPath("$.id").isNumber()
//
//        ).andExpect(
//                MockMvcResultMatchers.jsonPath("$.name").value("Jo Kendog")
//        ).andExpect(
//                MockMvcResultMatchers.jsonPath("$.age").value(23)
//        );
//
//    }
//
//    @Test
//    public void testThatUpdateAuthorReturn400HTTPsIfNotFound() throws Exception {
//        UserDto userDto = TestDataUtil.createTestAuthorDto();
//        String authorJson = objectMapper.writeValueAsString(userDto);
//
//        mockMvc.perform(
//                MockMvcRequestBuilders.put("/authors/update/99")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(authorJson)
//        ).andExpect(
//                MockMvcResultMatchers.status().isNotFound()
//        );
//
//    }
//
//    @Test
//    public void testThatUpdateAuthorReturn200HTTPsIfFound() throws Exception {
//        UserEntity testUserEntity = TestDataUtil.createTestAuthorC();
//        UserEntity savedUserEntity = userServices.createAuthor(testUserEntity);
//
//        UserDto userDto = TestDataUtil.createTestAuthorDto();
//        String authorJson = objectMapper.writeValueAsString(userDto);
//
//        mockMvc.perform(
//                MockMvcRequestBuilders.put("/authors/update/" + savedUserEntity.getId())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(authorJson)
//        ).andExpect(
//                MockMvcResultMatchers.status().isOk()
//        );
//
//    }
//
//    @Test
//    public void testThatFullUpdateUpdatesExistingAuthor() throws Exception {
//        UserEntity testUserEntity = TestDataUtil.createTestAuthorC();
//        UserEntity savedAuthor  = userServices.createAuthor(testUserEntity);
//
//        UserDto userDto = TestDataUtil.createTestAuthorDto();
//       userDto.setId(savedAuthor.getId());
//
//        String authorJson = objectMapper.writeValueAsString(userDto);
//
//        mockMvc.perform(
//                MockMvcRequestBuilders.put("/authors/update/" + savedAuthor.getId())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(authorJson)
//        ).andExpect(
//                MockMvcResultMatchers.jsonPath("$.id").value(savedAuthor.getId())
//
//        ).andExpect(
//                MockMvcResultMatchers.jsonPath("$.name").value(userDto.getName())
//        ).andExpect(
//                MockMvcResultMatchers.jsonPath("$.phone").value(userDto.getPhone())
//        );
//    }
//
//    @Test
//    public void testThatPartialUpdateReturnHTTPs200() throws Exception{
//        UserEntity userEntity = TestDataUtil.createTestAuthorC();
//        UserEntity savedEntity = userServices.createAuthor(userEntity);
//
//        UserDto userDto = TestDataUtil.createTestAuthorDto();
//        userDto.setName("partially updated");
//        String authorJson = objectMapper.writeValueAsString(userDto);
//
//        mockMvc.perform(
//                MockMvcRequestBuilders.patch("/authors/partial/" + savedEntity.getId())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(authorJson)
//        ).andExpect(
//                MockMvcResultMatchers.status().isOk()
//        );
//
//    }
//
//    @Test
//    public void testThatPartialUpdateReturnCorrectUpdatedAuthor() throws Exception{
//        UserEntity userEntity = TestDataUtil.createTestAuthorC();
//        UserEntity savedEntity = userServices.createAuthor(userEntity);
//
//        UserDto userDto = TestDataUtil.createTestAuthorDto();
//        userDto.setName("partially updated");
//        String authorJson = objectMapper.writeValueAsString(userDto);
//
//        mockMvc.perform(
//                MockMvcRequestBuilders.patch("/authors/partial/" + savedEntity.getId())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(authorJson)
//        ).andExpect(
//                MockMvcResultMatchers.jsonPath("$.id").value(savedEntity.getId())
//
//        ).andExpect(
//                MockMvcResultMatchers.jsonPath("$.name").value("partially updated")
//        ).andExpect(
//                MockMvcResultMatchers.jsonPath("$.phone").value(userDto.getPhone())
//        );
//
//    }
//
//    @Test
//    public void testThatDeleteAuthorReturn204IfSuccess() throws Exception {
//        mockMvc.perform(
//                MockMvcRequestBuilders.delete("/authors/delete/999")
//                        .contentType(MediaType.APPLICATION_JSON)
//        ).andExpect(
//                MockMvcResultMatchers.status().isNoContent()
//        );
//    }
//}