package com.crud.kodillalibrary.repositories;

import com.crud.kodillalibrary.domain.Reader;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ReaderRepository extends CrudRepository<Reader, Long> {

    @Override
    List<Reader> findAll();

    @Override
    Reader save(Reader reader);

    @Override
    Optional<Reader> findById(Long id);
}
