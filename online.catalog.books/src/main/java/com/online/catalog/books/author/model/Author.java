package com.online.catalog.books.author.model;

import com.online.catalog.books.author.model.enums.Sex;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
public class Author implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String firstName;
  private String lastName;
  private Date birthday;

  @Enumerated(EnumType.STRING)
  private Sex sex;

  public Author() {
  }

  public Author(Long id, String firstName, String lastName, Date birthday, Sex sex) {
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

  public Date getBirthday() {
    return birthday;
  }

  public void setBirthday(Date birthday) {
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
    Author author = (Author) o;
    return Objects.equals(firstName, author.firstName)
      && Objects.equals(lastName, author.lastName)
      && Objects.equals(birthday, author.birthday)
      && sex == author.sex;
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstName, lastName, birthday, sex);
  }
}
