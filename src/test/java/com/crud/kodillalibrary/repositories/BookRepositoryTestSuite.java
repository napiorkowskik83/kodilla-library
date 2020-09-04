package com.crud.kodillalibrary.repositories;

import com.crud.kodillalibrary.domain.Book;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookRepositoryTestSuite {
    @Autowired
    BookRepository bookRepository;

    @Test
    public void testBookRepositorySave(){
        //Given
        Book book1 = new Book("title1", "author1", 1983);

        //When
        bookRepository.save(book1);

        //Then
        Long id = book1.getId();
        Optional<Book> readBook = bookRepository.findById(id);
        Assert.assertTrue(readBook.isPresent());

        //CleanUp
        bookRepository.deleteById(id);
    }
}
