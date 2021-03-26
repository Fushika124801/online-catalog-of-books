package com.online.catalog.books.book.converter;

import com.online.catalog.books.author.converter.AuthorConverter;
import com.online.catalog.books.book.dto.BookDto;
import com.online.catalog.books.book.model.Book;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookConverter {

  private final AuthorConverter authorConverter;

  public BookConverter(AuthorConverter authorConverter) {
    this.authorConverter = authorConverter;
  }

  public Book toEntity(BookDto bookDto) {
    return new Book(bookDto.getId(),
      authorConverter.toListEntity(bookDto.getAuthors()),
      bookDto.getName(),
      bookDto.getYearPublication(),
      bookDto.getPublishingHouse());
  }

  public BookDto fromEntity(Book entity) {
    return new BookDto(entity.getId(),
      authorConverter.fromListEntity(entity.getAuthors()),
      entity.getName(),
      entity.getYearPublication(),
      entity.getPublishingHouse());
  }

  public List<BookDto> fromEntityList(List<Book> entities) {
    return entities.stream().map(this::fromEntity).collect(Collectors.toList());
  }
}
