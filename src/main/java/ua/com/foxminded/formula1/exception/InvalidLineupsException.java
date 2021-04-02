package ua.com.foxminded.formula1.exception;

public class InvalidLineupsException extends RuntimeException {

    private static final long serialVersionUID = 100L;

    public InvalidLineupsException(String msg) {
        super(msg);
    }
}
