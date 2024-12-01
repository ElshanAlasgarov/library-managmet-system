package az.edu.turing.service.impl;

import az.edu.turing.dao.abstracts.ReaderDao;
import az.edu.turing.entity.Reader;
import az.edu.turing.exception.ReaderNotFoundException;
import az.edu.turing.exception.ValidationException;
import az.edu.turing.service.ReaderService;

import java.util.List;
import java.util.Optional;

public class ReaderServiceImpl implements ReaderService {

    private final ReaderDao readerDao;

    public ReaderServiceImpl(ReaderDao readerDao){
        this.readerDao = readerDao;
    }

    @Override
    public Reader addReader(Reader reader) {
        validateReader(reader);
        try {
            return readerDao.add(reader);
        } catch (Exception e) {
            throw new ValidationException("Error occurred while adding the reader:" + e.getMessage());
        }

    }

    @Override
    public Reader updateReader(Reader reader) {
        validateReader(reader);
        Optional<Reader> existingBook = readerDao.getById(reader.getId());
        if (!existingBook.isPresent()) {
            throw new ReaderNotFoundException("Reader not found!");
        }
        readerDao.update(reader);
        return reader;
    }

    @Override
    public boolean deleteReader(int readerId) {
        Optional<Reader> existingReader = readerDao.getById(readerId);
        if (existingReader.isEmpty()) {
            throw new ReaderNotFoundException("Reader not found with ID: " + readerId);
        }
        readerDao.deleteById(readerId);
        return true;
    }

    @Override
    public Reader getReaderById(int readerId) {
        Optional<Reader> reader = readerDao.getById(readerId);
        if (reader.isEmpty()){
            throw new ReaderNotFoundException("Reader not found with ID: " + readerId);
        }
        return reader.get();
    }

    @Override
    public List<Reader> getAllReaders() {
        return readerDao.getAll();
    }

    private void validateReader(Reader reader) throws ValidationException {
        if (reader.getName() == null || reader.getName().isEmpty()) {
            throw new ValidationException("Reader name cannot be empty.");
        }
        if (reader.getEmail() == null || reader.getEmail().isEmpty()) {
            throw new ValidationException("Reader email cannot be empty.");
        }
        if (reader.getPhone() == null || reader.getPhone().isEmpty()) {
            throw new ValidationException("Reader phone number cannot be empty.");
        }
    }
}
