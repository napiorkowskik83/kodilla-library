package com.crud.kodillalibrary.repositories;

import com.crud.kodillalibrary.domain.Book;
import com.crud.kodillalibrary.domain.BookCopy;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookCopyRepositoryTestSuite {
    @Autowired
    BookCopyRepository bookCopyRepository;
    @Autowired
    BookRepository bookRepository;

    @Test
    public void testBookCopyRepositorySaveAndFindById() {
        //Given
        Book book1 = new Book("title1", "author1", 1983);
        bookRepository.save(book1);

        BookCopy book1Copy1 = new BookCopy(book1);
        book1.getBookCopies().add(book1Copy1);

        BookCopy book1Copy2 = new BookCopy(book1);
        book1.getBookCopies().add(book1Copy2);

        //When
        bookCopyRepository.save(book1Copy1);
        bookCopyRepository.save(book1Copy2);

        Long id1 = book1Copy1.getId();
        Long id2 = book1Copy2.getId();

        //Then
        Optional<BookCopy> readBookCopy1 = bookCopyRepository.findById(id1);
        Optional<BookCopy> readBookCopy2 = bookCopyRepository.findById(id2);
        Assert.assertTrue(readBookCopy1.isPresent());
        Assert.assertTrue(readBookCopy2.isPresent());

        //CleanUp
        Long bookId = book1.getId();
        bookRepository.deleteById(bookId);
    }

    @Test
    public void getAllAndAvailableBookCopiesOfBook() {
        //Given
        Book book1 = new Book("title1", "author1", 1983);
        bookRepository.save(book1);

        BookCopy book1Copy1 = new BookCopy(book1);
        book1.getBookCopies().add(book1Copy1);

        BookCopy book1Copy2 = new BookCopy(book1);
        book1.getBookCopies().add(book1Copy2);

        BookCopy book1Copy3 = new BookCopy(book1);
        book1.getBookCopies().add(book1Copy3);
        book1Copy3.setStatus("lost");

        //When
        bookCopyRepository.save(book1Copy1);
        bookCopyRepository.save(book1Copy2);
        bookCopyRepository.save(book1Copy3);

        Long book1Id = book1.getId();

        //Then
        List<BookCopy> allBookCopies = bookCopyRepository.getAllBookCopiesOfBook(book1Id);
        Assert.assertEquals(3, allBookCopies.size());
        List<BookCopy> availableBookCopies = bookCopyRepository.getAvailableBookCopiesOfBook(book1Id);
        Assert.assertEquals(2, availableBookCopies.size());

        //CleanUp
        bookRepository.deleteById(book1Id);
    }

    @Test
    public void getAllAndAvailableBookCopiesOfTitle() {
        //Given
        Book book1 = new Book("test_title", "author1", 1983);
        bookRepository.save(book1);

        Book book2 = new Book("test_title", "author1", 1990);
        bookRepository.save(book2);

        Book book3 = new Book("title3", "author1", 1990);
        bookRepository.save(book3);

        BookCopy book1Copy1 = new BookCopy(book1);
        book1.getBookCopies().add(book1Copy1);

        BookCopy book1Copy2 = new BookCopy(book1);
        book1.getBookCopies().add(book1Copy2);

        BookCopy book2Copy1 = new BookCopy(book2);
        book2.getBookCopies().add(book2Copy1);

        BookCopy book3Copy1 = new BookCopy(book3);
        book3.getBookCopies().add(book3Copy1);


        //When
        book1Copy2.setStatus("borrowed");
        bookCopyRepository.save(book1Copy1);
        bookCopyRepository.save(book1Copy2);
        bookCopyRepository.save(book2Copy1);
        bookCopyRepository.save(book3Copy1);

        Long book1Id = book1.getId();
        Long book2Id = book2.getId();
        Long book3Id = book3.getId();

        //Then
        List<BookCopy> allBookCopies = bookCopyRepository.getAllBookCopiesOfTitle("test_title");
        Assert.assertEquals(3, allBookCopies.size());
        List<BookCopy> availableBookCopies = bookCopyRepository.getAvailableBookCopiesOfTitle("test_title");
        Assert.assertEquals(2, availableBookCopies.size());

        //CleanUp
        bookRepository.deleteById(book1Id);
        bookRepository.deleteById(book2Id);
        bookRepository.deleteById(book3Id);
    }
}
