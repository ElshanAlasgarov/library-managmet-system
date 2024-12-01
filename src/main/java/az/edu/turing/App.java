package az.edu.turing;

import az.edu.turing.config.DatabaseConfig;
import az.edu.turing.controller.BookController;
import az.edu.turing.controller.LoanController;
import az.edu.turing.controller.ReaderController;
import az.edu.turing.dao.impl.database.BookDaoDatabaseImpl;
import az.edu.turing.dao.impl.database.LoanDaoDatabaseImpl;
import az.edu.turing.dao.impl.database.ReaderDaoDatabaseImpl;
import az.edu.turing.dao.impl.database.schema.TableInitializer;
import az.edu.turing.service.impl.BookServiceImpl;
import az.edu.turing.service.impl.LoanServiceImpl;
import az.edu.turing.service.impl.ReaderServiceImpl;
import az.edu.turing.ui.MainMenu;

import java.sql.Connection;

class App {

    public static void main(String[] args) {

        TableInitializer.initialize(DatabaseConfig.getConnection());
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