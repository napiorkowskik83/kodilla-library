package com.crud.kodillalibrary.services;

import com.crud.kodillalibrary.domain.Borrowing;
import com.crud.kodillalibrary.repositories.BorrowingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BorrowingDbService {
    private final BorrowingRepository borrowingRepository;

    @Autowired
    public BorrowingDbService(BorrowingRepository borrowingRepository){
        this.borrowingRepository = borrowingRepository;
    }

    public List<Borrowing> getAllBorrowings(){
        return borrowingRepository.findAll();
    }

    public Optional<Borrowing> getBorrowing(final Long id){
        return borrowingRepository.findById(id);
    }

    public Borrowing saveBorrowing(final Borrowing borrowing){
        return borrowingRepository.save(borrowing);
    }

    public void deleteBorrowing(final Long borrowingId){
        borrowingRepository.deleteById(borrowingId);
    }
}
