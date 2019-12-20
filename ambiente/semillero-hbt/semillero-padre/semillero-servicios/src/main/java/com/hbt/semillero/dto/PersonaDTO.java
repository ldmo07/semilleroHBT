package com.hbt.semillero.dto;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * <b>Descripción:<b> Clase que determina el dto a usar para modificar,
 * consultar y posteriormente eliminar una Persona
 * 
 * @author Luis David Mercado Ortega
 */

public class PersonaDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String nombre;
	private String ndocumento;
	private LocalDate FechaNaci;
	private String tipoDoc;
	
	
	
	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public String getNdocumento() {
		return ndocumento;
	}



	public void setNdocumento(String ndocumento) {
		this.ndocumento = ndocumento;
	}



	public LocalDate getFechaNaci() {
		return FechaNaci;
	}



	public void setFechaNaci(LocalDate fechaNaci) {
		FechaNaci = fechaNaci;
	}



	public String getTipoDoc() {
		return tipoDoc;
	}



	public void setTipoDoc(String tipoDoc) {
		this.tipoDoc = tipoDoc;
	}



	@Override
	public String toString() {
		return JsonUtils.toStringJson(this);
	}


	public static ComicDTO valueOf(String arg) {
		return JsonUtils.valueOf(arg, ComicDTO.class);
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((FechaNaci == null) ? 0 : FechaNaci.hashCode());
		result = prime * result + ((ndocumento == null) ? 0 : ndocumento.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((tipoDoc == null) ? 0 : tipoDoc.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PersonaDTO other = (PersonaDTO) obj;
		if (FechaNaci == null) {
			if (other.FechaNaci != null)
				return false;
		} else if (!FechaNaci.equals(other.FechaNaci))
			return false;
		if (ndocumento == null) {
			if (other.ndocumento != null)
				return false;
		} else if (!ndocumento.equals(other.ndocumento))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (tipoDoc == null) {
			if (other.tipoDoc != null)
				return false;
		} else if (!tipoDoc.equals(other.tipoDoc))
			return false;
		return true;
	}
	
	
}
