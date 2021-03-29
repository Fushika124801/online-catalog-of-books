package com.online.catalog.books.user.service.impl;

import com.online.catalog.books.author.converter.AuthorConverter;
import com.online.catalog.books.author.dto.AuthorDto;
import com.online.catalog.books.author.model.enums.Sex;
import com.online.catalog.books.book.converter.BookConverter;
import com.online.catalog.books.book.dto.BookDto;
import com.online.catalog.books.book.model.Book;
import com.online.catalog.books.book.service.BookService;
import com.online.catalog.books.user.model.User;
import com.online.catalog.books.user.repository.UserRepository;
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

class UserServiceV1Test {

  @InjectMocks private UserServiceV1 userServiceV1;

  @Mock private BookConverter bookConverter;
  @Mock private BookService bookService;
  @Mock private UserRepository userRepository;

  private User user;
  private List<Book> books;
  private List<BookDto> bookDtos;
  private BookDto bookDto;
  private Book bookEntity;

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

    bookDto = new BookDto(1L, authorDtos, "book of devil", Year.of(666), "Hell");
    bookEntity =
        new Book(
            1L, authorConverter.toListEntity(authorDtos), "book of devil", Year.of(666), "Hell");
    books =
        new ArrayList<Book>() {
          {
            add(bookEntity);
            add(bookEntity);
          }
        };
    bookDtos =
        new ArrayList<BookDto>() {
          {
            add(bookDto);
            add(bookDto);
          }
        };

    user = new User();
    user.setBooks(books);
  }

  @Test
  void testGetBookList() {
    when(bookConverter.fromListEntity(books)).thenReturn(bookDtos);
    when(userRepository.findByUsername(any())).thenReturn(java.util.Optional.of(user));

    List<BookDto> actual = userServiceV1.getBookList();

    Assertions.assertEquals(bookDtos, actual);
    verify(bookConverter, times(1)).fromListEntity(books);
    verify(userRepository, times(1)).findByUsername(any());
  }

  @Test
  void testAddBook() {
    Long someId = 1L;
    when(bookConverter.fromListEntity(books)).thenReturn(bookDtos);
    when(userRepository.findByUsername(any())).thenReturn(java.util.Optional.of(user));
    when(bookService.get(someId)).thenReturn(bookEntity);
    when(userRepository.save(user)).thenReturn(user);

    List<BookDto> actual = userServiceV1.addBook(someId);
    bookDtos.add(bookDto);

    Assertions.assertEquals(bookDtos, actual);
    verify(bookConverter, times(1)).fromListEntity(books);
    verify(userRepository, times(1)).findByUsername(any());
    verify(userRepository, times(1)).save(user);
    verify(bookService, times(1)).get(someId);
  }

  @Test
  void testRemoveBook() {
    Long someId = 1L;
    when(bookConverter.fromListEntity(books)).thenReturn(bookDtos);
    when(userRepository.findByUsername(any())).thenReturn(java.util.Optional.of(user));
    when(userRepository.save(user)).thenReturn(user);

    List<BookDto> actual = userServiceV1.removeBook(someId);
    bookDtos.remove(bookDto);

    Assertions.assertEquals(bookDtos, actual);
    verify(bookConverter, times(1)).fromListEntity(books);
    verify(userRepository, times(1)).findByUsername(any());
    verify(userRepository, times(1)).save(user);
  }
}
