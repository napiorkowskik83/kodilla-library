package com.crud.kodillalibrary.controllers;

import com.crud.kodillalibrary.domain.*;
import com.crud.kodillalibrary.mappers.BorrowingMapper;
import com.crud.kodillalibrary.services.BookCopyDbService;
import com.crud.kodillalibrary.services.BorrowingDbService;
import com.crud.kodillalibrary.services.ReaderDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/library/borrowing")
@CrossOrigin(origins = "*")
public class BorrowingController {

    private final BorrowingDbService borrowingDbService;
    private final BorrowingMapper borrowingMapper;
    private final BookCopyDbService bookCopyDbService;
    private final ReaderDbService readerDbService;

    @Autowired
    public BorrowingController(BorrowingDbService borrowingDbService,
                               BorrowingMapper borrowingMapper,
                               BookCopyDbService bookCopyDbService,
                               ReaderDbService readerDbService){
        this.borrowingDbService = borrowingDbService;
        this.borrowingMapper = borrowingMapper;
        this.bookCopyDbService = bookCopyDbService;
        this.readerDbService = readerDbService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "addBorrowing", consumes = APPLICATION_JSON_VALUE)
    public void addBorrowing(@RequestBody BorrowingDto borrowingDto) throws ItemNotFoundException{
        BookCopy bookCopy = bookCopyDbService.getBookCopy(borrowingDto.getBookCopyId()).orElseThrow(ItemNotFoundException::new);
        if("available".equals(bookCopy.getStatus())){
            Reader reader = readerDbService.getReader(borrowingDto.getReaderId()).orElseThrow(ItemNotFoundException::new);
            bookCopy.setStatus("on loan");
            Borrowing borrowing = borrowingMapper.mapToBorrowing(borrowingDto, bookCopy, reader);
            bookCopy.getBorrowingList().add(borrowing);
            reader.getBorrowings().add(borrowing);
            borrowingDbService.saveBorrowing(borrowing);
        }else{
            System.out.println("Book copy not available");
            //How to communicate this??
        }

    }

    @RequestMapping(method = RequestMethod.POST, value = "makeReturn")
    public void makeReturn(@RequestParam Long borrowingId) throws ItemNotFoundException{
        Borrowing borrowing = borrowingDbService.getBorrowing(borrowingId).orElseThrow(ItemNotFoundException::new);
        borrowing.getBookCopy().setStatus("available");
        borrowing.setReturnDate(new Date());
    }

}
