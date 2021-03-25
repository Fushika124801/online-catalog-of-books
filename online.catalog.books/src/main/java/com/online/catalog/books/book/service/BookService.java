package com.online.catalog.books.book.service;

import com.online.catalog.books.book.dto.BookDto;
import com.online.catalog.books.book.search.SearchRequest;

import java.util.List;

public interface BookService {

  List<BookDto> getAllDto();

  BookDto getDto(Long bookId);

  BookDto create(BookDto bookDto);

  BookDto edit(BookDto bookDto, Long bookId);

  void delete(BookDto bookDto);

  List<BookDto> search(SearchRequest searchRequest);
}
