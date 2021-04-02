package ua.com.foxminded.formula1.exception;

public class WrongLineFormatException extends RuntimeException {

    private static final long serialVersionUID = 100L;

    public WrongLineFormatException(String msg) {
        super(msg);
    }
}
