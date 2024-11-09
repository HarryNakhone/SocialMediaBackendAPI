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
//@DirtiesContext(classMode =  DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
//@AutoConfigureMockMvc
//public class BookControllerTests {
//
//    private MockMvc mockMvc;
//
//    private ObjectMapper objectMapper;
//
//    private PostServices postServices;
//
//    @Autowired
//    public BookControllerTests(MockMvc mockMvc, ObjectMapper objectMapper, PostServices postServices){
//        this.mockMvc = mockMvc;
//        this.objectMapper = objectMapper;
//        this.postServices = postServices;
//
//    }
//
//    @Test
//    public void testThatCreateBookReturnCorrectHTTP201() throws Exception{
//        PostDto postDto = TestDataUtil.createTestBookDto(null);
//        String createBookJson = objectMapper.writeValueAsString(postDto);
//
//        mockMvc.perform(
//                MockMvcRequestBuilders.put("/books/" + postDto.getPostId())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(createBookJson)
//        ).andExpect(
//                MockMvcResultMatchers.status().isCreated()
//        );
//    }
//
//    @Test
//    public void testThatCreateBookReturnSavedBook() throws Exception{
//
//
//
//        PostDto postDto = TestDataUtil.createTestBookDto(null);
//
//        String createBookJson = objectMapper.writeValueAsString(postDto);
//
//        mockMvc.perform(
//                MockMvcRequestBuilders.put("/books/" + postDto.getPostId())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(createBookJson)
//        ).andExpect(
//                MockMvcResultMatchers.jsonPath("$.isbn").value(postDto.getPostId())
//        ).andExpect(
//                MockMvcResultMatchers.jsonPath("$.title").value(postDto.getTitle())
//        );
//    }
//    @Test
//    public void testThatCreateUpdateBookReturnBookHttp200() throws Exception{
//
//        PostEntity postEntity = TestDataUtil.createTestBookE(null);
//        PostEntity savedPostEntity = postServices.createUpdateBook(postEntity.getPostId(), postEntity);
//
//        PostDto postDto = TestDataUtil.createTestBookDto(null);
//        postDto.setPostId(savedPostEntity.getPostId());
//        String createBookJson = objectMapper.writeValueAsString(postDto);
//
//        mockMvc.perform(
//                MockMvcRequestBuilders.put("/books/" + postEntity.getPostId())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(createBookJson)
//        ).andExpect(
//                MockMvcResultMatchers.status().isOk()
//        );
//    }
//
//    @Test
//    public void testThatUpdateBookReturnUpdatedBook() throws Exception {
//
//        PostEntity postEntity = TestDataUtil.createTestBookE(null);
//        PostEntity savedPostEntity = postServices.createUpdateBook(postEntity.getPostId(), postEntity);
//
//        PostDto postDto = TestDataUtil.createTestBookDto(null);
//        postDto.setPostId(savedPostEntity.getPostId());
//        postDto.setTitle("UPDATED");
//        String createBookJson = objectMapper.writeValueAsString(postDto);
//
//        mockMvc.perform(
//                MockMvcRequestBuilders.put("/books/555")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(createBookJson)
//        ).andExpect(
//                MockMvcResultMatchers.jsonPath("$.isbn").value("555")
//        ).andExpect(
//                MockMvcResultMatchers.jsonPath("$.title").value("UPDATED")
//        );
//
//    }
//
//    @Test
//    public void testThatListBookReturnHTTPs200() throws Exception {
//        mockMvc.perform(
//                MockMvcRequestBuilders.get("/books/get")
//                        .contentType(MediaType.APPLICATION_JSON)
//        ).andExpect(
//                MockMvcResultMatchers.status().isOk()
//        );
//
//    }
//
//    @Test
//    public void testThatListBookReturnProperList() throws Exception {
//
//        PostEntity postEntity = TestDataUtil.createTestBookC(null);
//
//        postServices.createUpdateBook(postEntity.getPostId(), postEntity);
//
//        mockMvc.perform(
//                MockMvcRequestBuilders.get("/books/get")
//                        .contentType(MediaType.APPLICATION_JSON)
//        ).andExpect(
//                MockMvcResultMatchers.jsonPath("$[0].isbn").value("88498")
//        ).andExpect(
//                MockMvcResultMatchers.jsonPath("$[0].title").value("Kuay")
//        );
//
//    }
//
//    @Test
//    public void testThatFindOneBookReturn200HTTPs() throws Exception {
//
//        PostEntity postEntity = TestDataUtil.createTestBookC(null);
//
//        postServices.createUpdateBook(postEntity.getPostId(), postEntity);
//
//        mockMvc.perform(
//                MockMvcRequestBuilders.get("/books/get/88498")
//                        .contentType(MediaType.APPLICATION_JSON)
//        ).andExpect(
//                MockMvcResultMatchers.status().isOk()
//        );
//    }
//
//    @Test
//    public void testThatFindOneReturnCorrectResults() throws Exception {
//
//        PostEntity postEntity = TestDataUtil.createTestBookC(null);
//
//        postServices.createUpdateBook(postEntity.getPostId(), postEntity);
//
//        mockMvc.perform(
//                MockMvcRequestBuilders.get("/books/get/88498")
//                        .contentType(MediaType.APPLICATION_JSON)
//        ).andExpect(
//                MockMvcResultMatchers.jsonPath("$.isbn").value("88498")
//        ).andExpect(
//                MockMvcResultMatchers.jsonPath("$.title").value("Kuay")
//        );
//
//    }
//
//}
