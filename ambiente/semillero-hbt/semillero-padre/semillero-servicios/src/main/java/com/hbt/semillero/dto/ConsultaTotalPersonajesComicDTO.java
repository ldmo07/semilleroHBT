package com.hbt.semillero.dto;

public class ConsultaTotalPersonajesComicDTO {
	private long total;
	private String comic;
	

	public ConsultaTotalPersonajesComicDTO() {
		
	}
	
	public ConsultaTotalPersonajesComicDTO(long total, String comic) {
		super();
		this.total = total;
		this.comic = comic;
	}
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public String getComic() {
		return comic;
	}
	public void setComic(String comic) {
		this.comic = comic;
	}
	
}
