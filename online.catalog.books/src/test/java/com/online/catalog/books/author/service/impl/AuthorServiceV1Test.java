package com.online.catalog.books.author.service.impl;

import com.online.catalog.books.author.converter.AuthorConverter;
import com.online.catalog.books.author.dto.AuthorDto;
import com.online.catalog.books.author.model.Author;
import com.online.catalog.books.author.model.enums.Sex;
import com.online.catalog.books.author.repository.AuthorRepository;
import com.online.catalog.books.common.exception.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

class AuthorServiceV1Test {

  @InjectMocks
  private AuthorServiceV1 authorServiceV1;

  @Mock
  private AuthorConverter authorConverter;
  @Mock
  private AuthorRepository authorRepository;

  private Author entity;
  private AuthorDto dto;

  @BeforeEach
  public void init() {
    MockitoAnnotations.openMocks(this);
    dto = new AuthorDto(1L, "oleg", "bubu", LocalDate.MAX, Sex.MALE);
    entity = new Author(1L, "oleg", "bubu", LocalDate.MAX, Sex.MALE);
  }

  @Test
  void testGetAllDto() {
    List<Author> entities = new ArrayList<Author>() {{
      add(entity);
      add(entity);
    }};
    List<AuthorDto> expected = new ArrayList<AuthorDto>() {{
      add(dto);
      add(dto);
    }};
    when(authorRepository.findAll()).thenReturn(entities);
    when(authorConverter.fromListEntity(entities)).thenReturn(expected);

    List<AuthorDto> actual = authorServiceV1.getAllDto();

    Assertions.assertEquals(expected, actual);
    verify(authorRepository, times(1)).findAll();
    verify(authorConverter, times(1)).fromListEntity(entities);
  }

  @Test
  void testGetDto() {
    Long someId = 1L;
    when(authorRepository.findById(someId)).thenReturn(java.util.Optional.ofNullable(entity));
    when(authorConverter.fromEntity(entity)).thenReturn(dto);

    AuthorDto actual = authorServiceV1.getDto(someId);

    Assertions.assertEquals(dto, actual);
    verify(authorRepository, times(1)).findById(someId);
    verify(authorConverter, times(1)).fromEntity(entity);
  }

  @Test
  void testGetDtoWhenNotAuthorWithSpecifiedId() {
    Long someId = 3L;
    when(authorRepository.findById(someId)).thenReturn(java.util.Optional.empty());

    Assertions.assertThrows(NotFoundException.class, () -> authorServiceV1.getDto(someId));
    verify(authorRepository, times(1)).findById(someId);
  }

  @Test
  void testCreate() {
    when(authorRepository.save(entity)).thenReturn(entity);
    when(authorConverter.toEntity(dto)).thenReturn(entity);
    when(authorConverter.fromEntity(entity)).thenReturn(dto);

    AuthorDto actual = authorServiceV1.create(dto);

    Assertions.assertEquals(dto, actual);
    verify(authorRepository, times(1)).save(entity);
    verify(authorConverter, times(1)).fromEntity(entity);
    verify(authorConverter, times(1)).toEntity(dto);
  }

  @Test
  void testEdit() {
    Long someId = 1L;
    when(authorRepository.save(entity)).thenReturn(entity);
    when(authorConverter.toEntity(dto)).thenReturn(entity);
    when(authorConverter.fromEntity(entity)).thenReturn(dto);
    when(authorRepository.existsById(someId)).thenReturn(true);

    AuthorDto actual = authorServiceV1.edit(dto, someId);

    Assertions.assertEquals(dto, actual);
    verify(authorRepository, times(1)).save(entity);
    verify(authorRepository, times(1)).existsById(someId);
    verify(authorConverter, times(1)).fromEntity(entity);
    verify(authorConverter, times(1)).toEntity(dto);
  }

  @Test
  void testEditWhenNotAuthorWithSpecifiedId() {
    Long someId = 1L;
    when(authorRepository.existsById(someId)).thenReturn(false);

    Assertions.assertThrows(NotFoundException.class, () -> authorServiceV1.edit(dto, someId));
    verify(authorRepository, times(1)).existsById(someId);
  }

  @Test
  void testGet() {
    Long someId = 1L;
    when(authorRepository.findById(someId)).thenReturn(java.util.Optional.ofNullable(entity));

    Author actual = authorServiceV1.get(someId);

    Assertions.assertEquals(entity, actual);
    verify(authorRepository, times(1)).findById(someId);
  }

  @Test
  void testGetWhenNotAuthorWithSpecifiedId() {
    Long someId = 3L;
    when(authorRepository.findById(someId)).thenReturn(java.util.Optional.empty());

    Assertions.assertThrows(NotFoundException.class, () -> authorServiceV1.get(someId));
    verify(authorRepository, times(1)).findById(someId);
  }
}