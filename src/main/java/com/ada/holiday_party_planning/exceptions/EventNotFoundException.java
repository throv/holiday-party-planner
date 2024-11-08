package com.ada.holiday_party_planning.exceptions;

/**
 * Exceção lançada quando um evento não é encontrado na base de dados.
 * Essa exceção é uma subclass de RuntimeException e é utilizada para sinalizar um erro quando
 * uma tentativa de acessar ou manipular um evento que não existe é realizada.
 * Pode ser usada em cenários como busca de eventos ou operações relacionadas a eventos que não foram encontrados.
 */

public class EventNotFoundException extends RuntimeException {

    /**
     * Construtor padrão que inicializa a exceção com uma mensagem padrão.
     * A mensagem padrão é: "No events found."
     */

    public EventNotFoundException() {
        super("No events found.");
    }

    /**
     * Construtor que permite personalizar a mensagem de erro.
     *
     * @param message A mensagem detalhada sobre o erro que ocorreu.
     */

    public EventNotFoundException(String message) {
        super(message);
    }
}
