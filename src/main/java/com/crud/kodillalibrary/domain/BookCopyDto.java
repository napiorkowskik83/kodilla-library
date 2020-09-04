package com.crud.kodillalibrary.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class BookCopyDto {
    private Long id;
    private Book book;
    private String status;
    List<Borrowing> borrowingList;
}
