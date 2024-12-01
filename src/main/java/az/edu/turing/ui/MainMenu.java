package az.edu.turing.ui;

import az.edu.turing.controller.BookController;
import az.edu.turing.controller.LoanController;
import az.edu.turing.controller.ReaderController;
import az.edu.turing.model.dto.BookDTO;
import az.edu.turing.model.dto.LoanDTO;
import az.edu.turing.model.dto.ReaderDTO;

import java.util.List;
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
                System.out.println("Book updated successfully!");
                System.out.println("New details:");
                System.out.println("ID: " + updatedBook.getId() + ", Title: " + updatedBook.getTitle() +
                        ", Author: " + updatedBook.getAuthor() + ", Category: " + updatedBook.getCategory());
            } else {
                System.out.println("Error updating book.");
            }
        } catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void deleteBook(Scanner scanner) {
        System.out.println("\nDelete Book");
        try {
            System.out.print("Enter book ID to delete: ");
            int id = scanner.nextInt();

            boolean isDeleted = bookController.deleteBook(id);
            if (isDeleted) {
                System.out.println("Book deleted successfully!");
            } else {
                System.out.println("Book not found or deletion failed.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void viewAllBooks() {
        System.out.println("\nAll Books:");
        try {
            List<BookDTO> books = bookController.getAllBooks();
            if (books.isEmpty()) {
                System.out.println("No books available.");
            } else {
                for (BookDTO book : books) {
                    System.out.println("ID: " + book.getId() +
                            ", Title: " + book.getTitle() +
                            ", Author: " + book.getAuthor() +
                            ", Category: " + book.getCategory() +
                            ", Status: " + (book.isStatus() ? "Available" : "Not Available"));
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void manageReaders(Scanner scanner) {
        while (true){
            System.out.println("\nManage Readers Menu");
            System.out.println("1. Add Reader");
            System.out.println("2. Update Reader");
            System.out.println("3. Delete Reader");
            System.out.println("4. View All Readers");
            System.out.println("5. Back to Main Menu");
            System.out.println("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice){
                case 1:
                    addReader(scanner);
                    break;
                case 2:
                    updateReader(scanner);
                    break;
                case 3:
                    deleteReader(scanner);
                    break;
                case 4:
                    viewAllReaders(scanner);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    private void addReader(Scanner scanner) {
        System.out.println("\nAdd Reader");
        try {
            System.out.println("Enter reader name: ");
            String name = scanner.nextLine();

            System.out.println("Enter reader email: ");
            String email = scanner.nextLine();

            System.out.println("Enter reader phone: ");
            String phone = scanner.nextLine();

            ReaderDTO newReaderDTO = new ReaderDTO(name,email,phone);
            ReaderDTO addedReader = readerController.addReader(newReaderDTO);

            if(addedReader != null){
                System.out.println("Reader added successfully!");
                System.out.println("ID: " + addedReader.getId());
            }else {
                System.out.println("Error adding reader.");
            }
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void updateReader(Scanner scanner) {
        System.out.println("\nUpdate Reader");
        try {
            System.out.println("Enter reader ID to update: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter new name: ");
            String name = scanner.nextLine();

            System.out.print("Enter new email: ");
            String email = scanner.nextLine();

            System.out.print("Enter new phone: ");
            String phone = scanner.nextLine();

            ReaderDTO updateReaderDTO = new ReaderDTO(id,name,email,phone);
            ReaderDTO updatedReader = readerController.update(updateReaderDTO);

            if (updatedReader != null) {
                System.out.println("Reader updated successfully!");
                System.out.println("New details:");
                System.out.println("ID: " + updatedReader.getId() +
                        ", Name: " + updatedReader.getName() +
                        ", Email: " + updatedReader.getEmail() +
                        ", Phone: " + updatedReader.getPhone());
            } else {
                System.out.println("Error updating reader.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void deleteReader(Scanner scanner) {
        System.out.println("\nDelete Reader");
        try {
            System.out.print("Enter reader ID to delete: ");
            int id = scanner.nextInt();

            boolean isDeleted = readerController.deleteReader(id);
            if (isDeleted) {
                System.out.println("Reader deleted successfully!");
            } else {
                System.out.println("Reader not found or deletion failed.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void viewAllReaders(Scanner scanner) {
        System.out.println("\nAll Readers:");
        try {
            List<ReaderDTO> readers = readerController.getAllReaders();
            if (readers.isEmpty()) {
                System.out.println("No readers available.");
            } else {
                for (ReaderDTO reader : readers) {
                    System.out.println("ID: " + reader.getId() +
                            ", Name: " + reader.getName() +
                            ", Email: " + reader.getEmail() +
                            ", Phone: " + reader.getPhone());
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void manageLoans(Scanner scanner) {

        while (true) {
            System.out.println("\nManage Loans Menu");
            System.out.println("1. Borrow Book");
            System.out.println("2. Return Book");
            System.out.println("3. View Loans by Reader");
            System.out.println("4. Back to Main Menu");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    borrowBook(scanner);
                    break;
                case 2:
                    returnBook(scanner);
                    break;
                case 3:
                    viewLoansByReader(scanner);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }

    }

    private void borrowBook(Scanner scanner) {
        System.out.println("\nBorrow Book");
        try {
            System.out.print("Enter book ID: ");
            int bookID = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter reader ID: ");
            int readerID = scanner.nextInt();
            scanner.nextLine();

            LoanDTO loanDTO = loanController.borrowBook(bookID,readerID);

            if (loanDTO == null){
                System.out.println("Book borrowed successfully!");
                System.out.println("Loan ID: " + loanDTO.getId());
                System.out.println("Borrow Date: " + loanDTO.getBorrowDate());
            }else {
                System.out.println("Error borrowing book. Please try again.");
            }
        } catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void returnBook(Scanner scanner) {
        System.out.println("\nReturn Book");
        try{
            System.out.println("Enter loan ID: ");
            int loanID = scanner.nextInt();
            scanner.nextLine();

            LoanDTO returnLoanDTO = loanController.returnBook(loanID);

            if (returnLoanDTO != null){
                System.out.println("Book returned successfully!");
                System.out.println("Return Date: " + returnLoanDTO.getReturnDate());
                if (returnLoanDTO.getPenalty() > 0){
                    System.out.printf("Penalty: %f $",returnLoanDTO.getPenalty());
                } else {
                    System.out.println("Error returning book. Please check loan ID.");
                }
            }
        } catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void viewLoansByReader(Scanner scanner) {
        System.out.println("\nView Loans By Reader");
        try {
            System.out.println("Enter reader ID: ");
            int readerID = scanner.nextInt();
            scanner.nextLine();

            List<LoanDTO> loans = loanController.getLoansByReaderId(readerID);

            if (loans.isEmpty()){
                System.out.println("No loans for the reader.");
            }else {
                System.out.println("Loans for Reader ID: " + readerID);
                for (LoanDTO loan : loans){
                    System.out.println("Loan ID: " +loan.getId() +
                            ", Book ID: " +loan.getBookId() +
                            ", Borrow Date: " + loan.getBorrowDate() +
                            ", Return Date: " + (loan.getBorrowDate() != null ? loan.getReturnDate() : "Not Returned") +
                            ", Penalty: " +loan.getPenalty() + " $");
                }
            }
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }
}
