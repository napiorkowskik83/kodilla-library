package com.crud.kodillalibrary.controllers;

import com.crud.kodillalibrary.domain.*;
import com.crud.kodillalibrary.mappers.BookCopyMapper;
import com.crud.kodillalibrary.services.BookCopyDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/library/bookCopy")
@CrossOrigin(origins = "*")
public class BookCopyController {
    private final BookCopyDbService bookCopyDbService;
    private final BookCopyMapper bookCopyMapper;

    @Autowired
    public BookCopyController(BookCopyDbService bookCopyDbService,
                              BookCopyMapper bookCopyMapper) {
        this.bookCopyDbService = bookCopyDbService;
        this.bookCopyMapper = bookCopyMapper;
    }

    @PostMapping(value = "addBookCopy")
    public void addBookCopy(@RequestParam Long bookId) throws BookNotFoundException {
        bookCopyDbService.addBookCopy(bookId);
    }

    @PutMapping(value = "setStatusToAvailable")
    public BookCopyDto setStatusToAvailable(@RequestParam Long bookCopyId) throws BookCopyNotFoundException {
        return bookCopyMapper.mapToBookCopyDto(bookCopyDbService.setStatusToAvailable(bookCopyId));
    }

    @PutMapping(value = "setStatusToBorrowed")
    public BookCopyDto setStatusToBorrowed(@RequestParam Long bookCopyId) throws BookCopyNotFoundException {
        return bookCopyMapper.mapToBookCopyDto(bookCopyDbService.setStatusToBorrowed(bookCopyId));
    }

    @PutMapping(value = "setStatusToLost")
    public BookCopyDto setStatusToLost(@RequestParam Long bookCopyId) throws BookCopyNotFoundException {
        return bookCopyMapper.mapToBookCopyDto(bookCopyDbService.setStatusToLost(bookCopyId));
    }

    @PutMapping(value = "setStatusToTattered")
    public BookCopyDto setStatusToTattered(@RequestParam Long bookCopyId) throws BookCopyNotFoundException {
        return bookCopyMapper.mapToBookCopyDto(bookCopyDbService.setStatusToTattered(bookCopyId));
    }

    @GetMapping(value = "getAllBookCopies")
    public List<BookCopyDto> getAllBookCopies() {
        return bookCopyMapper.mapToBookCopyDtoList(bookCopyDbService.getAllBookCopies());
    }

    @GetMapping(value = "getAllAvailableBookCopies")
    public List<BookCopyDto> getAllAvailableBookCopies() {
        return bookCopyMapper.mapToBookCopyDtoList(bookCopyDbService.getAllAvailableBookCopies());
    }

    @GetMapping(value = "getAllBookCopiesOfBook")
    public List<BookCopyDto> getAllBookCopiesOfBook(@RequestParam Long bookId) {
        return bookCopyMapper.mapToBookCopyDtoList(bookCopyDbService.getAllBookCopiesOfBook(bookId));
    }

    @GetMapping(value = "getAvailableBookCopiesOfBook")
    public List<BookCopyDto> getAvailableBookCopiesOfBook(@RequestParam Long bookId) {
        return bookCopyMapper.mapToBookCopyDtoList(bookCopyDbService.getAvailableBookCopiesOfBook(bookId));
    }

    @GetMapping(value = "getAllBookCopiesOfTitle")
    public List<BookCopyDto> getAllBookCopiesOfTitle(@RequestParam String title) {
        return bookCopyMapper.mapToBookCopyDtoList(bookCopyDbService.getAllBookCopiesOfTitle(title));
    }

    @GetMapping(value = "getAvailableBookCopiesOfTitle")
    public List<BookCopyDto> getAvailableBookCopiesOfTitle(@RequestParam String title) {
        return bookCopyMapper.mapToBookCopyDtoList(bookCopyDbService.getAvailableBookCopiesOfTitle(title));
    }

    @GetMapping(value = "getBookCopy")
    public BookCopyDto getBookCopy(@RequestParam Long bookCopyId) throws BookCopyNotFoundException {
        return bookCopyMapper.mapToBookCopyDto(bookCopyDbService.getBookCopy(bookCopyId));
    }
}
