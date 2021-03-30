package com.online.catalog.books.user.service;

import com.online.catalog.books.book.dto.BookDto;
import com.online.catalog.books.user.model.User;

import java.util.List;

public interface UserService {

  User getCurrentUser();

  List<BookDto> addBook(Long bookId);

  List<BookDto> removeBook(Long bookId);

  List<BookDto> getBookList();

  Boolean isBookInBookList(Long bookId, User user);
}
