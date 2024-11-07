package com.ada.holiday_party_planning.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * DTO utilizado para atualizar as informações de um evento.
 * Contém validações para garantir que os campos necessários sejam fornecidos.
 */

public class UpdateEventDTO {
    @NotBlank(message = "Theme cannot be empty")
    private String theme;

    @NotBlank(message = "Title cannot be empty")
    private String title;

    @NotNull(message = "Date cannot be null")
    private LocalDateTime date;

    @NotBlank(message = "Place cannot be empty")
    private String place;

    /**
     * Construtor para inicializar os dados do evento.
     *
     * @param theme Tema do evento.
     * @param title Título do evento.
     * @param date  Data do evento.
     * @param place Local do evento.
     */

    public UpdateEventDTO(String theme, String title, LocalDateTime date, String place) {
        this.theme = theme;
        this.title = title;
        this.date = date;
        this.place = place;
    }

    // Getters e Setters

    public String getTheme() {
            return theme;
        }

    public void setTheme(String theme) {
            this.theme = theme;
        }

    public String getTitle() {
            return title;
        }

    public void setTitle(String title) {
            this.title = title;
        }

    public LocalDateTime getDate() {
            return date;
        }

    public void setDate(LocalDateTime date) {
            this.date = date;
        }

    public String getPlace() {
            return place;
        }

    public void setPlace(String place) {
            this.place = place;
        }

}


