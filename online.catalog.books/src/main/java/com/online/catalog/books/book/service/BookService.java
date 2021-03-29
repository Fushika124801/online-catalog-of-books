package com.online.catalog.books.book.service;

import com.online.catalog.books.book.dto.BookDto;
import com.online.catalog.books.book.model.Book;
import com.online.catalog.books.book.search.SearchSpecification;

import java.util.List;

public interface BookService {

  List<BookDto> getAllDto();

  BookDto getDto(Long bookId);

  Book get(Long bookId);

  BookDto create(BookDto bookDto);

  BookDto edit(BookDto bookDto, Long bookId);

  void delete(Long bookId);

  List<BookDto> search(SearchSpecification searchSpecification);
}
