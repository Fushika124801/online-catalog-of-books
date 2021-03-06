package com.online.catalog.books.author.service.impl;

import com.online.catalog.books.author.converter.AuthorConverter;
import com.online.catalog.books.author.dto.AuthorDto;
import com.online.catalog.books.author.model.Author;
import com.online.catalog.books.author.repository.AuthorRepository;
import com.online.catalog.books.author.service.AuthorService;
import com.online.catalog.books.book.model.Book;
import com.online.catalog.books.book.repository.BookRepository;
import com.online.catalog.books.common.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorServiceV1 implements AuthorService {

  private final AuthorRepository authorRepository;
  private final AuthorConverter authorConverter;
  private final BookRepository bookRepository;

  public AuthorServiceV1(AuthorRepository authorRepository, AuthorConverter authorConverter, BookRepository bookRepository) {
    this.authorRepository = authorRepository;
    this.authorConverter = authorConverter;
    this.bookRepository = bookRepository;
  }

  @Override
  public List<AuthorDto> getAllDto() {
    return authorConverter.fromListEntity(authorRepository.findAll());
  }

  @Override
  public AuthorDto getDto(Long authorId) {
    return authorConverter.fromEntity(get(authorId));
  }

  @Override
  public AuthorDto create(AuthorDto authorDto) {
    Author author = authorConverter.toEntity(authorDto);
    authorRepository.save(author);
    return authorConverter.fromEntity(author);
  }

  @Override
  public AuthorDto edit(AuthorDto authorDto, Long authorId) {
    checkAuthorExist(authorId);
    Author author = authorConverter.toEntity(authorDto);
    author.setId(authorId);
    authorRepository.save(author);

    return authorConverter.fromEntity(author);
  }

  @Override
  public void delete(Long authorId) {
    deleteAuthorsInBooks(authorId);
    authorRepository.deleteById(authorId);
  }

  @Override
  public Author get(Long authorId) {
    return authorRepository
      .findById(authorId)
      .orElseThrow(() -> new NotFoundException("Author not found!"));
  }

  private void checkAuthorExist(Long bookId) {
    if (!authorRepository.existsById(bookId)) {
      throw new NotFoundException("Author not found!");
    }
  }

  private void deleteAuthorsInBooks(Long authorId) {
    List<Author> authors = new ArrayList<>();
    authors.add(authorRepository.getOne(authorId));

    List<Book> books = bookRepository.findAllByAuthorsIn(authors);
    books
      .forEach(book ->
        book.getAuthors().removeIf(author ->
          author.getId().equals(authorId)));
    bookRepository.saveAll(books);
  }
}
