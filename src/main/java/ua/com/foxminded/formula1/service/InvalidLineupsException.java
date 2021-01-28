package ua.com.foxminded.formula1.service;

public class InvalidLineupsException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    
    InvalidLineupsException(String msg) {
        super(msg);
    }
}
