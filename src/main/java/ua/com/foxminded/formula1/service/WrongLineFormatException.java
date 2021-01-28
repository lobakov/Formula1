package ua.com.foxminded.formula1.service;

public class WrongLineFormatException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    
    WrongLineFormatException(String msg) {
        super(msg);
    }
}
