package com.online.catalog.books.book.search;

import com.online.catalog.books.author.model.enums.Sex;

import java.util.Date;
import java.util.Objects;

public class SearchRequest {

  private String name;
  private Date yearPublication;
  private String publishingHouse;
  private String nameOfAuthor;
  private Sex sexOfAuthor;
  private Date birthdayOfAuthor;

  public SearchRequest() {}

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

  public String getPublishingHouse() {
    return publishingHouse;
  }

  public void setPublishingHouse(String publishingHouse) {
    this.publishingHouse = publishingHouse;
  }

  public String getNameOfAuthor() {
    return nameOfAuthor;
  }

  public void setNameOfAuthor(String nameOfAuthor) {
    this.nameOfAuthor = nameOfAuthor;
  }

  public Sex getSexOfAuthor() {
    return sexOfAuthor;
  }

  public void setSexOfAuthor(Sex sexOfAuthor) {
    this.sexOfAuthor = sexOfAuthor;
  }

  public Date getBirthdayOfAuthor() {
    return birthdayOfAuthor;
  }

  public void setBirthdayOfAuthor(Date birthdayOfAuthor) {
    this.birthdayOfAuthor = birthdayOfAuthor;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    SearchRequest that = (SearchRequest) o;
    return Objects.equals(name, that.name) && Objects.equals(yearPublication, that.yearPublication) && Objects.equals(publishingHouse, that.publishingHouse) && Objects.equals(nameOfAuthor, that.nameOfAuthor) && sexOfAuthor == that.sexOfAuthor && Objects.equals(birthdayOfAuthor, that.birthdayOfAuthor);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, yearPublication, publishingHouse, nameOfAuthor, sexOfAuthor, birthdayOfAuthor);
  }
}
