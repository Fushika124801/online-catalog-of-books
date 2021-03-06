package com.online.catalog.books.book.dto;

import com.online.catalog.books.author.dto.AuthorDto;

import java.time.Year;
import java.util.List;
import java.util.Objects;

public class BookDto {

  private Long id;
  private String name;
  private Year yearPublication;
  private String publishingHouse;
  private List<AuthorDto> authors;

  public BookDto() {}

  public BookDto(
      Long id, List<AuthorDto> authors, String name, Year yearPublication, String publishingHouse) {
    this.id = id;
    this.authors = authors;
    this.name = name;
    this.yearPublication = yearPublication;
    this.publishingHouse = publishingHouse;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Year getYearPublication() {
    return yearPublication;
  }

  public void setYearPublication(Year yearPublication) {
    this.yearPublication = yearPublication;
  }

  public String getPublishingHouse() {
    return publishingHouse;
  }

  public void setPublishingHouse(String publishingHouse) {
    this.publishingHouse = publishingHouse;
  }

  public List<AuthorDto> getAuthors() {
    return authors;
  }

  public void setAuthors(List<AuthorDto> authors) {
    this.authors = authors;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    BookDto bookDto = (BookDto) o;
    return Objects.equals(authors, bookDto.authors)
        && Objects.equals(name, bookDto.name)
        && Objects.equals(yearPublication, bookDto.yearPublication)
        && Objects.equals(publishingHouse, bookDto.publishingHouse);
  }

  @Override
  public int hashCode() {
    return Objects.hash(authors, name, yearPublication, publishingHouse);
  }
}
