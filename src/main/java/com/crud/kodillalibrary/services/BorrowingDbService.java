package com.crud.kodillalibrary.services;

import com.crud.kodillalibrary.controllers.*;
import com.crud.kodillalibrary.domain.*;
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
        if(BookCopyStatus.AVAILABLE.equals(bookCopy.getStatus())) {
            bookCopyDbService.setStatusTo(bookCopy.getId(), BookCopyStatus.BORROWED);
            return borrowingRepository.save(borrowingMapper.mapToBorrowing(borrowingDto, bookCopy, reader));
        }else{
            throw new BookCopyNotAvailableException("Chosen book copy is not available!");
        }
    }

    public Borrowing makeReturn(final Long borrowingId)
            throws BorrowingNotFoundException, BookCopyNotFoundException, ActiveBorrowingNotFoundException{
        Borrowing borrowing = borrowingRepository.findById(borrowingId).orElseThrow(()-> new BorrowingNotFoundException("Borrowing with pointed ID does not exist!"));
        if(borrowing.getReturnDate() == null) {
            bookCopyDbService.setStatusTo(borrowing.getBookCopy().getId(), BookCopyStatus.AVAILABLE);
            borrowing.setReturnDate(new Date());
            return borrowingRepository.save(borrowing);
        }else{
            throw new ActiveBorrowingNotFoundException("Chosen borrowing is already finished!");
        }
    }

    public Borrowing finishBorrowingAs(final Long borrowingId, final BookCopyStatus status)
            throws BorrowingNotFoundException, BookCopyNotFoundException, ActiveBorrowingNotFoundException{
        Borrowing borrowing = borrowingRepository.findById(borrowingId).orElseThrow(()-> new BorrowingNotFoundException("Borrowing with pointed ID does not exist!"));
        if(borrowing.getReturnDate() == null) {
            bookCopyDbService.setStatusTo(borrowing.getBookCopy().getId(), status);
            borrowing.setReturnDate(new Date());
            return borrowingRepository.save(borrowing);
        }else{
            throw new ActiveBorrowingNotFoundException("Chosen borrowing is already finished!");
        }
    }

    public Borrowing getBorrowing(final Long id) throws BorrowingNotFoundException{
        return borrowingRepository.findById(id).orElseThrow(()-> new BorrowingNotFoundException("Borrowing with pointed ID does not exist!"));
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
