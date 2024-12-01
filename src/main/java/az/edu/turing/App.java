import config.DatabaseConfig;
import controller.BookController;
import controller.LoanController;
import controller.ReaderController;
import dao.abstracts.BookDao;
import dao.impl.database.BookDaoDatabaseImpl;
import dao.impl.database.LoanDaoDatabaseImpl;
import dao.impl.database.ReaderDaoDatabaseImpl;
import entity.Book;
import service.BookService;
import service.impl.BookServiceImpl;
import service.impl.LoanServiceImpl;
import service.impl.ReaderServiceImpl;
import ui.MainMenu;

import java.sql.Connection;

class App {

    public static void main(String[] args) {

        System.out.println("Welcome to the Library Management System!");

        Connection connection = DatabaseConfig.getConnection();

        BookDaoDatabaseImpl bookDaoDatabase = new BookDaoDatabaseImpl(connection);
        ReaderDaoDatabaseImpl readerDaoDatabase = new ReaderDaoDatabaseImpl(connection);
        LoanDaoDatabaseImpl loanDaoDatabase = new LoanDaoDatabaseImpl(connection);

        BookServiceImpl bookService = new BookServiceImpl(bookDaoDatabase);
        ReaderServiceImpl readerService = new ReaderServiceImpl(readerDaoDatabase);
        LoanServiceImpl loanService = new LoanServiceImpl(bookDaoDatabase,readerDaoDatabase,loanDaoDatabase);

        BookController bookController =new BookController(bookService);
        ReaderController readerController = new ReaderController(readerService);
        LoanController loanController = new LoanController(loanService);

        MainMenu mainMenu = new MainMenu(bookController, readerController, loanController);
        mainMenu.displayMenu();

    }
}