package com.crud.kodillalibrary.controllers;

import com.crud.kodillalibrary.domain.BookDto;
import com.crud.kodillalibrary.mappers.BookMapper;
import com.crud.kodillalibrary.services.BookDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/library/book")
@CrossOrigin(origins = "*")
public class BookController {
    private final BookDbService bookDbService;
    private final BookMapper bookMapper;

    @Autowired
    public BookController(BookDbService bookDbService, BookMapper bookMapper){
        this.bookDbService = bookDbService;
        this.bookMapper = bookMapper;
    }

    @PostMapping(value = "addBook", consumes = APPLICATION_JSON_VALUE)
    public void addBook(@RequestBody BookDto bookDto) {
        bookDbService.saveBook(bookMapper.mapToBook(bookDto));
    }

    @PutMapping(value = "updateBook", consumes = APPLICATION_JSON_VALUE)
    public void updateBook(@RequestBody BookDto bookDto) throws BookNotFoundException {
        bookDbService.updateBook(bookMapper.mapToBook(bookDto));
    }

    @GetMapping(value = "getBooks")
    public List<BookDto> getBooks() {
        return bookMapper.mapToBookDtoList(bookDbService.getAllBooks());
    }

    @GetMapping(value = "getBook")
    public BookDto getBook(@RequestParam Long bookId) throws BookNotFoundException {
        return bookMapper.mapToBookDto(bookDbService.getBook(bookId));
    }

    @DeleteMapping(value = "deleteBook")
    public void deleteBook(@RequestParam Long bookId) {
        bookDbService.deleteBook(bookId);
    }
}
