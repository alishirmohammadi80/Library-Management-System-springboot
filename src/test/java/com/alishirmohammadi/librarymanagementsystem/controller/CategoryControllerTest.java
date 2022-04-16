package com.alishirmohammadi.librarymanagementsystem.controller;
import com.alishirmohammadi.librarymanagementsystem.entity.Category;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
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
class CategoryControllerTest {

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
    void findAllCategories() throws Exception {
        MvcResult  result=  mockMvc.perform(get("/categories").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        Assertions.assertEquals(200,result.getResponse().getStatus());
    }

    @Test
    void findCategoryById() throws Exception {
        MvcResult  result=  mockMvc.perform(get("/category/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        Assertions.assertEquals(200,result.getResponse().getStatus());
    }

    @Test
    void showCreateForm() throws Exception {
        MvcResult  result=  mockMvc.perform(get("/addCategory").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        Assertions.assertEquals(200,result.getResponse().getStatus());
    }

    @Test
    void showUpdateForm() throws Exception {
        MvcResult  result=  mockMvc.perform(get("/updateCategory/2").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        Assertions.assertEquals(200,result.getResponse().getStatus());
    }

    @Test
    void deleteCategory() throws Exception {
        MvcResult  result=  mockMvc.perform(get("/remove-category/6").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(302)).andReturn();
        Assertions.assertEquals(302,result.getResponse().getStatus());
    }

    @Test
    void exportToExcel() throws Exception {
        MvcResult  result=  mockMvc.perform(get("/category/export/excel").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        Assertions.assertEquals(200,result.getResponse().getStatus());
    }

    @Test
    void createCategory() throws Exception {


    }

    @Test
    void updateCategory() throws Exception {


    }
}