package com.crud.kodillalibrary.repositories;

import com.crud.kodillalibrary.domain.Book;
import com.crud.kodillalibrary.domain.BookCopy;
import com.crud.kodillalibrary.domain.Borrowing;
import com.crud.kodillalibrary.domain.Reader;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BorrowingRepositoryTestSuite {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookCopyRepository bookCopyRepository;

    @Autowired
    ReaderRepository readerRepository;

    @Autowired
    BorrowingRepository borrowingRepository;

    @Test
    public void testBorrowingRepositorySaveAndFindById() {
        //Given
        Book book1 = new Book("title1", "author1", 1983);
        bookRepository.save(book1);
        Long bookId = book1.getId();

        BookCopy book1Copy1 = new BookCopy(book1);
        book1.getBookCopies().add(book1Copy1);
        bookCopyRepository.save(book1Copy1);

        Reader reader1 = new Reader("Joe", "Doe");
        readerRepository.save(reader1);
        Long readerId = reader1.getId();

        Borrowing borrowing1 = new Borrowing(book1Copy1, reader1);

        //When
        borrowingRepository.save(borrowing1);

        //Then
        Long borrowingId = borrowing1.getId();
        Optional<Borrowing> readBorrowing = borrowingRepository.findById(borrowingId);
        Assert.assertTrue(readBorrowing.isPresent());

        //CleanUp
        borrowingRepository.deleteById(borrowingId);
        readerRepository.deleteById(readerId);
        bookRepository.deleteById(bookId);
    }

    @Test
    public void testGetAllBorrowingsOfReader() {
        //Given
        Book book1 = new Book("title1", "author1", 1983);
        bookRepository.save(book1);
        Long bookId = book1.getId();

        BookCopy book1Copy1 = new BookCopy(book1);
        book1.getBookCopies().add(book1Copy1);
        bookCopyRepository.save(book1Copy1);

        BookCopy book1Copy2 = new BookCopy(book1);
        book1.getBookCopies().add(book1Copy2);
        bookCopyRepository.save(book1Copy2);

        BookCopy book1Copy3 = new BookCopy(book1);
        book1.getBookCopies().add(book1Copy3);
        bookCopyRepository.save(book1Copy3);

        Reader reader1 = new Reader("Joe", "Doe");
        readerRepository.save(reader1);

        Reader reader2 = new Reader("Max", "Pen");
        readerRepository.save(reader2);

        Borrowing borrowing1 = new Borrowing(book1Copy1, reader1);
        Borrowing borrowing2 = new Borrowing(book1Copy2, reader1);
        Borrowing borrowing3 = new Borrowing(book1Copy3, reader2);


        //When
        borrowingRepository.save(borrowing1);
        borrowingRepository.save(borrowing2);
        borrowingRepository.save(borrowing3);
        Long borrowing1Id = borrowing1.getId();
        Long borrowing2Id = borrowing2.getId();
        Long borrowing3Id = borrowing3.getId();
        Long reader1Id = reader1.getId();
        Long reader2Id = reader2.getId();

        //Then
        List<Borrowing> readBorrowings1 = borrowingRepository.getAllBorrowingsOfReader(reader1Id);
        List<Borrowing> readBorrowings2 = borrowingRepository.getAllBorrowingsOfReader(reader2Id);
        Assert.assertEquals(2, readBorrowings1.size());
        Assert.assertEquals(1, readBorrowings2.size());

        //CleanUp
        borrowingRepository.deleteById(borrowing1Id);
        borrowingRepository.deleteById(borrowing2Id);
        borrowingRepository.deleteById(borrowing3Id);
        readerRepository.deleteById(reader1Id);
        readerRepository.deleteById(reader2Id);
        bookRepository.deleteById(bookId);
    }

    @Test
    public void testGetActiveBorrowingsOfReader() {
        //Given
        Book book1 = new Book("title1", "author1", 1983);
        bookRepository.save(book1);
        Long bookId = book1.getId();

        BookCopy book1Copy1 = new BookCopy(book1);
        book1.getBookCopies().add(book1Copy1);
        bookCopyRepository.save(book1Copy1);

        BookCopy book1Copy2 = new BookCopy(book1);
        book1.getBookCopies().add(book1Copy2);
        bookCopyRepository.save(book1Copy2);

        BookCopy book1Copy3 = new BookCopy(book1);
        book1.getBookCopies().add(book1Copy3);
        bookCopyRepository.save(book1Copy3);

        Reader reader1 = new Reader("Joe", "Doe");
        readerRepository.save(reader1);

        Borrowing borrowing1 = new Borrowing(book1Copy1, reader1);
        Borrowing borrowing2 = new Borrowing(book1Copy2, reader1);
        Borrowing borrowing3 = new Borrowing(book1Copy3, reader1);

        borrowingRepository.save(borrowing1);
        borrowingRepository.save(borrowing2);
        borrowingRepository.save(borrowing3);
        Long borrowing1Id = borrowing1.getId();
        Long borrowing2Id = borrowing2.getId();
        Long borrowing3Id = borrowing3.getId();

        //When
        borrowing3.setReturnDate(new Date());
        borrowingRepository.save(borrowing3);

        Long reader1Id = reader1.getId();

        //Then
        List<Borrowing> readBorrowings = borrowingRepository.getActiveBorrowingsOfReader(reader1Id);

        Assert.assertEquals(2, readBorrowings.size());

        //CleanUp
        borrowingRepository.deleteById(borrowing1Id);
        borrowingRepository.deleteById(borrowing2Id);
        borrowingRepository.deleteById(borrowing3Id);
        readerRepository.deleteById(reader1Id);
        bookRepository.deleteById(bookId);
    }

    @Test
    public void testGetAllAndActiveBorrowingsOfBookCopy() {
        //Given
        Book book = new Book("title1", "author1", 1983);
        bookRepository.save(book);
        Long bookId = book.getId();

        BookCopy bookCopy = new BookCopy(book);
        book.getBookCopies().add(bookCopy);
        bookCopyRepository.save(bookCopy);
        Long bookCopyId = bookCopy.getId();

        Reader reader1 = new Reader("Joe", "Doe");
        Reader reader2 = new Reader("Jan", "Kruk");
        readerRepository.save(reader1);
        readerRepository.save(reader2);
        Long reader1Id = reader1.getId();
        Long reader2Id = reader2.getId();

        Borrowing borrowing1 = new Borrowing(bookCopy, reader1);
        borrowing1.setReturnDate(new Date());
        Borrowing borrowing2 = new Borrowing(bookCopy, reader2);


        //When
        borrowingRepository.save(borrowing1);
        borrowingRepository.save(borrowing2);
        Long borrowing1Id = borrowing1.getId();
        Long borrowing2Id = borrowing2.getId();

        //Then
        List<Borrowing> allBorrowings = borrowingRepository.getAllBorrowingsOfBookCopy(bookCopyId);
        List<Borrowing> activeBorrowings = borrowingRepository.getActiveBorrowingsOfBookCopy(bookCopyId);
        Assert.assertEquals(2, allBorrowings.size());
        Assert.assertEquals(1, activeBorrowings.size());

        //CleanUp
        borrowingRepository.deleteById(borrowing1Id);
        borrowingRepository.deleteById(borrowing2Id);
        readerRepository.deleteById(reader1Id);
        readerRepository.deleteById(reader2Id);
        bookRepository.deleteById(bookId);
    }
}
