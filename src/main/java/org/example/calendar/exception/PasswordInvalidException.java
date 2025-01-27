package org.example.calendar.exception;

import org.example.calendar.error.ErrorCode;

public class PasswordInvalidException extends RuntimeException{
    private ErrorCode errorCode = ErrorCode.INVALID_PASSWORD;

    public ErrorCode getErrorCode(){
        return errorCode;
    }
}
