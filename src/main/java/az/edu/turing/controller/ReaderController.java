package controller;

import entity.Reader;
import exception.ValidationException;
import model.dto.ReaderDTO;
import service.ReaderService;

import java.util.List;
import java.util.stream.Collectors;

public class ReaderController {
    private final ReaderService readerService;

    public ReaderController(ReaderService readerService) {
        this.readerService = readerService;
    }

    public ReaderDTO addReader(ReaderDTO readerDTO) {
        try {
            Reader reader = new Reader(0, readerDTO.getName(), readerDTO.getEmail(), readerDTO.getPhone());
            Reader addedReader = readerService.addReader(reader);
            return new ReaderDTO(addedReader.getId(), addedReader.getName(), addedReader.getEmail(), addedReader.getPhone());
        } catch (ValidationException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    public List<ReaderDTO> getAllReaders() {
        List<Reader> readers = readerService.getAllReaders();
        return readers.stream()
                .map(reader -> new ReaderDTO(reader.getId(), reader.getName(), reader.getEmail(), reader.getPhone()))
                .collect(Collectors.toList());
    }
}