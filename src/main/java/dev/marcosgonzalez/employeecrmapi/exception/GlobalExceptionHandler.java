package dev.marcosgonzalez.employeecrmapi.exception;

import dev.marcosgonzalez.employeecrmapi.dto.ErrorMsgResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMsgResponse> handleValidationErrors(MethodArgumentNotValidException ex) {
        ErrorMsgResponse error = new ErrorMsgResponse(ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicateUserException.class)
    public ResponseEntity<ErrorMsgResponse> duplicateUserErrors(DuplicateUserException ex) {
        ErrorMsgResponse error = new ErrorMsgResponse(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }
}
