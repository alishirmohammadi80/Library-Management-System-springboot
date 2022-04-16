package com.alishirmohammadi.librarymanagementsystem.controller;
import com.alishirmohammadi.librarymanagementsystem.entity.Book;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
@RunWith(SpringRunner.class)
@SpringBootTest
class BookControllerTest {

    private MockMvc mockMvc;

    @Autowired
    WebApplicationContext context;

    @Autowired
    ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        mockMvc= MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    void findAllBooks() throws Exception {
        MvcResult  result=  mockMvc.perform(get("/books").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        Assertions.assertEquals(200,result.getResponse().getStatus());
    }

    @Test
    void searchBook() throws Exception {
        MvcResult  result=  mockMvc.perform(get("/searchBook").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        Assertions.assertEquals(200,result.getResponse().getStatus());
    }

    @Test
    void showCreateForm() throws Exception {
        MvcResult  result=  mockMvc.perform(get("/add").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        Assertions.assertEquals(200,result.getResponse().getStatus());
    }

    @Test
    void exportToExcel() throws Exception {
        MvcResult  result=  mockMvc.perform(get("/book/export/excel").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        Assertions.assertEquals(200,result.getResponse().getStatus());
    }

    @Test
    void findBookById() throws Exception {
        MvcResult  result=  mockMvc.perform(get("/book/4").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        Assertions.assertEquals(200,result.getResponse().getStatus());
    }

    @Test
    void deleteBook() throws Exception {
        MvcResult  result=  mockMvc.perform(get("/remove-book/8").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(302)).andReturn();
        Assertions.assertEquals(302,result.getResponse().getStatus());
    }

    @Test
    void showUpdateForm() throws Exception {
        MvcResult  result=  mockMvc.perform(get("/update/11").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        Assertions.assertEquals(200,result.getResponse().getStatus());
    }

    @Test
    void createBook() {
    }

    @Test
    void updateBook() {
    }
}