package com.online.catalog.books.user.controller;

import com.online.catalog.books.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class UserControllerTest {

  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private UserService userService;

  @Test
  @Transactional
  @WithUserDetails("super")
  void testGetBookList() throws Exception {
    MockHttpServletRequestBuilder get = MockMvcRequestBuilders.get("/api/v1/users/bookList");

    this.mockMvc
      .perform(get)
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(jsonPath("$").isArray());
  }

  @Test
  @Transactional
  @WithUserDetails("super")
  void testAddBook() throws Exception {
    MockHttpServletRequestBuilder addBook =
      MockMvcRequestBuilders.put("/api/v1/users/bookList/add/2");

    this.mockMvc
      .perform(addBook)
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(jsonPath("$").isArray())
      .andExpect(jsonPath("$[0].id").value(2L));
  }

  @Test
  @Transactional
  @WithUserDetails("super")
  void testAddBookWhenNotBookWithSpecifiedId() throws Exception {
    MockHttpServletRequestBuilder addBook =
      MockMvcRequestBuilders.put("/api/v1/users/bookList/add/42");

    this.mockMvc.perform(addBook).andExpect(status().isNotFound());
  }

  @Test
  @Transactional
  @WithUserDetails("super")
  void testRemoveBook() throws Exception {
    MockHttpServletRequestBuilder addBook =
      MockMvcRequestBuilders.put("/api/v1/users/bookList/add/2");
    this.mockMvc.perform(addBook);

    MockHttpServletRequestBuilder removeBook =
      MockMvcRequestBuilders.put("/api/v1/users/bookList/remove/2");

    this.mockMvc
      .perform(removeBook)
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(jsonPath("$").isEmpty());
  }

  @Test
  @Transactional
  @WithUserDetails("super")
  void testRemoveBookWhenNotBookWithSpecifiedIdInBookList() throws Exception {
    MockHttpServletRequestBuilder removeBook =
      MockMvcRequestBuilders.put("/api/v1/users/bookList/remove/100");

    this.mockMvc.perform(removeBook).andExpect(status().isNotFound());
  }
}
