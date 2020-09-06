package com.crud.kodillalibrary.repositories;

import com.crud.kodillalibrary.domain.Book;
import com.crud.kodillalibrary.domain.BookCopy;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
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
    public void testBookCopyRepositorySave() {
        //Given
        Book book1 = new Book("title1", "author1", 1983);

        //When
        BookCopy book1Copy1 = new BookCopy(book1);
        book1.getBookCopies().add(book1Copy1);
        bookCopyRepository.save(book1Copy1);

        BookCopy book1Copy2 = new BookCopy(book1);
        book1.getBookCopies().add(book1Copy2);
        bookCopyRepository.save(book1Copy2);

        //Then
        Long id1 = book1Copy1.getId();
        Long id2 = book1Copy1.getId();
        Optional<BookCopy> readBookCopy1 = bookCopyRepository.findById(id1);
        Optional<BookCopy> readBookCopy2 = bookCopyRepository.findById(id2);
        Assert.assertTrue(readBookCopy1.isPresent());
        Assert.assertTrue(readBookCopy2.isPresent());

        //CleanUp
        bookCopyRepository.deleteById(id1);
        bookCopyRepository.deleteById(id2);
    }

    @Test
    public void getAvailableBookCopiesOfBook() {
        //Given
        Book book1 = new Book("title1", "author1", 1983);

        BookCopy book1Copy1 = new BookCopy(book1);
        book1.getBookCopies().add(book1Copy1);

        BookCopy book1Copy2 = new BookCopy(book1);
        book1.getBookCopies().add(book1Copy2);

        BookCopy book1Copy3 = new BookCopy(book1);
        book1.getBookCopies().add(book1Copy3);

        //When
        bookCopyRepository.save(book1Copy1);
        bookCopyRepository.save(book1Copy2);
        bookCopyRepository.save(book1Copy3);

        Long book1Copy1Id = book1Copy1.getId();
        Long book1Copy2Id = book1Copy2.getId();
        Long book1Copy3Id = book1Copy3.getId();
        Long book1Id = book1.getId();

        //Then
        List<BookCopy> readList = bookCopyRepository.getAvailableBookCopiesOfBook(book1Id);
        Assert.assertEquals(3, readList.size());

        //CleanUp
        bookCopyRepository.deleteById(book1Copy1Id);
        bookCopyRepository.deleteById(book1Copy2Id);
        bookCopyRepository.deleteById(book1Copy3Id);
    }
}
