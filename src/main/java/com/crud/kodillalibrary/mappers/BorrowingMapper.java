package com.crud.kodillalibrary.mappers;

import com.crud.kodillalibrary.domain.BookCopy;
import com.crud.kodillalibrary.domain.Borrowing;
import com.crud.kodillalibrary.domain.BorrowingDto;
import com.crud.kodillalibrary.domain.Reader;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BorrowingMapper {

    public Borrowing mapToBorrowing(BorrowingDto borrowingDto, BookCopy bookCopy, Reader reader){
        return new Borrowing(
                borrowingDto.getId(),
                bookCopy,
                reader);
    }

    public BorrowingDto mapToBorrowingDto(Borrowing borrowing){
        return new BorrowingDto(
                borrowing.getId(),
                borrowing.getBookCopy().getId(),
                borrowing.getReader().getId(),
                borrowing.getBorrowDate(),
                borrowing.getReturnDate()
        );
    }

    public List<BorrowingDto> mapToBorrowingDtoList(List<Borrowing> borrowingList){
        return borrowingList.stream()
                .map(t -> new BorrowingDto(t.getId(), t.getBookCopy().getId(), t.getReader().getId(), t.getBorrowDate(), t.getReturnDate()))
                .collect(Collectors.toList());
    }
}
