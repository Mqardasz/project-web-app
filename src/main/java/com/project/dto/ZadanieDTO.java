package com.project.dto;

import java.time.LocalDateTime;

public class ZadanieDTO {
    private Integer projektId; // Tylko ID projektu
    private String nazwa;
    private Integer kolejnosc;
    private String opis;
    private LocalDateTime dataczasDodania;
	public Integer getProjektId() {
		return projektId;
	}
	public void setProjektId(Integer projektId) {
		this.projektId = projektId;
	}
	public String getNazwa() {
		return nazwa;
	}
	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}
	public Integer getKolejnosc() {
		return kolejnosc;
	}
	public void setKolejnosc(Integer kolejnosc) {
		this.kolejnosc = kolejnosc;
	}
	public String getOpis() {
		return opis;
	}
	public void setOpis(String opis) {
		this.opis = opis;
	}
	public LocalDateTime getDataczasDodania() {
		return dataczasDodania;
	}
	public void setDataczasDodania(LocalDateTime dataczasDodania) {
		this.dataczasDodania = dataczasDodania;
	}
    
    

}
