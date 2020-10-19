package com.crud.kodillalibrary.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class BookCopyDto {
    private Long id;
    private Long bookId;
    private BookCopyStatus status;
}
