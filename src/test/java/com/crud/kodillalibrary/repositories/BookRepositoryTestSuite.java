package com.crud.kodillalibrary.repositories;

import com.crud.kodillalibrary.domain.Book;
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
public class BookRepositoryTestSuite {
    @Autowired
    BookRepository bookRepository;

    @Test
    public void testBookRepositorySaveAndFindById(){
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

    @Test
    public void testBookRepositoryFindAll(){
        //Given
        Book book1 = new Book("title1", "author1", 1983);
        Book book2 = new Book("title2", "author2", 2019);

        //When
        bookRepository.save(book1);
        bookRepository.save(book2);

        //Then
        Long id1 = book1.getId();
        Long id2 = book2.getId();
        List<Book> retrievedBooks = bookRepository.findAll();
        Assert.assertEquals(2, retrievedBooks.size());

        //CleanUp
        bookRepository.deleteById(id1);
        bookRepository.deleteById(id2);
    }
}
