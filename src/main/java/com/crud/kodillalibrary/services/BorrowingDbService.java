package com.crud.kodillalibrary.services;

import com.crud.kodillalibrary.controllers.*;
import com.crud.kodillalibrary.domain.BookCopy;
import com.crud.kodillalibrary.domain.Borrowing;
import com.crud.kodillalibrary.domain.BorrowingDto;
import com.crud.kodillalibrary.domain.Reader;
import com.crud.kodillalibrary.mappers.BorrowingMapper;
import com.crud.kodillalibrary.repositories.BorrowingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BorrowingDbService {
    private final BorrowingRepository borrowingRepository;
    private final BorrowingMapper borrowingMapper;
    private final BookCopyDbService bookCopyDbService;
    private final ReaderDbService readerDbService;

    @Autowired
    public BorrowingDbService(final BorrowingRepository borrowingRepository,
                              final BorrowingMapper borrowingMapper,
                              final BookCopyDbService bookCopyDbService,
                              final ReaderDbService readerDbService){
        this.borrowingRepository = borrowingRepository;
        this.borrowingMapper = borrowingMapper;
        this.bookCopyDbService = bookCopyDbService;
        this.readerDbService = readerDbService;
    }

    public Borrowing addBorrowing(final BorrowingDto borrowingDto)
            throws BookCopyNotFoundException, ReaderNotFoundException, BookCopyNotAvailableException {
        BookCopy bookCopy = bookCopyDbService.getBookCopy(borrowingDto.getBookCopyId());
        Reader reader = readerDbService.getReader(borrowingDto.getReaderId());
        if("available".equals(bookCopy.getStatus())) {
            bookCopyDbService.setStatusToBorrowed(bookCopy.getId());
            return borrowingRepository.save(borrowingMapper.mapToBorrowing(borrowingDto, bookCopy, reader));
        }else{
            throw new BookCopyNotAvailableException();
        }
    }

    public Borrowing makeReturn(final Long borrowingId)
            throws BorrowingNotFoundException, BookCopyNotFoundException, ActiveBorrowingNotFoundException{
        Borrowing borrowing = borrowingRepository.findById(borrowingId).orElseThrow(BorrowingNotFoundException::new);
        if(borrowing.getReturnDate() == null) {
            bookCopyDbService.setStatusToAvailable(borrowing.getBookCopy().getId());
            borrowing.setReturnDate(new Date());
            return borrowingRepository.save(borrowing);
        }else{
            throw new ActiveBorrowingNotFoundException();
        }
    }

    public Borrowing finishBorrowingAsLost(final Long borrowingId)
            throws BorrowingNotFoundException, BookCopyNotFoundException, ActiveBorrowingNotFoundException{
        Borrowing borrowing = borrowingRepository.findById(borrowingId).orElseThrow(BorrowingNotFoundException::new);
        if(borrowing.getReturnDate() == null) {
            bookCopyDbService.setStatusToLost(borrowing.getBookCopy().getId());
            borrowing.setReturnDate(new Date());
            return borrowingRepository.save(borrowing);
        }else{
            throw new ActiveBorrowingNotFoundException();
        }
    }
    public Borrowing finishBorrowingAsTattered(final Long borrowingId)
            throws BorrowingNotFoundException, BookCopyNotFoundException, ActiveBorrowingNotFoundException{
        Borrowing borrowing = borrowingRepository.findById(borrowingId).orElseThrow(BorrowingNotFoundException::new);
        if(borrowing.getReturnDate() == null) {
            bookCopyDbService.setStatusToTattered(borrowing.getBookCopy().getId());
            borrowing.setReturnDate(new Date());
            return borrowingRepository.save(borrowing);
        }else{
            throw new ActiveBorrowingNotFoundException();
        }
    }

    public Borrowing getBorrowing(final Long id) throws BorrowingNotFoundException{
        return borrowingRepository.findById(id).orElseThrow(BorrowingNotFoundException::new);
    }

    public List<Borrowing> getAllBorrowings(){
        return borrowingRepository.findAll();
    }

    public List<Borrowing> getAllBorrowingsOfBookCopy(final Long bookCopyId){
        return borrowingRepository.getAllBorrowingsOfBookCopy(bookCopyId);
    }

    public List<Borrowing> getActiveBorrowingsOfBookCopy(final Long bookCopyId){
        return borrowingRepository.getActiveBorrowingsOfBookCopy(bookCopyId);
    }

    public List<Borrowing> getAllBorrowingsOfReader(final Long readerId){
        return borrowingRepository.getAllBorrowingsOfReader(readerId);
    }

    public List<Borrowing> getActiveBorrowingsOfReader(final Long readerId){
        return borrowingRepository.getActiveBorrowingsOfReader(readerId);
    }
}
