package com.crud.kodillalibrary.controllers;

import com.crud.kodillalibrary.domain.*;
import com.crud.kodillalibrary.mappers.BorrowingMapper;
import com.crud.kodillalibrary.services.BorrowingDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/library/borrowing")
@CrossOrigin(origins = "*")
public class BorrowingController {

    private final BorrowingDbService borrowingDbService;
    private final BorrowingMapper borrowingMapper;

    @Autowired
    public BorrowingController(final BorrowingDbService borrowingDbService,
                               final BorrowingMapper borrowingMapper) {
        this.borrowingDbService = borrowingDbService;
        this.borrowingMapper = borrowingMapper;
    }

    @PostMapping(value = "addBorrowing", consumes = APPLICATION_JSON_VALUE)
    public BorrowingDto addBorrowing(@RequestBody BorrowingDto borrowingDto)
            throws BookCopyNotFoundException, ReaderNotFoundException, BookCopyNotAvailableException {
        return borrowingMapper.mapToBorrowingDto(borrowingDbService.addBorrowing(borrowingDto));
    }

    @PutMapping(value = "makeReturn")
    public BorrowingDto makeReturn(@RequestParam Long borrowingId)
            throws BorrowingNotFoundException, BookCopyNotFoundException, ActiveBorrowingNotFoundException {
        return borrowingMapper.mapToBorrowingDto(borrowingDbService.makeReturn(borrowingId));
    }

    @PutMapping(value = "finishBorrowingAsLost")
    public BorrowingDto finishBorrowingAsLost(@RequestParam Long borrowingId)
            throws BorrowingNotFoundException, BookCopyNotFoundException, ActiveBorrowingNotFoundException {
        return borrowingMapper.mapToBorrowingDto(borrowingDbService.finishBorrowingAsLost(borrowingId));
    }

    @PutMapping(value = "finishBorrowingAsTattered")
    public BorrowingDto finishBorrowingAsTattered(@RequestParam Long borrowingId)
            throws BorrowingNotFoundException, BookCopyNotFoundException, ActiveBorrowingNotFoundException {
        return borrowingMapper.mapToBorrowingDto(borrowingDbService.finishBorrowingAsTattered(borrowingId));
    }

    @GetMapping(value = "getBorrowing")
    public BorrowingDto getBorrowing(@RequestParam Long borrowingId)
            throws BorrowingNotFoundException {
        return borrowingMapper.mapToBorrowingDto(borrowingDbService.getBorrowing(borrowingId));
    }

    @GetMapping(value = "getAllBorrowings")
    public List<BorrowingDto> getAllBorrowings() {
        return borrowingMapper.mapToBorrowingDtoList(borrowingDbService.getAllBorrowings());
    }

    @GetMapping(value = "getAllBorrowingsOfBookCopy")
    public List<BorrowingDto> getAllBorrowingsOfBookCopy(@RequestParam Long bookCopyId){
        return borrowingMapper.mapToBorrowingDtoList(borrowingDbService.getAllBorrowingsOfBookCopy(bookCopyId));
    }

    @GetMapping(value = "getActiveBorrowingsOfBookCopy")
    public List<BorrowingDto> getActiveBorrowingsOfBookCopy(@RequestParam Long bookCopyId){
        return borrowingMapper.mapToBorrowingDtoList(borrowingDbService.getActiveBorrowingsOfBookCopy(bookCopyId));
    }

    @GetMapping(value = "getAllBorrowingsOfReader")
    public List<BorrowingDto> getAllBorrowingsOfReader(@RequestParam Long readerId){
        return borrowingMapper.mapToBorrowingDtoList(borrowingDbService.getAllBorrowingsOfReader(readerId));
    }

    @GetMapping(value = "getActiveBorrowingsOfReader")
    public List<BorrowingDto> getActiveBorrowingsOfReader(@RequestParam Long readerId){
        return borrowingMapper.mapToBorrowingDtoList(borrowingDbService.getActiveBorrowingsOfReader(readerId));
    }
}
