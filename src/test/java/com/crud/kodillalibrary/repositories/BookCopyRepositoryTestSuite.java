package com.crud.kodillalibrary.repositories;

import com.crud.kodillalibrary.domain.Book;
import com.crud.kodillalibrary.domain.BookCopy;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookCopyRepositoryTestSuite {
    @Autowired
    BookCopyRepository bookCopyRepository;

    @Test
    public void testBookCopyRepositorySave() {
        //Given
        Book book1 = new Book("title1", "author1", 1983);
        BookCopy book1Copy1 = new BookCopy(book1);
        book1.getBookCopies().add(book1Copy1);

        //When
        bookCopyRepository.save(book1Copy1);

        //Then
        Long id = book1Copy1.getId();
        Optional<BookCopy> readBookCopy = bookCopyRepository.findById(id);
        Assert.assertTrue(readBookCopy.isPresent());

        //CleanUp
        bookCopyRepository.deleteById(id);
    }
}
