package com.online.catalog.books.author.service.impl;

import com.online.catalog.books.author.converter.AuthorConverter;
import com.online.catalog.books.author.dto.AuthorDto;
import com.online.catalog.books.author.model.Author;
import com.online.catalog.books.author.repository.AuthorRepository;
import com.online.catalog.books.author.service.AuthorService;
import com.online.catalog.books.common.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceV1 implements AuthorService {

  private final AuthorRepository authorRepository;
  private final AuthorConverter authorConverter;

  public AuthorServiceV1(AuthorRepository authorRepository, AuthorConverter authorConverter) {
    this.authorRepository = authorRepository;
    this.authorConverter = authorConverter;
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
}
