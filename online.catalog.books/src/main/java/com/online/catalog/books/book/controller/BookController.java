package com.online.catalog.books.book.controller;

import com.online.catalog.books.book.dto.BookDto;
import com.online.catalog.books.book.search.SearchSpecification;
import com.online.catalog.books.book.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

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

  @GetMapping("/search")
  public ResponseEntity<List<BookDto>> search(SearchSpecification searchSpecification) {
    return ResponseEntity.status(OK).body(bookService.search(searchSpecification));
  }

  @PostMapping
  public ResponseEntity<BookDto> create(@Valid @RequestBody BookDto bookDto) {
    return ResponseEntity.status(CREATED).body(bookService.create(bookDto));
  }

  @PutMapping("/{bookId}")
  public ResponseEntity<BookDto> edit(
    @RequestBody BookDto bookDto, @PathVariable Long bookId) {
    return ResponseEntity.status(OK).body(bookService.edit(bookDto, bookId));
  }

  @DeleteMapping("/{bookId}")
  public ResponseEntity<BookDto> delete(@PathVariable Long bookId) {
    bookService.delete(bookId);
    return ResponseEntity.status(NO_CONTENT).build();
  }
}
