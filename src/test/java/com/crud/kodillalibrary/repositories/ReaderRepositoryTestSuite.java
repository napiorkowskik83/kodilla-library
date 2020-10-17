package com.crud.kodillalibrary.repositories;

import com.crud.kodillalibrary.domain.Reader;
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
public class ReaderRepositoryTestSuite {
    @Autowired
    ReaderRepository readerRepository;

    @Test
    public void testReaderRepository() {
        //Given
        Reader reader1 = new Reader("Joe", "Doe");
        Reader reader2 = new Reader("Jan", "Dzik");

        //When
        readerRepository.save(reader1);
        readerRepository.save(reader2);

        //Then
        Long id1 = reader1.getId();
        Long id2 = reader2.getId();
        Optional<Reader> readReader1 = readerRepository.findById(id1);
        Optional<Reader> readReader2 = readerRepository.findById(id2);
        List<Reader> readers = readerRepository.findAll();
        Assert.assertTrue(readReader1.isPresent());
        Assert.assertTrue(readReader2.isPresent());
        Assert.assertEquals(2, readers.size());
        //CleanUp
        readerRepository.deleteById(id1);
        readerRepository.deleteById(id2);
    }
}
