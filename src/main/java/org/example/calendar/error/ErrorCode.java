package org.example.calendar.error;

import org.springframework.http.HttpStatus;

public enum ErrorCode {

    // 400 에러


    // 401 에러
    INVALID_PASSWORD("비밀번호가 잘못되었습니다.", HttpStatus.UNAUTHORIZED),

    // 404 에러
    SCHEDULE_NOT_FOUND("id에 해당하는 일정이 없습니다.", HttpStatus.NOT_FOUND),
    USER_NOT_FOUND("userId에 해당하는 유저가 없습니다.", HttpStatus.NOT_FOUND);

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
