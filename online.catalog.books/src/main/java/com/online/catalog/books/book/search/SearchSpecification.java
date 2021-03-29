package com.online.catalog.books.book.search;

import com.online.catalog.books.author.model.Author;
import com.online.catalog.books.author.model.enums.Sex;
import com.online.catalog.books.book.model.Book;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public class SearchSpecification implements Specification<Book> {

  private String name;
  private String publishingHouse;
  private String firstNameOfAuthor;
  private String lastNameOfAuthor;
  private Sex sexOfAuthor;
  private Year yearPublication;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "dd-MM-yyyy")
  private LocalDate birthdayOfAuthor;

  @Override
  public Predicate toPredicate(Root<Book> book, CriteriaQuery<?> query, CriteriaBuilder builder) {
    List<Predicate> predicates = new ArrayList<>();
    Join<Book, Author> authors = book.join("authors");

    if (name != null) {
      predicates.add(builder.like(builder.upper(book.get("name")), "%" + name.toUpperCase() + "%"));
    }
    if (publishingHouse != null) {
      predicates.add(
          builder.like(
              builder.upper(book.get("publishingHouse")),
              "%" + publishingHouse.toUpperCase() + "%"));
    }
    if (yearPublication != null) {
      predicates.add(builder.equal(book.get("yearPublication"), yearPublication));
    }
    if (firstNameOfAuthor != null) {
      predicates.add(
          builder.like(
              builder.upper(authors.get("firstName")), firstNameOfAuthor.toUpperCase() + "%"));
    }
    if (lastNameOfAuthor != null) {
      predicates.add(
          builder.like(
              builder.upper(authors.get("lastName")), lastNameOfAuthor.toUpperCase() + "%"));
    }
    if (sexOfAuthor != null) {
      predicates.add(builder.equal(authors.get("sex"), sexOfAuthor));
    }
    if (birthdayOfAuthor != null) {
      // When one book has two authors, to correctly search by birthday, add 1
      predicates.add(
          builder.and(authors.get("birthday").in(birthdayOfAuthor, birthdayOfAuthor.plusDays(1))));
    }

    query.distinct(true);

    return builder.and(predicates.toArray(new Predicate[0]));
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPublishingHouse() {
    return publishingHouse;
  }

  public void setPublishingHouse(String publishingHouse) {
    this.publishingHouse = publishingHouse;
  }

  public String getFirstNameOfAuthor() {
    return firstNameOfAuthor;
  }

  public void setFirstNameOfAuthor(String firstNameOfAuthor) {
    this.firstNameOfAuthor = firstNameOfAuthor;
  }

  public String getLastNameOfAuthor() {
    return lastNameOfAuthor;
  }

  public void setLastNameOfAuthor(String lastNameOfAuthor) {
    this.lastNameOfAuthor = lastNameOfAuthor;
  }

  public Sex getSexOfAuthor() {
    return sexOfAuthor;
  }

  public void setSexOfAuthor(Sex sexOfAuthor) {
    this.sexOfAuthor = sexOfAuthor;
  }

  public Year getYearPublication() {
    return yearPublication;
  }

  public void setYearPublication(Year yearPublication) {
    this.yearPublication = yearPublication;
  }

  public LocalDate getBirthdayOfAuthor() {
    return birthdayOfAuthor;
  }

  public void setBirthdayOfAuthor(LocalDate birthdayOfAuthor) {
    this.birthdayOfAuthor = birthdayOfAuthor;
  }
}
