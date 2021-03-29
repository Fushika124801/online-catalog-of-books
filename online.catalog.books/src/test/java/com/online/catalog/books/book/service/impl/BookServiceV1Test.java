package com.online.catalog.books.book.service.impl;

import com.online.catalog.books.author.converter.AuthorConverter;
import com.online.catalog.books.author.dto.AuthorDto;
import com.online.catalog.books.author.model.enums.Sex;
import com.online.catalog.books.book.converter.BookConverter;
import com.online.catalog.books.book.dto.BookDto;
import com.online.catalog.books.book.model.Book;
import com.online.catalog.books.book.repository.BookRepository;
import com.online.catalog.books.book.search.SearchSpecification;
import com.online.catalog.books.common.exception.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

class BookServiceV1Test {

  @InjectMocks private BookServiceV1 bookServiceV1;

  @Mock private BookConverter bookConverter;
  @Mock private BookRepository bookRepository;

  private Book entity;
  private BookDto dto;

  @BeforeEach
  public void init() {
    MockitoAnnotations.openMocks(this);
    AuthorConverter authorConverter = new AuthorConverter();
    AuthorDto authorDto = new AuthorDto(1L, "oleg", "bubu", LocalDate.MAX, Sex.MALE);
    List<AuthorDto> authorDtos =
        new ArrayList<AuthorDto>() {
          {
            add(authorDto);
            add(authorDto);
          }
        };
    dto = new BookDto(1L, authorDtos, "book of devil", Year.of(666), "Hell");
    entity =
        new Book(
            1L, authorConverter.toListEntity(authorDtos), "book of devil", Year.of(666), "Hell");
  }

  @Test
  void testGetAllDto() {
    List<Book> entities =
        new ArrayList<Book>() {
          {
            add(entity);
            add(entity);
          }
        };
    List<BookDto> expected =
        new ArrayList<BookDto>() {
          {
            add(dto);
            add(dto);
          }
        };

    when(bookRepository.findAll()).thenReturn(entities);
    when(bookConverter.fromListEntity(entities)).thenReturn(expected);

    List<BookDto> actual = bookServiceV1.getAllDto();

    Assertions.assertEquals(expected, actual);
    verify(bookRepository, times(1)).findAll();
    verify(bookConverter, times(1)).fromListEntity(entities);
  }

  @Test
  void testGetDto() {
    Long someId = 1L;
    when(bookRepository.findById(someId)).thenReturn(java.util.Optional.ofNullable(entity));
    when(bookConverter.fromEntity(entity)).thenReturn(dto);

    BookDto actual = bookServiceV1.getDto(someId);

    Assertions.assertEquals(dto, actual);
    verify(bookRepository, times(1)).findById(someId);
    verify(bookConverter, times(1)).fromEntity(entity);
  }

  @Test
  void testGetDtoWhenNotAuthorWithSpecifiedId() {
    Long someId = 3L;
    when(bookRepository.findById(someId)).thenReturn(java.util.Optional.empty());

    Assertions.assertThrows(NotFoundException.class, () -> bookServiceV1.getDto(someId));
    verify(bookRepository, times(1)).findById(someId);
  }

  @Test
  void testCreate() {
    when(bookRepository.save(entity)).thenReturn(entity);
    when(bookConverter.toEntity(dto)).thenReturn(entity);
    when(bookConverter.fromEntity(entity)).thenReturn(dto);

    BookDto actual = bookServiceV1.create(dto);

    Assertions.assertEquals(dto, actual);
    verify(bookRepository, times(1)).save(entity);
    verify(bookConverter, times(1)).fromEntity(entity);
    verify(bookConverter, times(1)).toEntity(dto);
  }

  @Test
  void testEdit() {
    Long someId = 1L;
    when(bookRepository.save(entity)).thenReturn(entity);
    when(bookConverter.toEntity(dto)).thenReturn(entity);
    when(bookConverter.fromEntity(entity)).thenReturn(dto);
    when(bookRepository.existsById(someId)).thenReturn(true);

    BookDto actual = bookServiceV1.edit(dto, someId);

    Assertions.assertEquals(dto, actual);
    verify(bookRepository, times(1)).existsById(someId);
    verify(bookRepository, times(1)).save(entity);
    verify(bookConverter, times(1)).fromEntity(entity);
    verify(bookConverter, times(1)).toEntity(dto);
  }

  @Test
  void testEditWhenNotAuthorWithSpecifiedId() {
    Long someId = 1L;
    when(bookRepository.existsById(someId)).thenReturn(false);

    Assertions.assertThrows(NotFoundException.class, () -> bookServiceV1.edit(dto, someId));
    verify(bookRepository, times(1)).existsById(someId);
  }

  @Test
  void testGet() {
    Long someId = 1L;
    when(bookRepository.findById(someId)).thenReturn(java.util.Optional.ofNullable(entity));

    Book actual = bookServiceV1.get(someId);

    Assertions.assertEquals(entity, actual);
    verify(bookRepository, times(1)).findById(someId);
  }

  @Test
  void testGetWhenNotAuthorWithSpecifiedId() {
    Long someId = 3L;
    when(bookRepository.findById(someId)).thenReturn(java.util.Optional.empty());

    Assertions.assertThrows(NotFoundException.class, () -> bookServiceV1.get(someId));
    verify(bookRepository, times(1)).findById(someId);
  }

  @Test
  void testSearch() {
    List<Book> entities =
        new ArrayList<Book>() {
          {
            add(entity);
            add(entity);
          }
        };
    List<BookDto> expected =
        new ArrayList<BookDto>() {
          {
            add(dto);
            add(dto);
          }
        };
    SearchSpecification searchSpecification = new SearchSpecification();
    when(bookRepository.findAll(searchSpecification)).thenReturn(entities);
    when(bookConverter.fromListEntity(entities)).thenReturn(expected);

    List<BookDto> actual = bookServiceV1.search(searchSpecification);

    Assertions.assertEquals(expected, actual);
    verify(bookRepository, times(1)).findAll(searchSpecification);
    verify(bookConverter, times(1)).fromListEntity(entities);
  }
}
