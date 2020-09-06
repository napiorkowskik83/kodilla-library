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

    @RequestMapping(method = RequestMethod.POST, value = "addBook", consumes = APPLICATION_JSON_VALUE)
    public void addBook(@RequestBody BookDto bookDto) {
        bookDbService.saveBook(bookMapper.mapToBook(bookDto));
    }

    @RequestMapping(method = RequestMethod.GET, value = "getBooks")
    public List<BookDto> getBooks() {
        return bookMapper.mapToBookDtoList(bookDbService.getAllBooks());
    }

    @RequestMapping(method = RequestMethod.GET, value = "getBook")
    public BookDto getBook(@RequestParam Long bookId) throws ItemNotFoundException {
        return bookMapper.mapToBookDto(bookDbService.getBook(bookId).orElseThrow(ItemNotFoundException::new));
    }
}
