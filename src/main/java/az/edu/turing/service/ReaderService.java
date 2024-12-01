package az.edu.turing.service;

import az.edu.turing.entity.Reader;
import java.util.List;

public interface ReaderService {

    Reader addReader(Reader reader);

    Reader updateReader(Reader reader);

    boolean deleteReader(int readerId);

    Reader getReaderById(int readerId) ;

    List<Reader> getAllReaders();
}
