package service;

import entity.Reader;
import java.util.List;

public interface ReaderService {

    Reader addReader(Reader reader);

    Reader updateReader(Reader reader);

    boolean deleteReader(int readerId);

    Reader getReaderById(int readerId) ;

    List<Reader> getAllReaders();
}
