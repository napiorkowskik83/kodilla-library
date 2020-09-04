package com.crud.kodillalibrary.mappers;

import com.crud.kodillalibrary.domain.Book;
import com.crud.kodillalibrary.domain.BookDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookMapper {

    public Book mapToBook(BookDto bookDto){
        return new Book(
                bookDto.getId(),
                bookDto.getTitle(),
                bookDto.getAuthor(),
                bookDto.getReleaseYear(),
                bookDto.getBookCopies()
        );
    }

    public BookDto mapToBookDto(Book book){
        return new BookDto(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getReleaseYear(),
                book.getBookCopies()
        );
    }

    public List<BookDto> mapToBookDtoList(List<Book> bookList){
        return bookList.stream()
                .map(t -> new BookDto(t.getId(), t.getTitle(),t.getAuthor(),t.getReleaseYear(),t.getBookCopies()))
                .collect(Collectors.toList());
    }
}
