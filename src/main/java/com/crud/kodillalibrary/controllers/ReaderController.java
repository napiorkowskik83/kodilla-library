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
    public ReaderController(ReaderDbService readerDbService, ReaderMapper readerMapper){
        this.readerDbService = readerDbService;
        this.readerMapper = readerMapper;
    }

    @RequestMapping(method = RequestMethod.POST, value = "addReader", consumes = APPLICATION_JSON_VALUE)
    public void addReader(@RequestBody ReaderDto readerDto) {
        readerDbService.saveReader(readerMapper.mapToReader(readerDto));
    }

    @RequestMapping(method = RequestMethod.GET, value = "getReaders")
    public List<ReaderDto> getReaders() {
        return readerMapper.mapToReaderDtoList(readerDbService.getAllReaders());
    }

    @RequestMapping(method = RequestMethod.GET, value = "getReader")
    public ReaderDto getReader(@RequestParam Long readerId) throws ItemNotFoundException {
        return readerMapper.mapToReaderDto(readerDbService.getReader(readerId).orElseThrow(ItemNotFoundException::new));
    }
}
