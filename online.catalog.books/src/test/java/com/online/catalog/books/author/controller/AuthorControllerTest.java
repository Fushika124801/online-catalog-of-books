package com.online.catalog.books.author.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AuthorControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void testGetAll() throws Exception {
    MockHttpServletRequestBuilder get =
      MockMvcRequestBuilders.get("/api/v1/authors");

    this.mockMvc
      .perform(get)
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON));
  }

  @Test
  void testGet() throws Exception {
    MockHttpServletRequestBuilder get =
      MockMvcRequestBuilders.get("/api/v1/authors/1");

    this.mockMvc
      .perform(get)
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(jsonPath("$.id").value(1L));
  }

  @Test
  void create() throws Exception {
    MockHttpServletRequestBuilder save =
      MockMvcRequestBuilders.post("/api/v1/authors")
        .contentType(MediaType.APPLICATION_JSON)
        .content(
          "{\"firstName\":\"Edi\","
            + "\"lastName\":\"Gabrono\","
            + "\"birthday\":\"12-12-2021\"}");

    this.mockMvc
      .perform(save)
      .andExpect(status().isCreated())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(jsonPath("$.firstName").value("Edi"))
      .andExpect(jsonPath("$.lastName").value("Gabrono"))
      .andExpect(jsonPath("$.birthday").value("12-12-2021"));
  }

  @Test
  void edit() throws Exception {
    MockHttpServletRequestBuilder edit =
      MockMvcRequestBuilders.put("/api/v1/authors/1")
        .contentType(MediaType.APPLICATION_JSON)
        .content(
          "{\"firstName\":\"Edi\","
            + "\"lastName\":\"Gabrono\","
            + "\"birthday\":\"12-12-2021\"}");

    this.mockMvc
      .perform(edit)
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(jsonPath("$.id").value(1L))
      .andExpect(jsonPath("$.firstName").value("Edi"))
      .andExpect(jsonPath("$.lastName").value("Gabrono"))
      .andExpect(jsonPath("$.birthday").value("12-12-2021"));
  }
}