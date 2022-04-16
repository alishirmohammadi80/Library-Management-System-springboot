package com.alishirmohammadi.librarymanagementsystem.controller;
import org.junit.jupiter.api.Test;
import com.alishirmohammadi.librarymanagementsystem.entity.Member;
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
class PublisherControllerTest {
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
    void findAllPublishers() throws Exception {
        MvcResult  result=  mockMvc.perform(get("/publishers").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        Assertions.assertEquals(200,result.getResponse().getStatus());
    }

    @Test
    void findPublisherById() throws Exception {
        MvcResult  result=  mockMvc.perform(get("/publisher/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        Assertions.assertEquals(200,result.getResponse().getStatus());
    }

    @Test
    void showCreateForm() throws Exception {
        MvcResult  result=  mockMvc.perform(get("/addPublisher").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        Assertions.assertEquals(200,result.getResponse().getStatus());
    }

    @Test
    void showUpdateForm() throws Exception {
        MvcResult  result=  mockMvc.perform(get("/updatePublisher/2").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        Assertions.assertEquals(200,result.getResponse().getStatus());
    }

    @Test
    void deletePublisher() throws Exception {
        MvcResult  result=  mockMvc.perform(get("/remove-publisher/6").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(302)).andReturn();
        Assertions.assertEquals(302,result.getResponse().getStatus());
    }

    @Test
    void exportToExcel() throws Exception {
        MvcResult  result=  mockMvc.perform(get("/publisher/export/excel").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        Assertions.assertEquals(200,result.getResponse().getStatus());
    }

    @Test
    void createPublisher() {
    }

    @Test
    void updatePublisher() {
    }
}