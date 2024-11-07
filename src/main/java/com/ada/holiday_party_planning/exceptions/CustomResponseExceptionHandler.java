package com.ada.holiday_party_planning.exceptions;

import com.ada.holiday_party_planning.exceptions.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class CustomResponseExceptionHandler extends ResponseEntityExceptionHandler {

    private ResponseEntity<ExceptionResponse> getExceptionResponse(HttpStatus status, String errorType, String message) {

        ExceptionResponse exceptionResponse = new ExceptionResponse(
                LocalDateTime.now(),
                errorType,
                message
        );

        return ResponseEntity.status(status).body(exceptionResponse);

    }

    @ExceptionHandler(PartyOwnerNotFoundException.class)
    public ResponseEntity<ExceptionResponse> partyOwnerNotFoundHandler(PartyOwnerNotFoundException exception) {

        return getExceptionResponse(
                HttpStatus.NOT_FOUND,
                "PARTY_OWNER_NOT_FOUND",
                exception.getMessage()
        );

    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ExceptionResponse> invalidCredentialsHandler(InvalidCredentialsException exception) {

        return getExceptionResponse(
                HttpStatus.UNAUTHORIZED,
                "INVALID_CREDENTIALS",
                exception.getMessage()
        );
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> emailAlreadyExistsHandler(EmailAlreadyExistsException exception) {

        return getExceptionResponse(
                HttpStatus.BAD_REQUEST,
                "EMAIL_ALREADY_EXISTS",
                exception.getMessage()
        );
    }

    @ExceptionHandler(EventNotFoundException.class)
    public ResponseEntity<ExceptionResponse> eventNotFoundHandler(EventNotFoundException exception) {

        return getExceptionResponse(
                HttpStatus.NOT_FOUND,
                "EVENT_NOT_FOUND",
                exception.getMessage()
        );
    }
}
