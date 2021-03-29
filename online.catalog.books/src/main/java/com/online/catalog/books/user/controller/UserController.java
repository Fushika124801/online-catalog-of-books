package com.online.catalog.books.user.controller;

import com.online.catalog.books.book.dto.BookDto;
import com.online.catalog.books.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/bookList")
  public ResponseEntity<List<BookDto>> getBookList() {
    return ResponseEntity.status(OK).body(userService.getBookList());
  }

  @PutMapping("/bookList/add/{bookId}")
  public ResponseEntity<List<BookDto>> addBook(@PathVariable Long bookId) {
    return ResponseEntity.status(OK).body(userService.addBook(bookId));
  }

  @PutMapping("/bookList/remove/{bookId}")
  public ResponseEntity<List<BookDto>> deleteBook(@PathVariable Long bookId) {
    return ResponseEntity.status(OK).body(userService.removeBook(bookId));
  }
}
