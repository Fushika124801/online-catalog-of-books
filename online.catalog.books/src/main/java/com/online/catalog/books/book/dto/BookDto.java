package com.online.catalog.books.book.dto;

import com.online.catalog.books.author.dto.AuthorDto;
import com.online.catalog.books.author.model.Author;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Date;
import java.util.Objects;

public class BookDto {

    private Long id;
    private AuthorDto author;
    private String name;
    private Date yearPublication;
    private String publishingHouse;

    public BookDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AuthorDto getAuthor() {
        return author;
    }

    public void setAuthor(AuthorDto author) {
        this.author = author;
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

    public String getPublishingHouse() {
        return publishingHouse;
    }

    public void setPublishingHouse(String publishingHouse) {
        this.publishingHouse = publishingHouse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookDto bookDto = (BookDto) o;
        return Objects.equals(author, bookDto.author) && Objects.equals(name, bookDto.name) && Objects.equals(yearPublication, bookDto.yearPublication) && Objects.equals(publishingHouse, bookDto.publishingHouse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(author, name, yearPublication, publishingHouse);
    }
}
