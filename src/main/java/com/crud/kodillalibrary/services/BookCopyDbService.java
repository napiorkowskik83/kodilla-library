package com.crud.kodillalibrary.services;

import com.crud.kodillalibrary.controllers.BookCopyNotFoundException;
import com.crud.kodillalibrary.controllers.BookNotFoundException;
import com.crud.kodillalibrary.domain.Book;
import com.crud.kodillalibrary.domain.BookCopy;
import com.crud.kodillalibrary.mappers.BookCopyMapper;
import com.crud.kodillalibrary.repositories.BookCopyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookCopyDbService {
    private final BookCopyRepository bookCopyRepository;
    private final BookCopyMapper bookCopyMapper;
    private final BookDbService bookDbService;

    @Autowired
    public BookCopyDbService(final BookCopyRepository bookCopyRepository,
                             final BookCopyMapper bookCopyMapper,
                             final BookDbService bookDbService) {
        this.bookCopyRepository = bookCopyRepository;
        this.bookCopyMapper = bookCopyMapper;
        this.bookDbService = bookDbService;
    }

    public void addBookCopy(final Long bookId) throws BookNotFoundException {
        Book book = bookDbService.getBook(bookId);
        BookCopy bookCopy = new BookCopy(book);
        book.getBookCopies().add(bookCopy);
        bookCopyRepository.save(bookCopy);
    }

    public BookCopy setStatusToAvailable(final Long bookCopyId) throws BookCopyNotFoundException {
        BookCopy bookCopy = bookCopyRepository.findById(bookCopyId).orElseThrow(BookCopyNotFoundException::new);
        bookCopy.setStatus("available");
        return bookCopyRepository.save(bookCopy);
    }
    public BookCopy setStatusToBorrowed(final Long bookCopyId) throws BookCopyNotFoundException {
        BookCopy bookCopy = bookCopyRepository.findById(bookCopyId).orElseThrow(BookCopyNotFoundException::new);
        bookCopy.setStatus("borrowed");
        return bookCopyRepository.save(bookCopy);
    }

    public BookCopy setStatusToLost(final Long bookCopyId) throws BookCopyNotFoundException {
        BookCopy bookCopy = bookCopyRepository.findById(bookCopyId).orElseThrow(BookCopyNotFoundException::new);
        bookCopy.setStatus("lost");
        return bookCopyRepository.save(bookCopy);
    }

    public BookCopy setStatusToTattered(final Long bookCopyId) throws BookCopyNotFoundException {
        BookCopy bookCopy = bookCopyRepository.findById(bookCopyId).orElseThrow(BookCopyNotFoundException::new);
        bookCopy.setStatus("tattered");
        return bookCopyRepository.save(bookCopy);
    }

    public List<BookCopy> getAllBookCopies() {
        return bookCopyRepository.findAll();
    }

    public List<BookCopy> getAllAvailableBookCopies() {
        return bookCopyRepository.getAllAvailableBookCopies();
    }

    public List<BookCopy> getAllBookCopiesOfBook(final Long bookId) {
        return bookCopyRepository.getAllBookCopiesOfBook(bookId);
    }

    public List<BookCopy> getAvailableBookCopiesOfBook(final Long bookId) {
        return bookCopyRepository.getAvailableBookCopiesOfBook(bookId);
    }

    public List<BookCopy> getAllBookCopiesOfTitle(final String title) {
        return bookCopyRepository.getAllBookCopiesOfTitle(title);
    }

    public List<BookCopy> getAvailableBookCopiesOfTitle(final String title) {
        return bookCopyRepository.getAvailableBookCopiesOfTitle(title);
    }

    public BookCopy getBookCopy(final Long id) throws BookCopyNotFoundException {
        return bookCopyRepository.findById(id).orElseThrow(BookCopyNotFoundException::new);
    }

    public void deleteBookCopy(final Long bookCopyId) throws BookCopyNotFoundException, BookNotFoundException {
        BookCopy bookCopyToDelete = bookCopyRepository.findById(bookCopyId).orElseThrow(BookCopyNotFoundException::new);
        Book book = bookDbService.getBook(bookCopyToDelete.getBook().getId());
        book.getBookCopies().remove(bookCopyToDelete);
        bookDbService.saveBook(book);
    }

}
