package com.crud.kodillalibrary.mappers;

import com.crud.kodillalibrary.domain.BookCopy;
import com.crud.kodillalibrary.domain.BookCopyDto;
import com.crud.kodillalibrary.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookCopyMapper {

    public BookCopy mapToBookCopy(BookCopyDto bookCopyDto){
        return new BookCopy(
                bookCopyDto.getId(),
                bookCopyDto.getBook(),
                bookCopyDto.getStatus(),
                bookCopyDto.getBorrowingList()
        );
    }

    public BookCopyDto mapToBookCopyDto(BookCopy bookCopy){
        return new BookCopyDto(
                bookCopy.getId(),
                bookCopy.getBook(),
                bookCopy.getStatus(),
                bookCopy.getBorrowingList()
        );
    }

    public List<BookCopyDto> mapToBookCopyDtoList(List<BookCopy> bookCopyList){
        return bookCopyList.stream()
                .map(t -> new BookCopyDto(t.getId(), t.getBook(), t.getStatus(), t.getBorrowingList()))
                .collect(Collectors.toList());
    }
}