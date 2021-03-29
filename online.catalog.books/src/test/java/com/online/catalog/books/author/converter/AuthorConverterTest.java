package com.online.catalog.books.author.converter;

import com.online.catalog.books.author.dto.AuthorDto;
import com.online.catalog.books.author.model.Author;
import com.online.catalog.books.author.model.enums.Sex;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class AuthorConverterTest {

  private final AuthorConverter authorConverter = new AuthorConverter();
  private Author entity;
  private AuthorDto dto;
  private List<Author> entities;
  private List<AuthorDto> dtos;

  @BeforeEach
  public void setUp() {
    dto = new AuthorDto(1L, "oleg", "bubu", LocalDate.MAX, Sex.MALE);
    entity = new Author(1L, "oleg", "bubu", LocalDate.MAX, Sex.MALE);
    entities = new ArrayList<Author>(){{
      add(entity);
      add(entity);
    }};
     dtos = new ArrayList<AuthorDto>(){{
      add(dto);
      add(dto);
    }};
  }

  @Test
  void testToEntity() {
    Author actual = authorConverter.toEntity(dto);
    Assertions.assertEquals(entity, actual);
  }

  @Test
  void testFromEntity() {
    AuthorDto actual = authorConverter.fromEntity(entity);
    Assertions.assertEquals(dto, actual);
  }

  @Test
  void testFromEntityList() {
    List<AuthorDto> actual = authorConverter.fromListEntity(entities);
    Assertions.assertEquals(dtos, actual);
  }

  @Test
  void testToEntityList() {
    List<Author> actual = authorConverter.toListEntity(dtos);
    Assertions.assertEquals(entities, actual);
  }
}