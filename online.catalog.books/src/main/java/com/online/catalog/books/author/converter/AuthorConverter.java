package com.online.catalog.books.author.converter;

import com.online.catalog.books.author.dto.AuthorDto;
import com.online.catalog.books.author.model.Author;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuthorConverter {

  public AuthorDto fromEntity(Author entity) {
    return new AuthorDto(
        entity.getId(),
        entity.getFirstName(),
        entity.getLastName(),
        entity.getBirthday(),
        entity.getSex());
  }

  public List<AuthorDto> fromListEntity(List<Author> entities) {
    return entities.stream().map(this::fromEntity).collect(Collectors.toList());
  }

  public Author toEntity(AuthorDto authorDto) {

    return null;
  }
}
