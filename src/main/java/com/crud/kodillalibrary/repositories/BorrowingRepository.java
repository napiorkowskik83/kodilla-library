package com.crud.kodillalibrary.repositories;

import com.crud.kodillalibrary.domain.Book;
import com.crud.kodillalibrary.domain.Borrowing;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface BorrowingRepository extends CrudRepository<Borrowing, Long> {

    @Override
    List<Borrowing> findAll();

    @Override
    Borrowing save(Borrowing borrowing);

    @Override
    Optional<Borrowing> findById(Long id);
}
