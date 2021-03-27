package com.online.catalog.books.book.controller;

import com.online.catalog.books.book.dto.BookDto;
import com.online.catalog.books.book.search.SearchRequest;
import com.online.catalog.books.book.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

  private final BookService bookService;

  public BookController(BookService bookService) {
    this.bookService = bookService;
  }

  @GetMapping
  public ResponseEntity<List<BookDto>> getAll() {
    return ResponseEntity.status(OK).body(bookService.getAllDto());
  }

  @GetMapping("/{bookId}")
  public ResponseEntity<BookDto> get(@PathVariable Long bookId) {
    return ResponseEntity.status(OK).body(bookService.getDto(bookId));
  }

  @PutMapping("/search")
  public ResponseEntity<List<BookDto>> search(@RequestBody SearchRequest searchRequest) {
    return ResponseEntity.status(OK).body(bookService.search(searchRequest));
  }

  @PostMapping
  public ResponseEntity<BookDto> create(@RequestBody BookDto bookDto) {
    return ResponseEntity.status(CREATED).body(bookService.create(bookDto));
  }

  @PutMapping("/{bookId}")
  public ResponseEntity<BookDto> edit(
          @RequestBody BookDto bookDto, @PathVariable Long bookId) {
    return ResponseEntity.status(OK).body(bookService.edit(bookDto, bookId));
  }

  @DeleteMapping
  public ResponseEntity<BookDto> delete(@RequestBody BookDto bookDto) {
    bookService.delete(bookDto);
    return ResponseEntity.status(OK).build();
  }
}
