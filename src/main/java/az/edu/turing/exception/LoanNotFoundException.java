package az.edu.turing.exception;

public class LoanNotFoundException extends RuntimeException{

    public LoanNotFoundException(String message) {
        super(message);
    }
}
