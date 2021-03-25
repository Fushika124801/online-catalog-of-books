package com.online.catalog.books.book.service.impl;

import com.online.catalog.books.author.model.Author;
import com.online.catalog.books.book.converter.BookConverter;
import com.online.catalog.books.book.dto.BookDto;
import com.online.catalog.books.book.model.Book;
import com.online.catalog.books.book.repository.BookRepository;
import com.online.catalog.books.book.search.SearchRequest;
import com.online.catalog.books.book.service.BookService;
import com.online.catalog.books.common.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceV1 implements BookService {

  private final BookRepository bookRepository;
  private final BookConverter bookConverter;

  public BookServiceV1(BookRepository bookRepository, BookConverter bookConverter) {
    this.bookRepository = bookRepository;
    this.bookConverter = bookConverter;
  }

  @Override
  public List<BookDto> getAllDto() {
    return bookConverter.fromEntityList(bookRepository.findAll());
  }

  @Override
  public BookDto getDto(Long bookId) {
    return bookConverter.fromEntity(get(bookId));
  }

  @Override
  public BookDto create(BookDto bookDto) {
    Book book = bookConverter.toEntity(bookDto);
    bookRepository.save(book);

    return bookConverter.fromEntity(book);
  }

  @Override
  public BookDto edit(BookDto bookDto, Long bookId) {
    if (bookRepository.existsById(bookId)) {
      Book book = bookConverter.toEntity(bookDto);
      book.setId(bookId);
      bookRepository.save(book);

      return bookConverter.fromEntity(book);
    } else {
      throw new NotFoundException("Author not found!");
    }
  }

  @Override
  public void delete(BookDto bookDto) {
    bookRepository.delete(bookConverter.toEntity(bookDto));
  }

  @Override
  public List<BookDto> search(SearchRequest searchRequest) {
    return null;
  }

  private Book get(Long bookId) {
    return bookRepository
        .findById(bookId)
        .orElseThrow(() -> new NotFoundException("Book not found!"));
  }
}
