package com.crud.kodillalibrary.repositories;

import com.crud.kodillalibrary.domain.BookCopy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface BookCopyRepository extends CrudRepository<BookCopy, Long> {

    @Override
    List<BookCopy> findAll();

    @Override
    BookCopy save(BookCopy book);

    @Override
    Optional<BookCopy> findById(Long id);

    @Query(nativeQuery = true)
    List<BookCopy> getAvailableBookCopiesOfBook(@Param("BOOK_ID") Long bookId);
}
