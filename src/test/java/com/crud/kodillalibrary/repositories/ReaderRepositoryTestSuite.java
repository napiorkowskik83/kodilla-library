package com.crud.kodillalibrary.repositories;

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
public class ReaderRepositoryTestSuite {
    @Autowired
    ReaderRepository readerRepository;

    @Test
    public void testReaderRepositorySave() {
        //Given
        Reader reader1 = new Reader("Joe", "Doe");

        //When
        readerRepository.save(reader1);

        //Then
        Long id = reader1.getId();
        Optional<Reader> readReader = readerRepository.findById(id);
        Assert.assertTrue(readReader.isPresent());

        //CleanUp
        readerRepository.deleteById(id);
    }
}
