package com.crud.kodillalibrary.controllers;

import com.crud.kodillalibrary.domain.ReaderDto;
import com.crud.kodillalibrary.mappers.ReaderMapper;
import com.crud.kodillalibrary.services.ReaderDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/library/reader")
@CrossOrigin(origins = "*")
public class ReaderController {
    private final ReaderDbService readerDbService;
    private final ReaderMapper readerMapper;

    @Autowired
    public ReaderController(ReaderDbService readerDbService, ReaderMapper readerMapper) {
        this.readerDbService = readerDbService;
        this.readerMapper = readerMapper;
    }

    @PostMapping(value = "addReader", consumes = APPLICATION_JSON_VALUE)
    public void addReader(@RequestBody ReaderDto readerDto) {
        readerDbService.saveReader(readerMapper.mapToReader(readerDto));
    }

    @PutMapping(value = "updateReader")
    public ReaderDto updateReader(@RequestBody ReaderDto readerDto) throws ReaderNotFoundException {
        return readerMapper.mapToReaderDto(readerDbService.updateReader(readerDto));
    }

    @GetMapping(value = "getReaders")
    public List<ReaderDto> getReaders() {
        return readerMapper.mapToReaderDtoList(readerDbService.getAllReaders());
    }

    @GetMapping(value = "getReader")
    public ReaderDto getReader(@RequestParam Long readerId) throws ReaderNotFoundException {
        return readerMapper.mapToReaderDto(readerDbService.getReader(readerId));
    }
}
