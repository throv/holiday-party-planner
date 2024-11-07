package com.ada.holiday_party_planning.exceptions;

/**
 * Exceção lançada quando um e-mail fornecido já existe na base de dados.
 * Essa exceção é uma subclass de RuntimeException e é utilizada para sinalizar um erro quando a tentativa
 * de registro de um e-mail que já está em uso é realizada.
 * Pode ser usada em cenários como criação de usuários ou cadastro de e-mails onde não se permite duplicidade.
 */

public class EmailAlreadyExistsException extends RuntimeException {

    /**
     * Construtor padrão que inicializa a exceção com uma mensagem padrão.
     * A mensagem padrão é: "Email already exists."
     */

    public EmailAlreadyExistsException() {
        super("Email already exists.");
    }

    /**
     * Construtor que permite personalizar a mensagem de erro.
     *
     * @param message A mensagem detalhada sobre o erro que ocorreu.
     */

    public EmailAlreadyExistsException(String message) {
        super(message);
    }
}
