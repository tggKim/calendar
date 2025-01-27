package org.example.calendar.error;

import org.springframework.http.HttpStatus;

public enum ErrorCode {

    // 400 에러
    INVALID_DATE_FORMAT("날짜 형식이 올바르지 않습니다.", HttpStatus.BAD_REQUEST),
    INVALID_SORT_FORMAT("정렬 형식이 올바르지 않습니다.", HttpStatus.BAD_REQUEST),
    INVALID_PAGING_FORMAT("페이징 형식이 올바르지 않습니다. size는 -1보다 크고, page는 1이상 입니다.", HttpStatus.BAD_REQUEST),

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
