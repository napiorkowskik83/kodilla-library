package com.crud.kodillalibrary.services;
import com.crud.kodillalibrary.domain.BookCopy;
import com.crud.kodillalibrary.repositories.BookCopyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookCopyDbService {
    private final BookCopyRepository bookCopyRepository;

    @Autowired
    public BookCopyDbService(BookCopyRepository bookCopyRepository){
        this.bookCopyRepository = bookCopyRepository;
    }

    public List<BookCopy> getAllBookCopies(){
        return bookCopyRepository.findAll();
    }

    public Optional<BookCopy> getBookCopy(final Long id){
        return bookCopyRepository.findById(id);
    }

    public BookCopy saveBookCopy(final BookCopy bookCopy){
        return bookCopyRepository.save(bookCopy);
    }

    public void deleteBookCopy(final Long bookCopyId){
        bookCopyRepository.deleteById(bookCopyId);
    }

}
