package com.project.model;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Zadanie {

    private Integer zadanieId;

    private Projekt projektId;

    @NotBlank(message = "{projekt.nazwa.notblank}")
    @Size(min = 3, max = 50, message = "{projekt.nazwa.size}")
    private String nazwa;

    private Integer kolejnosc;

    @Size(min = 3, max = 50, message = "{projekt.nazwa.size}")
    private String opis;

    @NotBlank(message = "{projekt.nazwa.notblank}")
    private LocalDateTime dataczasDodania;
}