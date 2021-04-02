package ua.com.foxminded.formula1.exception;

public class RaceMismatchException extends RuntimeException {

    private static final long serialVersionUID = 100L;

    public RaceMismatchException(String msg) {
        super(msg);
    }
}
