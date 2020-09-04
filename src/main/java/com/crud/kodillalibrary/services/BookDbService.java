package com.crud.kodillalibrary.services;

import com.crud.kodillalibrary.domain.Book;
import com.crud.kodillalibrary.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookDbService {
    private final BookRepository bookRepository;

    @Autowired
    public BookDbService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public Optional<Book> getBook(final Long id){
        return bookRepository.findById(id);
    }

    public Book saveBook(final Book book){
        return bookRepository.save(book);
    }

    public void deleteBook(final Long bookId){
        bookRepository.deleteById(bookId);
    }

}
