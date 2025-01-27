package org.example.calendar.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler
    protected ResponseEntity<String> handleException401(Exception401 ex){
        return new ResponseEntity<>(ex.getErrorCode().getMessage(), ex.getErrorCode().getHttpStatus());
    }

    @ExceptionHandler
    protected ResponseEntity<String> handleException404(Exception404 ex){
        return new ResponseEntity<>(ex.getErrorCode().getMessage(), ex.getErrorCode().getHttpStatus());
    }
}
