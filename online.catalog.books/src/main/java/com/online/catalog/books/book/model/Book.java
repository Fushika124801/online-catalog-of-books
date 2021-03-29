package com.online.catalog.books.book.model;

import com.online.catalog.books.author.model.Author;
import com.online.catalog.books.book.converter.YearConverter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Year;
import java.util.List;
import java.util.Objects;

@Entity
public class Book implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
  @JoinTable(
      name = "book_author",
      joinColumns = {@JoinColumn(name = "book_id")},
      inverseJoinColumns = {@JoinColumn(name = "author_id")})
  @Column(nullable = false)
  @NotNull
  private List<Author> authors;

  private String name;

  @Column(columnDefinition = "smallint")
  @Convert(converter = YearConverter.class)
  private Year yearPublication;

  private String publishingHouse;

  public Book() {}

  public Book(
      Long id, List<Author> authors, String name, Year yearPublication, String publishingHouse) {
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

  public List<Author> getAuthor() {
    return authors;
  }

  public void setAuthor(List<Author> authors) {
    this.authors = authors;
  }

  public String getPublishingHouse() {
    return publishingHouse;
  }

  public void setPublishingHouse(String publishingHouse) {
    this.publishingHouse = publishingHouse;
  }

  public List<Author> getAuthors() {
    return authors;
  }

  public void setAuthors(List<Author> authors) {
    this.authors = authors;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Book book = (Book) o;
    return Objects.equals(authors, book.authors)
        && Objects.equals(name, book.name)
        && Objects.equals(yearPublication, book.yearPublication)
        && Objects.equals(publishingHouse, book.publishingHouse);
  }

  @Override
  public int hashCode() {
    return Objects.hash(authors, name, yearPublication, publishingHouse);
  }
}
