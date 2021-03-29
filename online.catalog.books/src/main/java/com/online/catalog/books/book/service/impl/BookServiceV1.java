package com.online.catalog.books.book.service.impl;

import com.online.catalog.books.book.converter.BookConverter;
import com.online.catalog.books.book.dto.BookDto;
import com.online.catalog.books.book.model.Book;
import com.online.catalog.books.book.repository.BookRepository;
import com.online.catalog.books.book.search.SearchSpecification;
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
    return bookConverter.fromListEntity(bookRepository.findAll());
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
    checkBookExist(bookId);
    Book book = bookConverter.toEntity(bookDto);
    book.setId(bookId);
    bookRepository.save(book);

    return bookConverter.fromEntity(book);
  }

  @Override
  public void delete(Long bookId) {
    checkBookExist(bookId);
    bookRepository.deleteById(bookId);
  }

  @Override
  public List<BookDto> search(SearchSpecification searchSpecification) {
    return bookConverter.fromListEntity(bookRepository.findAll(searchSpecification));
  }

  @Override
  public Book get(Long bookId) {
    return bookRepository
        .findById(bookId)
        .orElseThrow(() -> new NotFoundException("Book not found!"));
  }

  private void checkBookExist(Long bookId) {
    if (!bookRepository.existsById(bookId)) {
      throw new NotFoundException("Book not found!");
    }
  }
}
