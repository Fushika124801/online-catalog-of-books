package com.online.catalog.books.book.converter;

import com.online.catalog.books.book.dto.BookDto;
import com.online.catalog.books.book.model.Book;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookConverter {

    public Book toEntity(BookDto bookDto){
        return null;
    }

    public BookDto fromEntity(Book entity){
        return null;
    }

    public List<BookDto> fromEntityList(List<Book> entities) {
        return null;
    }
}
