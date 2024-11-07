package com.ada.holiday_party_planning.exceptions;

import com.ada.holiday_party_planning.exceptions.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

/**
 * Classe responsável por centralizar o tratamento de exceções em toda a aplicação.
 * Utiliza a anotação @ControllerAdvice para capturar exceções lançadas pelos controladores e retornar uma resposta personalizada
 * com informações detalhadas sobre o erro ocorrido, incluindo o código de status HTTP, tipo de erro e mensagem.
 * A classe herda de ResponseEntityExceptionHandler, fornecendo uma forma customizada de gerar respostas de erro.
 */

@ControllerAdvice
public class CustomResponseExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Método auxiliar que cria uma resposta de erro personalizada.
     *
     * @param status Código de status HTTP a ser retornado.
     * @param errorType Tipo de erro representado por uma string.
     * @param message Mensagem detalhada sobre o erro.
     * @return Retorna uma ResponseEntity com o status HTTP e um corpo contendo a resposta de erro.
     */

    private ResponseEntity<ExceptionResponse> getExceptionResponse(HttpStatus status, String errorType, String message) {

        ExceptionResponse exceptionResponse = new ExceptionResponse(
                LocalDateTime.now(),
                errorType,
                message
        );

        return ResponseEntity.status(status).body(exceptionResponse);

    }

    /**
     * Manipulador de exceção para o caso de o proprietário da festa não ser encontrado.
     *
     * @param exception A exceção lançada quando o proprietário da festa não é encontrado.
     * @return Retorna uma ResponseEntity com status 404 (Not Found) e detalhes sobre o erro.
     */

    @ExceptionHandler(PartyOwnerNotFoundException.class)
    public ResponseEntity<ExceptionResponse> partyOwnerNotFoundHandler(PartyOwnerNotFoundException exception) {

        return getExceptionResponse(
                HttpStatus.NOT_FOUND,
                "PARTY_OWNER_NOT_FOUND",
                exception.getMessage()
        );

    }

    /**
     * Manipulador de exceção para o caso de credenciais inválidas.
     *
     * @param exception A exceção lançada quando as credenciais fornecidas são inválidas.
     * @return Retorna uma ResponseEntity com status 401 (Unauthorized) e detalhes sobre o erro.
     */

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ExceptionResponse> invalidCredentialsHandler(InvalidCredentialsException exception) {

        return getExceptionResponse(
                HttpStatus.UNAUTHORIZED,
                "INVALID_CREDENTIALS",
                exception.getMessage()
        );
    }

    /**
     * Manipulador de exceção para o caso de um e-mail já estar registrado.
     *
     * @param exception A exceção lançada quando o e-mail fornecido já existe na base de dados.
     * @return Retorna uma ResponseEntity com status 400 (Bad Request) e detalhes sobre o erro.
     */

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> emailAlreadyExistsHandler(EmailAlreadyExistsException exception) {

        return getExceptionResponse(
                HttpStatus.BAD_REQUEST,
                "EMAIL_ALREADY_EXISTS",
                exception.getMessage()
        );
    }

    /**
     * Manipulador de exceção para o caso de o evento não ser encontrado.
     *
     * @param exception A exceção lançada quando o evento não é encontrado.
     * @return Retorna uma ResponseEntity com status 404 (Not Found) e detalhes sobre o erro.
     */

    @ExceptionHandler(EventNotFoundException.class)
    public ResponseEntity<ExceptionResponse> eventNotFoundHandler(EventNotFoundException exception) {

        return getExceptionResponse(
                HttpStatus.NOT_FOUND,
                "EVENT_NOT_FOUND",
                exception.getMessage()
        );
    }
}
