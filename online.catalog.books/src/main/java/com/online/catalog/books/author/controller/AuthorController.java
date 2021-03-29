package com.online.catalog.books.author.controller;

import com.online.catalog.books.author.dto.AuthorDto;
import com.online.catalog.books.author.service.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/v1/authors")
public class AuthorController {

  private final AuthorService authorService;

  public AuthorController(AuthorService authorService) {
    this.authorService = authorService;
  }

  @GetMapping
  public ResponseEntity<List<AuthorDto>> getAll() {
    return ResponseEntity.status(OK).body(authorService.getAllDto());
  }

  @GetMapping("/{authorId}")
  public ResponseEntity<AuthorDto> get(@PathVariable Long authorId) {
    return ResponseEntity.status(OK).body(authorService.getDto(authorId));
  }

  @PostMapping
  public ResponseEntity<AuthorDto> create(@RequestBody AuthorDto authorDto) {
    return ResponseEntity.status(CREATED).body(authorService.create(authorDto));
  }

  @PutMapping("/{authorId}")
  public ResponseEntity<AuthorDto> edit(
      @RequestBody AuthorDto authorDto, @PathVariable Long authorId) {
    return ResponseEntity.status(OK).body(authorService.edit(authorDto, authorId));
  }

  @DeleteMapping("/{authorId}")
  public ResponseEntity<AuthorDto> delete(@PathVariable Long authorId) {
    authorService.delete(authorId);
    return ResponseEntity.status(NO_CONTENT).build();
  }
}
