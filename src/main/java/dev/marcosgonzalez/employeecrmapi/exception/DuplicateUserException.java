package dev.marcosgonzalez.employeecrmapi.exception;

public class DuplicateUserException extends RuntimeException {

    private static final long serialVersionUID = 1;

    public DuplicateUserException(String message) {
        super(message);
    }
}
