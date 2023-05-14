package dev.marcosgonzalez.employeecrmapi.exception;

public class EmployeeNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 3;

    public EmployeeNotFoundException(String message) {
        super(message);
    }
}
