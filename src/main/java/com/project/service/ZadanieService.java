package com.project.service;

import com.project.model.Zadanie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ZadanieService {

    Zadanie setZadanie(Zadanie zadanie);

    Optional<Zadanie> getZadanieById(Integer id);

    void deleteZadanieById(Integer id);

    Zadanie updateZadanie(Integer id, Zadanie updatedZadanie);

    Page<Zadanie> getAllZadania(Pageable pageable);

    Page<Zadanie> getZadaniaByProjektId(Integer projektId, Pageable pageable);
}