package com.crud.kodillalibrary.services;

import com.crud.kodillalibrary.controllers.ReaderNotFoundException;
import com.crud.kodillalibrary.domain.Reader;
import com.crud.kodillalibrary.domain.ReaderDto;
import com.crud.kodillalibrary.mappers.ReaderMapper;
import com.crud.kodillalibrary.repositories.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReaderDbService {
    private final ReaderRepository readerRepository;
    private final ReaderMapper readerMapper;

    @Autowired
    public ReaderDbService(final ReaderRepository readerRepository, final ReaderMapper readerMapper) {
        this.readerRepository = readerRepository;
        this.readerMapper = readerMapper;
    }

    public List<Reader> getAllReaders() {
        return readerRepository.findAll();
    }

    public Reader getReader(final Long id) throws ReaderNotFoundException {
        return readerRepository.findById(id).orElseThrow(ReaderNotFoundException::new);
    }

    public Reader saveReader(final Reader reader) {
        return readerRepository.save(reader);
    }

    public Reader updateReader(final ReaderDto readerDto) throws ReaderNotFoundException {
        readerRepository.findById(readerDto.getId()).orElseThrow(ReaderNotFoundException::new);
        return readerRepository.save(readerMapper.mapToReader(readerDto));
    }

    public void deleteReader(final Long readerId) {
        readerRepository.deleteById(readerId);
    }
}
