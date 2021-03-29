package com.online.catalog.books.author.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.online.catalog.books.author.model.enums.Sex;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class AuthorDto implements Serializable {

  private Long id;
  private String firstName;
  private String lastName;
  private Sex sex;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
  private LocalDate birthday;

  public AuthorDto() {
  }

  public AuthorDto(Long id, String firstName, String lastName, LocalDate birthday, Sex sex) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.birthday = birthday;
    this.sex = sex;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public LocalDate getBirthday() {
    return birthday;
  }

  public void setBirthday(LocalDate birthday) {
    this.birthday = birthday;
  }

  public Sex getSex() {
    return sex;
  }

  public void setSex(Sex sex) {
    this.sex = sex;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    AuthorDto authorDto = (AuthorDto) o;
    return Objects.equals(firstName, authorDto.firstName)
      && Objects.equals(lastName, authorDto.lastName)
      && Objects.equals(birthday, authorDto.birthday)
      && sex == authorDto.sex;
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstName, lastName, birthday, sex);
  }
}
