package com.ada.holiday_party_planning.exceptions.response;


import java.time.LocalDateTime;

public class ExceptionResponse {

    private final LocalDateTime timeStamp;
    private final String errorType;
    private final String message;

    public ExceptionResponse(LocalDateTime timeStamp, String errorCode, String message) {
        this.timeStamp = timeStamp;
        this.errorType = errorCode;
        this.message = message;

    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public String getErrorCode() {
        return errorType;
    }
}
