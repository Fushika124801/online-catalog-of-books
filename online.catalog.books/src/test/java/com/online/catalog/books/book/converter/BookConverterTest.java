package com.online.catalog.books.book.converter;

import com.online.catalog.books.author.converter.AuthorConverter;
import com.online.catalog.books.author.dto.AuthorDto;
import com.online.catalog.books.author.model.enums.Sex;
import com.online.catalog.books.book.dto.BookDto;
import com.online.catalog.books.book.model.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

class BookConverterTest {

  private final AuthorConverter authorConverter = new AuthorConverter();
  private final BookConverter bookConverter = new BookConverter(authorConverter);
  private Book entity;
  private BookDto dto;

  @BeforeEach
  public void setUp() {
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
  void testToEntity() {
    Book actual = bookConverter.toEntity(dto);
    Assertions.assertEquals(entity, actual);
  }

  @Test
  void testFromEntity() {
    BookDto actual = bookConverter.fromEntity(entity);
    Assertions.assertEquals(dto, actual);
  }

  @Test
  void testFromEntityList() {
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

    List<BookDto> actual = bookConverter.fromListEntity(entities);

    Assertions.assertEquals(expected, actual);
  }
}
