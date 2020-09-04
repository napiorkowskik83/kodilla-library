package com.crud.kodillalibrary.services;

import com.crud.kodillalibrary.domain.Reader;
import com.crud.kodillalibrary.repositories.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReaderDbService {
    private final ReaderRepository readerRepository;

    @Autowired
    public ReaderDbService(ReaderRepository readerRepository){
        this.readerRepository = readerRepository;
    }

    public List<Reader> getAllReaders(){
        return readerRepository.findAll();
    }

    public Optional<Reader> getReader(final Long id){
        return readerRepository.findById(id);
    }

    public Reader saveReader(final Reader reader){
        return readerRepository.save(reader);
    }

    public void deleteReader(final Long readerId){
        readerRepository.deleteById(readerId);
    }
}
