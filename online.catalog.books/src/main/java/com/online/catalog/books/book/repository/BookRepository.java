package com.online.catalog.books.book.repository;

import com.online.catalog.books.book.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.function.Predicate;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>, QuerydslPredicateExecutor<Book> {
//
//  public List<Book> findAll(Predicate predicate);
}

