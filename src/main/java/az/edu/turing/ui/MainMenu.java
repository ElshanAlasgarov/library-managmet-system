package az.edu.turing.ui;

import az.edu.turing.controller.BookController;
import az.edu.turing.controller.LoanController;
import az.edu.turing.controller.ReaderController;
import az.edu.turing.model.dto.BookDTO;

import java.util.Scanner;

public class MainMenu {

    private final BookController bookController;
    private final ReaderController readerController;
    private final LoanController loanController;

    public MainMenu(BookController bookController, ReaderController readerController, LoanController loanController) {
        this.bookController = bookController;
        this.readerController = readerController;
        this.loanController = loanController;
    }

    public void displayMenu(){

        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("\nLibrary Managment System");
            System.out.println("1. Manage Books");
            System.out.println("2. Manage Readers");
            System.out.println("3. Manage Loans");
            System.out.println("4. Exit");
            System.out.println("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice){
                case 1 -> manageBooks(scanner);
                case 2 -> manageReaders(scanner);
                case 3 -> manageLoans(scanner);
                case 4 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice! Please try again.");
            }
        }
    }
    private void manageBooks(Scanner scanner) {
        while (true) {
            System.out.println("\nManage Books Menu");
            System.out.println("1. Add Book");
            System.out.println("2. Update Book");
            System.out.println("3. Delete Book");
            System.out.println("4. View All Books");
            System.out.println("5. Back to Main Menu");
            System.out.println("Choose an option");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice){
                case 1:
                    addBook(scanner);
                    break;
                case 2:
                    updateBook(scanner);
                    break;
                case 3:
                    deleteBook(scanner);
                    break;
                case 4:
                    viewAllBooks();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    private void addBook(Scanner scanner){
        System.out.println("\nAdd Book");
        try {
            System.out.print("Enter book title: ");
            String title = scanner.nextLine();

            System.out.print("Enter book author: ");
            String author = scanner.nextLine();

            System.out.print("Enter book category: ");
            String category = scanner.nextLine();

            BookDTO newBookDTO = new BookDTO(title,author,category);
            BookDTO addedBook = bookController.addBook(newBookDTO);

            if (addedBook != null){
                System.out.println("Book added successfully!");
                System.out.println("ID: " + addedBook.getId());
            }else {
                System.out.println("Error adding book.");
            }
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }
    private void updateBook(Scanner scanner) {
        System.out.println("\nUpdate Book");
        try {
            System.out.println("Enter book ID to update: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Enter new title: ");
            String title = scanner.nextLine();

            System.out.println("Enter new author: ");
            String author = scanner.nextLine();

            System.out.println("Enter new category: ");
            String category = scanner.nextLine();

            BookDTO updatedBookDto = new BookDTO(id,title,author,category,true);
            BookDTO updatedBook = bookController.updateBook(updatedBookDto);

            if (updatedBook != null){
                System.out.println("Book added successfully!");
                System.out.println("ID: " + updatedBook.getId());
            }else {
                System.out.println("Error adding book.");
            }
        } catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void deleteBook(Scanner scanner) {
    }

    private void viewAllBooks() {
    }

    private void manageReaders(Scanner scanner) {
    }
    private void manageLoans(Scanner scanner) {

    }
}
