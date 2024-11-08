package com.ada.holiday_party_planning.exceptions.response;

import java.time.LocalDateTime;

/**
 * Classe que representa a resposta de uma exceção, incluindo detalhes sobre o erro.
 */

public class ExceptionResponse {

    private final LocalDateTime timeStamp;
    private final String errorType;
    private final String message;

    /**
     * Construtor da classe que inicializa os campos da resposta de exceção.
     *
     * @param timeStamp O momento em que a exceção ocorreu.
     * @param errorCode O tipo do erro.
     * @param message   A mensagem de erro detalhada.
     */

    public ExceptionResponse(LocalDateTime timeStamp, String errorCode, String message) {
        this.timeStamp = timeStamp;
        this.errorType = errorCode;
        this.message = message;

    }

    // Getters

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
