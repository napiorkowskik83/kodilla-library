package com.crud.kodillalibrary.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.Date;

@Getter
@AllArgsConstructor
public class BorrowingDto {
    private Long id;
    private BookCopy bookCopy;
    private Reader reader;
    private Date borrowDate;
    private Date returnDate;
}
