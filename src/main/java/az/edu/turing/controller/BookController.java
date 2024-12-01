package controller;

import entity.Book;
import exception.BookNotFoundException;
import exception.ValidationException;
import model.dto.BookDTO;
import service.BookService;

import java.util.List;
import java.util.stream.Collectors;

public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    public BookDTO addBook(BookDTO bookDTO) {
        try {
            Book book = new Book(0, bookDTO.getTitle(), bookDTO.getAuthor(), bookDTO.getCategory(), true);
            Book addedBook = bookService.addBook(book);
            return new BookDTO(
                    addedBook.getId(),
                    addedBook.getTitle(),
                    addedBook.getAuthor(),
                    addedBook.getCategory(),
                    addedBook.isStatus());
        } catch (ValidationException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    public List<BookDTO> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return books.stream()
                .map(book -> new BookDTO(book.getId(), book.getTitle(), book.getAuthor(), book.getCategory(), book.isStatus()))
                .collect(Collectors.toList());
    }

    public boolean deleteBook(int id) {
        try {
            return bookService.deleteBook(id);
        } catch (BookNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

    public BookDTO updateBook(BookDTO bookDTO) {
        try {
            Book bookToUpdate = new Book(
                    bookDTO.getId(),
                    bookDTO.getTitle(),
                    bookDTO.getAuthor(),
                    bookDTO.getCategory(),
                    bookDTO.isStatus()
            );
            Book updatedBook = bookService.updateBook(bookToUpdate);
            return new BookDTO(
                    updatedBook.getId(),
                    updatedBook.getTitle(),
                    updatedBook.getAuthor(),
                    updatedBook.getCategory(),
                    updatedBook.isStatus());
        } catch (ValidationException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

}
