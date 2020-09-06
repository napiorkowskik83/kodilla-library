package com.crud.kodillalibrary.controllers;

import com.crud.kodillalibrary.domain.*;
import com.crud.kodillalibrary.mappers.BookCopyMapper;
import com.crud.kodillalibrary.services.BookCopyDbService;
import com.crud.kodillalibrary.services.BookDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/library/bookCopy")
@CrossOrigin(origins = "*")
public class BookCopyController {
    private final BookCopyDbService bookCopyDbService;
    private final BookCopyMapper bookCopyMapper;
    private final BookDbService bookDbService;

    @Autowired
    public BookCopyController(BookCopyDbService bookCopyDbService,
                              BookCopyMapper bookCopyMapper,
                              BookDbService bookDbService
                            ){
        this.bookCopyDbService = bookCopyDbService;
        this.bookCopyMapper = bookCopyMapper;
        this.bookDbService = bookDbService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "addBookCopy", consumes = APPLICATION_JSON_VALUE)
    public void addBookCopy(@RequestBody BookCopyDto bookCopyDto) throws ItemNotFoundException {
        Book book = bookDbService.getBook(bookCopyDto.getBookId()).orElseThrow(ItemNotFoundException::new);
        BookCopy bookCopy = bookCopyMapper.mapToBookCopy(bookCopyDto, book);
        book.getBookCopies().add(bookCopy);
        bookCopyDbService.saveBookCopy(bookCopy);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getAllBookCopies")
    public List<BookCopyDto> getAllBookCopies() {
        return bookCopyMapper.mapToBookCopyDtoList(bookCopyDbService.getAllBookCopies());
    }

//    @RequestMapping(method = RequestMethod.GET, value = "getAvailableBookCopiesOfBook")
//    public List<BookCopyDto> getBookCopiesOfBook(@RequestParam Long bookId){
//        return getAllBookCopies().stream()
//                .filter(t-> bookId.equals(t.getBookId()))
//                .collect(Collectors.toList());
//    }

    @RequestMapping(method = RequestMethod.GET, value = "getAvailableBookCopiesOfBook")
    public List<BookCopyDto> getAvailableBookCopiesOfBook(@RequestParam Long bookId){
        return getAllBookCopies().stream()
                .filter(t-> bookId.equals(t.getBookId()) && ("available").equals(t.getStatus()))
                .collect(Collectors.toList());
    }

    @RequestMapping(method = RequestMethod.GET, value = "getBookCopy")
    public BookCopyDto getBookCopy(@RequestParam Long bookCopyId) throws ItemNotFoundException {
        return bookCopyMapper.mapToBookCopyDto(bookCopyDbService.getBookCopy(bookCopyId).orElseThrow(ItemNotFoundException::new));
    }
}
