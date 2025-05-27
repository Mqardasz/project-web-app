package com.project.model;

import java.util.Set;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Student {

    private Integer studentId;

    @NotBlank(message = "{projekt.nazwa.notblank}")
    @Size(min = 3, max = 50, message = "{projekt.nazwa.size}")
    private String imie;

    @NotBlank(message = "{projekt.nazwa.notblank}")
    @Size(min = 3, max = 100, message = "{projekt.nazwa.size}")
    private String nazwisko;

    @NotBlank(message = "{projekt.nazwa.notblank}")
    @Size(min = 3, max = 20, message = "{projekt.nazwa.size}")
    private String nrIndeksu;

    @NotBlank(message = "{projekt.nazwa.notblank}")
    @Size(min = 3, max = 50, message = "{projekt.nazwa.size}")
    private String email;

    @NotNull(message = "{projekt.nazwa.notblank}")
    private Boolean stacjonarny;

    // W DTO przekazuj tylko ID powiązanych projektów, nie całe obiekty!
    @JsonIgnore
    private Set<Integer> projektyIds;
}