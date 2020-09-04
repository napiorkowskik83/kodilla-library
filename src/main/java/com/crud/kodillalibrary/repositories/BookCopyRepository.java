package com.crud.kodillalibrary.repositories;

import com.crud.kodillalibrary.domain.Book;
import com.crud.kodillalibrary.domain.BookCopy;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface BookCopyRepository extends CrudRepository<BookCopy, Long> {

    @Override
    List<BookCopy> findAll();

    @Override
    BookCopy save(BookCopy book);

    @Override
    Optional<BookCopy> findById(Long id);
}
