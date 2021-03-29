package com.online.catalog.books.book.controller;

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
@AutoConfigureMockMvc(addFilters = false)
class BookControllerTest {

  @Autowired private MockMvc mockMvc;

  @Test
  void testGetAll() throws Exception {
    MockHttpServletRequestBuilder get = MockMvcRequestBuilders.get("/api/v1/books");

    this.mockMvc
        .perform(get)
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON));
  }

  @Test
  void testGet() throws Exception {
    MockHttpServletRequestBuilder get = MockMvcRequestBuilders.get("/api/v1/books/1");

    this.mockMvc
        .perform(get)
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id").value(1L));
  }

  @Test
  void save() throws Exception {
    MockHttpServletRequestBuilder save =
        MockMvcRequestBuilders.post("/api/v1/books")
            .contentType(MediaType.APPLICATION_JSON)
            .content(
                "{\"name\":\"viva lia Mexico\","
                    + "\"yearPublication\":\"1887\","
                    + "\"authors\":[{"
                    + "\"id\":1"
                    + "}]"
                    + "}");

    this.mockMvc
        .perform(save)
        .andExpect(status().isCreated())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.name").value("viva lia Mexico"))
        .andExpect(jsonPath("$.yearPublication").value("1887"));
  }

  @Test
  void edit() throws Exception {
    MockHttpServletRequestBuilder edit =
        MockMvcRequestBuilders.put("/api/v1/books/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(
                "{\"name\":\"viva lia Mexico\","
                    + "\"yearPublication\":\"1887\","
                    + "\"authors\":[{"
                    + "\"id\":1"
                    + "}]"
                    + "}");

    this.mockMvc
        .perform(edit)
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id").value(1L))
        .andExpect(jsonPath("$.name").value("viva lia Mexico"))
        .andExpect(jsonPath("$.yearPublication").value("1887"));
  }
}
