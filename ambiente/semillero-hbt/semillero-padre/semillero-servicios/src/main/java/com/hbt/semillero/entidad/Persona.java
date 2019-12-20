package com.hbt.semillero.entidad;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/*Descripcion clase Persona para hacer el mapeo a la Tabla Persona en la BD*/

@Entity
@Table(name = "PERSONA")
public class Persona implements Serializable {
	
	
	
	public Persona() {
		super();
	}

	private static final long serialVersionUID = 1L;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Id
	@Column(name = "ndocumento")
	private String ndocumento;
	
	@Column(name = "FechaNaci")
	private LocalDate FechaNaci;
	
	@Column(name = "tipoDoc")
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
		return "Persona [nombre=" + nombre + ", ndocumento=" + ndocumento + ", FechaNaci=" + FechaNaci + ", tipoDoc="
				+ tipoDoc + "]";
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
		Persona other = (Persona) obj;
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
