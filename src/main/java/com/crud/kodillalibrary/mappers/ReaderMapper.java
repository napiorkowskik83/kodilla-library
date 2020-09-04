package com.crud.kodillalibrary.mappers;

import com.crud.kodillalibrary.domain.Reader;
import com.crud.kodillalibrary.domain.ReaderDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReaderMapper {

    public ReaderDto mapToReaderDto(Reader reader){
        return new ReaderDto(
                reader.getId(),
                reader.getFirstName(),
                reader.getLastName(),
                reader.getCreated(),
                reader.getBorrowings()
        );
    }

    public Reader mapToReader(ReaderDto readerDto){
        return new Reader(
                readerDto.getId(),
                readerDto.getFirstName(),
                readerDto.getLastName(),
                readerDto.getCreated(),
                readerDto.getBorrowings()
        );
    }

    public List<ReaderDto> mapToReaderDtoList(List<Reader> readerList){
        return readerList.stream()
                .map(t-> new ReaderDto(t.getId(), t.getFirstName(), t.getLastName(), t.getCreated(), t.getBorrowings()))
                .collect(Collectors.toList());
    }
}
