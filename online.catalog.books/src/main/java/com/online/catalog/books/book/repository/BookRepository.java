package com.online.catalog.books.book.repository;

import com.online.catalog.books.author.model.Author;
import com.online.catalog.books.book.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {

  List<Book> findAllByAuthorsIn(List<Author> authors);
}
