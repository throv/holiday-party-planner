package com.ada.holiday_party_planning.exceptions;

/**
 * Exceção lançada quando o proprietário de uma festa não é encontrado.
 * Essa exceção é uma subclass de RuntimeException e é utilizada para indicar que não
 * foi possível localizar um proprietário de festa com os dados fornecidos.
 * Geralmente, é usada quando o sistema não encontra o proprietário ao tentar realizar
 * operações de busca ou verificação.
 */

public class PartyOwnerNotFoundException extends RuntimeException {

    /**
     * Construtor padrão que inicializa a exceção com uma mensagem padrão.
     * A mensagem padrão é: "No Party Owners found."
     */

    public PartyOwnerNotFoundException() {
        super("No Party Owners found.");
    }

    /**
     * Construtor que permite personalizar a mensagem de erro.
     *
     * @param message A mensagem detalhada sobre o erro que ocorreu.
     */

    public PartyOwnerNotFoundException(String message) {
        super(message);
    }
}
