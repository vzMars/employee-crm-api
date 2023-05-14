package dev.marcosgonzalez.employeecrmapi.exception;

public class InvalidMongoIdException extends RuntimeException {

    private static final long serialVersionUID = 2;

    public InvalidMongoIdException(String message) {
        super(message);
    }
}
