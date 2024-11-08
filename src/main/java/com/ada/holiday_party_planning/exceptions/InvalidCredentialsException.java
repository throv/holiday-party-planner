package com.ada.holiday_party_planning.exceptions;

/**
 * Exceção lançada quando as credenciais fornecidas são inválidas.
 * Essa exceção é uma subclass de RuntimeException e é utilizada para sinalizar um erro quando
 * o email ou a senha fornecidos para autenticação não são válidos.
 * Normalmente, é usada em cenários de login ou autenticação de usuários.
 */

public class InvalidCredentialsException extends RuntimeException {

    /**
     * Construtor padrão que inicializa a exceção com uma mensagem padrão.
     * A mensagem padrão é: "Email or password is not valid."
     */

    public InvalidCredentialsException() {
        super("Email or password is not valid.");
    }

    /**
     * Construtor que permite personalizar a mensagem de erro.
     *
     * @param message A mensagem detalhada sobre o erro que ocorreu.
     */

    public InvalidCredentialsException(String message) {
        super(message);
    }
}
