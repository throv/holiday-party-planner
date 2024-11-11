package com.ada.holiday_party_planning.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * DTO utiliznemado para atualizar as informações de um evento.
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

    private String description;

    private Boolean funActivate;

    private String categoryFun;

    /**
     * Construtor para inicializar os dados do evento.
     *
     * @param theme Tema do evento.
     * @param title Título do evento.
     * @param date  Data do evento.
     * @param place Local do evento.
     */

    public UpdateEventDTO(String theme, String title, LocalDateTime date, String place,
                          String description, Boolean funActivate,
                          String categoryFun, String descriptionTranslateFun) {
        this.theme = theme;
        this.title = title;
        this.date = date;
        this.place = place;
        this.description = description;
        this.funActivate = funActivate;
        this.categoryFun = categoryFun;
    }

    // Getters e Setters


    public @NotBlank(message = "Theme cannot be empty") String getTheme() {
        return theme;
    }

    public void setTheme(@NotBlank(message = "Theme cannot be empty") String theme) {
        this.theme = theme;
    }

    public @NotBlank(message = "Title cannot be empty") String getTitle() {
        return title;
    }

    public void setTitle(@NotBlank(message = "Title cannot be empty") String title) {
        this.title = title;
    }

    public @NotNull(message = "Date cannot be null") LocalDateTime getDate() {
        return date;
    }

    public void setDate(@NotNull(message = "Date cannot be null") LocalDateTime date) {
        this.date = date;
    }

    public @NotBlank(message = "Place cannot be empty") String getPlace() {
        return place;
    }

    public void setPlace(@NotBlank(message = "Place cannot be empty") String place) {
        this.place = place;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getFunActivate() {
        return funActivate;
    }

    public void setFunActivate(Boolean funActivate) {
        this.funActivate = funActivate;
    }

    public String getCategoryFun() {
        return categoryFun;
    }

    public void setCategoryFun(String categoryFun) {
        this.categoryFun = categoryFun;
    }
}


