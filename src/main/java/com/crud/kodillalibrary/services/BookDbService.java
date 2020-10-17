package com.crud.kodillalibrary.services;

import com.crud.kodillalibrary.controllers.BookNotFoundException;
import com.crud.kodillalibrary.domain.Book;
import com.crud.kodillalibrary.domain.BookDto;
import com.crud.kodillalibrary.mappers.BookMapper;
import com.crud.kodillalibrary.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookDbService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Autowired
    public BookDbService(final BookRepository bookRepository, final BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBook(final Long id) throws BookNotFoundException {
        return bookRepository.findById(id).orElseThrow(BookNotFoundException::new);
    }

    public void saveBook(final Book book) {
        bookRepository.save(book);
    }

    public Book updateBook(final Book book) throws BookNotFoundException {
        bookRepository.findById(book.getId()).orElseThrow(BookNotFoundException::new);
        return bookRepository.save(book);
    }

    public void deleteBook(final Long bookId) {
        bookRepository.deleteById(bookId);
    }
}
