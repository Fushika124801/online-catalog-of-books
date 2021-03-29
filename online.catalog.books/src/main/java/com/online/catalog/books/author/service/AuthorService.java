package com.online.catalog.books.author.service;

import com.online.catalog.books.author.dto.AuthorDto;
import com.online.catalog.books.author.model.Author;

import java.util.List;

public interface AuthorService {

  List<AuthorDto> getAllDto();

  AuthorDto getDto(Long authorId);

  Author get(Long authorId);

  AuthorDto create(AuthorDto authorDto);

  AuthorDto edit(AuthorDto authorDto, Long authorId);

  void delete(Long authorId);
}
