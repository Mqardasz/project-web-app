package com.project.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

@JsonIgnoreProperties(ignoreUnknown = true)
public class Projekt {

	private Integer projektId;
	
	@NotBlank(message = "{projekt.nazwa.notblank}")
	@Size(min = 3, max = 50, message = "{projekt.nazwa.size}")
	private String nazwa;
	
	@Size(min = 1, max = 1000, message = "{projekt.nazwa.size}")
	private String opis;
	
	// działa to automatycznie
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	private LocalDateTime createdDate;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	private LocalDate lastModifiedDate;
	
	@JsonManagedReference
	private List<Zadanie> zadania;
	

	 @JsonIgnore
	 private Set<Student> studenci;

}
