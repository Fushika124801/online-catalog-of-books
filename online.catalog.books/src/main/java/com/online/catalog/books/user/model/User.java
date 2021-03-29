package com.online.catalog.books.user.model;

import com.online.catalog.books.book.model.Book;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String username;
  private String password;

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(
    name = "user_book",
    joinColumns = {@JoinColumn(name = "user_id")},
    inverseJoinColumns = {@JoinColumn(name = "book_id")}
  )
  private List<Book> books;

  public User() {
  }


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public List<Book> getBooks() {
    return books;
  }

  public void setBooks(List<Book> books) {
    this.books = books;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    User user = (User) o;
    return Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(books, user.books);
  }

  @Override
  public int hashCode() {
    return Objects.hash(username, password, books);
  }
}
