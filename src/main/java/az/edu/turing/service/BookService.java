package az.edu.turing.service;

import az.edu.turing.entity.Book;

import java.util.List;

public interface BookService {

    Book addBook(Book book);

    Book updateBook(Book book);

    boolean deleteBook(int bookId);

    List<Book> getAllBooks();

    Book getBookById(int bookId);

}
