package com.crud.kodillalibrary.controller;

import com.crud.kodillalibrary.domain.BookCopyDto;
import com.crud.kodillalibrary.domain.BookDto;
import com.crud.kodillalibrary.domain.ReaderDto;
import com.crud.kodillalibrary.mappers.BookCopyMapper;
import com.crud.kodillalibrary.mappers.BookMapper;
import com.crud.kodillalibrary.mappers.BorrowingMapper;
import com.crud.kodillalibrary.mappers.ReaderMapper;
import com.crud.kodillalibrary.services.BookCopyDbService;
import com.crud.kodillalibrary.services.BookDbService;
import com.crud.kodillalibrary.services.BorrowingDbService;
import com.crud.kodillalibrary.services.ReaderDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/library")
@CrossOrigin(origins = "*")
public class LibraryController {
    private final BookDbService bookDbService;
    private final BookCopyDbService bookCopyDbService;
    private final ReaderDbService readerDbService;
    private final BorrowingDbService borrowingDbService;
    private final BookMapper bookMapper;
    private final BookCopyMapper bookCopyMapper;
    private final ReaderMapper readerMapper;
    private final BorrowingMapper borrowingMapper;

    @Autowired
    public LibraryController(BookDbService bookDbService,
                             BookCopyDbService bookCopyDbService,
                             ReaderDbService readerDbService,
                             BorrowingDbService borrowingDbService,
                             BookMapper bookMapper,
                             BookCopyMapper bookCopyMapper,
                             ReaderMapper readerMapper,
                             BorrowingMapper borrowingMapper){
        this.bookDbService = bookDbService;
        this.bookCopyDbService = bookCopyDbService;
        this.readerDbService = readerDbService;
        this.borrowingDbService =borrowingDbService;
        this.bookMapper = bookMapper;
        this.bookCopyMapper = bookCopyMapper;
        this.readerMapper = readerMapper;
        this.borrowingMapper = borrowingMapper;
    }

    @RequestMapping(method = RequestMethod.POST, value = "addReader", consumes = APPLICATION_JSON_VALUE)
    public void addReader(@RequestBody ReaderDto readerDto) {
        readerDbService.saveReader(readerMapper.mapToReader(readerDto));
    }

    @RequestMapping(method = RequestMethod.GET, value = "getReaders")
    public List<ReaderDto> getReaders() {
        return readerMapper.mapToReaderDtoList(readerDbService.getAllReaders());
    }

    @RequestMapping(method = RequestMethod.GET, value = "getReader")
    public ReaderDto getReader(@RequestParam Long readerId) throws ItemNotFoundException {
        return readerMapper.mapToReaderDto(readerDbService.getReader(readerId).orElseThrow(ItemNotFoundException::new));
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

    @RequestMapping(method = RequestMethod.POST, value = "addBookCopy", consumes = APPLICATION_JSON_VALUE)
    public void addBookCopy(@RequestBody BookCopyDto bookCopyDto) {
        bookCopyDbService.saveBookCopy(bookCopyMapper.mapToBookCopy(bookCopyDto));
    }
}
