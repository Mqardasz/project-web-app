package com.project.service;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.project.model.Projekt;

public interface ProjektService {
	
	Optional<Projekt> getProjekt(Integer projektId);	// read
	Projekt setProjekt(Projekt projekt);				// create, update
	void deleteProjekt(Integer projektId);				// delete
	Page<Projekt> getProjekty(Pageable pageable);		// read
	Page<Projekt> searchByNazwa(String nazwa, Pageable pageable);	// read
	
}