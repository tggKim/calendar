package org.example.calendar.error;

import org.springframework.http.HttpStatus;

public enum ErrorCode {

    INVALID_PASSWORD("비밀번호가 잘못되었습니다.", HttpStatus.UNAUTHORIZED);

    private String message;
    private HttpStatus httpStatus;

    private ErrorCode(String message, HttpStatus httpStatus){
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus(){
        return httpStatus;
    }
}
