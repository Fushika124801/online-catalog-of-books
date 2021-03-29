package com.online.catalog.books.user.service.impl;

import com.online.catalog.books.book.converter.BookConverter;
import com.online.catalog.books.book.dto.BookDto;
import com.online.catalog.books.book.service.BookService;
import com.online.catalog.books.common.exception.NotFoundException;
import com.online.catalog.books.user.model.User;
import com.online.catalog.books.user.repository.UserRepository;
import com.online.catalog.books.user.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceV1 implements UserService {

  private final UserRepository userRepository;
  private final BookService bookService;
  private final BookConverter bookConverter;

  public UserServiceV1(UserRepository userRepository, BookService bookService, BookConverter bookConverter) {
    this.userRepository = userRepository;
    this.bookService = bookService;
    this.bookConverter = bookConverter;
  }

  //todo getCurrentUser
  @Override
  public User getCurrentUser() {
    return userRepository.findByUsername("puper")
      .orElseThrow(() -> new NotFoundException("Current user not found!"));
  }

  @Override
  public List<BookDto> addBook(Long bookId) {
    User user = getCurrentUser();
    user.getBooks().add(bookService.get(bookId));
    userRepository.save(user);

    return bookConverter.fromListEntity(user.getBooks());
  }

  @Override
  public List<BookDto> removeBook (Long bookId) {
    User user = getCurrentUser();
    user.getBooks().removeIf(book -> book.getId().equals(bookId));
    userRepository.save(user);

    return bookConverter.fromListEntity(user.getBooks());
  }

  @Override
  public List<BookDto> getBookList() {
    return bookConverter.fromListEntity(getCurrentUser().getBooks());
  }

}
