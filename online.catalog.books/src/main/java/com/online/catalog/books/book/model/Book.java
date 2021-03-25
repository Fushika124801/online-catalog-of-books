package com.online.catalog.books.book.model;

import com.online.catalog.books.author.model.Author;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
public class Book implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToMany(cascade = CascadeType.ALL)
//  @JoinTable(
//          name = "book_author",
//          joinColumns = @JoinColumn(name = "book_id"),
//          inverseJoinColumns = @JoinColumn(name = "author_id"))
  private List<Author> authors;

  private String name;
  private Date yearPublication;
  private String publishingHouse;

  public Book() {}

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

  public Date getYearPublication() {
    return yearPublication;
  }

  public void setYearPublication(Date yearPublication) {
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
}
