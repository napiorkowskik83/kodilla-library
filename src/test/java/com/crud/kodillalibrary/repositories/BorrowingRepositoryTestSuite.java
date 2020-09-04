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

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BorrowingRepositoryTestSuite {
    @Autowired
    BorrowingRepository borrowingRepository;

    @Test
    public void testBorrowingRepositorySave() {
        //Given
        Book book1 = new Book("title1", "author1", 1983);
        BookCopy book1Copy1 = new BookCopy(book1);
        book1.getBookCopies().add(book1Copy1);
        Reader reader1 = new Reader("Joe", "Doe");
        Borrowing borrowing1 = new Borrowing(book1Copy1, reader1);
        book1Copy1.getBorrowingList().add(borrowing1);
        reader1.getBorrowings().add(borrowing1);

        //When
        borrowingRepository.save(borrowing1);

        //Then
        Long id = borrowing1.getId();
        Optional<Borrowing> readBorrowing = borrowingRepository.findById(id);
        Assert.assertTrue(readBorrowing.isPresent());

//        System.out.println(borrowing1.getId() + "; " + borrowing1.getBookCopy() + "; " + borrowing1.getBookCopy().getBook());
//        if(readBorrowing.isPresent()){
//            System.out.println(readBorrowing.get().getId() + "; " + readBorrowing.get().getBookCopy() + "; "
//                    + readBorrowing.get().getBookCopy().getBook());
//        }

        //CleanUp
        borrowingRepository.deleteById(id);
    }
}
