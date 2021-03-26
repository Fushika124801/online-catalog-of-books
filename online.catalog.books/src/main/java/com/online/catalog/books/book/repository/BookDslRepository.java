package com.online.catalog.books.book.repository;

import com.online.catalog.books.book.model.Book;
import com.online.catalog.books.book.model.QBook;
import com.online.catalog.books.book.search.SearchRequest;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class BookDslRepository {

  @PersistenceContext
  private EntityManager em;

  public void search(SearchRequest searchRequest) {
    JPAQuery<Book> query = new JPAQuery<>(em);
    QBook book = QBook.book;

//    query.from(book).where(book.authors.any().firstName.contains(searchRequest.));
  }
}
