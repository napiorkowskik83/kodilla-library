package com.crud.kodillalibrary.mappers;

import com.crud.kodillalibrary.domain.Borrowing;
import com.crud.kodillalibrary.domain.BorrowingDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BorrowingMapper {

    public BorrowingDto mapToBorrowingDto(Borrowing borrowing){
        return new BorrowingDto(
                borrowing.getId(),
                borrowing.getBookCopy(),
                borrowing.getReader(),
                borrowing.getBorrowDate(),
                borrowing.getReturnDate()
        );
    }

    public Borrowing mapToBorrowing(BorrowingDto borrowingDto){
        return new Borrowing(
                borrowingDto.getId(),
                borrowingDto.getBookCopy(),
                borrowingDto.getReader(),
                borrowingDto.getBorrowDate(),
                borrowingDto.getReturnDate()
        );
    }

    public List<BorrowingDto> mapToBorrowingDtoList(List<Borrowing> borrowingList){
        return borrowingList.stream()
                .map(t -> new BorrowingDto(t.getId(), t.getBookCopy(), t.getReader(), t.getBorrowDate(), t.getReturnDate()))
                .collect(Collectors.toList());
    }
}
