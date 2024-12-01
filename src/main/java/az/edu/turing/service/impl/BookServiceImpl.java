package az.edu.turing.service.impl;

import az.edu.turing.dao.abstracts.BookDao;
import az.edu.turing.entity.Book;
import az.edu.turing.exception.BookNotFoundException;
import az.edu.turing.service.BookService;
import az.edu.turing.exception.ValidationException;
import java.util.List;
import java.util.Optional;

public class BookServiceImpl implements BookService {

    private final BookDao bookDao;

    public BookServiceImpl(BookDao bookDao){
        this.bookDao = bookDao;
    }

    @Override
    public Book addBook(Book book) {
        validateBook(book);
        try {
            return bookDao.add(book);
        }catch (Exception e){
            throw new ValidationException("Error occurred while adding the book: " + e.getMessage());
        }
    }

    @Override
    public Book updateBook(Book book) {
        validateBook(book);
        Optional<Book> existingBook = bookDao.getById(book.getId());
        if (!existingBook.isPresent()) {
            throw new BookNotFoundException("Book not found!");
        }
        bookDao.update(book);
        return book;
    }

    @Override
    public boolean deleteBook(int bookId) {
        Optional<Book> book = bookDao.getById(bookId);
        if (!book.isPresent()) {
            throw new BookNotFoundException("Book not found for deletion!");
        }
        bookDao.deleteById(bookId);
        return true;
    }

    @Override
    public List<Book> getAllBooks() {
        return bookDao.getAll();
    }

    @Override
    public Book getBookById(int bookId) {
        Optional<Book> book = bookDao.getById(bookId);
        if (!book.isPresent()) {
            throw new BookNotFoundException("Book not found!");
        }
        return book.get();
    }

    private void validateBook(Book book) throws ValidationException {
        if (book.getTitle() == null || book.getTitle().isEmpty()) {
            throw new ValidationException("Book title cannot be empty!");
        }
        if (book.getAuthor() == null || book.getAuthor().isEmpty()) {
            throw new ValidationException("Author name cannot be empty!");
        }
        if(book.getCategory() == null || book.getCategory().isEmpty()){
            throw new ValidationException("Book category cannot be empty");
        }
    }
}
