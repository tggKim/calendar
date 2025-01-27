package org.example.calendar.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler
    protected ResponseEntity<String> handlePasswordInvalidException(PasswordInvalidException ex){
        return new ResponseEntity<>(ex.getErrorCode().getMessage(), ex.getErrorCode().getHttpStatus());
    }
}
