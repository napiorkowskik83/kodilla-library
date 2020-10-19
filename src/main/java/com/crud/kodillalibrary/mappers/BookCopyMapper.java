package com.crud.kodillalibrary.mappers;

import com.crud.kodillalibrary.domain.Book;
import com.crud.kodillalibrary.domain.BookCopy;
import com.crud.kodillalibrary.domain.BookCopyDto;
import com.crud.kodillalibrary.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookCopyMapper {

    @Autowired
    BookRepository bookRepository;

    public BookCopy mapToBookCopy(BookCopyDto bookCopyDto, Book book){
        return new BookCopy(
                bookCopyDto.getId(),
                book,
                bookCopyDto.getStatus()
        );
    }

    public BookCopyDto mapToBookCopyDto(BookCopy bookCopy){
        return new BookCopyDto(
                bookCopy.getId(),
                bookCopy.getBook().getId(),
                bookCopy.getStatus()
        );
    }

    public List<BookCopyDto> mapToBookCopyDtoList(List<BookCopy> bookCopyList){
        return bookCopyList.stream()
                .map(t -> new BookCopyDto(t.getId(), t.getBook().getId(), t.getStatus()))
                .collect(Collectors.toList());
    }
}
