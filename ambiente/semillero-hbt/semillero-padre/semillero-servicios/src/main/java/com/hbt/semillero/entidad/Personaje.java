
package com.hbt.semillero.entidad;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 
 * @author Luis Mercado@@@@@@@
 * @version
 */
@Entity
@Table(name = "PERSONAJE")
public class Personaje implements Serializable {


	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(allocationSize = 1, name = "PERSONAJE_ID_GENERATOR", sequenceName = "SEC_PERSONAJE")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PERSONAJE_ID_GENERATOR")
	@Column(name = "PERS_ID")
	private long id;
	
	@Column(name = "PERS_ID_COMIC")
	private long idcomic;
	
	@Column(name = "PERS_NOMBRE")
	private String nombre;
	
	@Column(name = "PERS_ESTADO")
	@Enumerated(value = EnumType.STRING)
	private String estado;
	
	@Column(name = "PERS_SUPERPODER")
	private String superpoder;
	/**
	 * Constructor de la clase.
	 */
	public Personaje() {

	}
	
	
	public long getIdcomic() {
		return idcomic;
	}



	public void setIdcomic(long idcomic) {
		this.idcomic = idcomic;
	}


	
	public String getEstado() {
		return estado;
	}



	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getSuperpoder() {
		return superpoder;
	}



	public void setSuperpoder(String superpoder) {
		this.superpoder = superpoder;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	
	public void setId(long id) {
		this.id = id;
	}


	
	
	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	/**
	 * Metodo encargado de retornar el valor del atributo id
	 * 
	 * @return El id asociado a la clase
	 */
	
	public Long getId() {
		return id;
	}

	
	@Override
	public String toString() {
		return "Personaje [id=" + id + ", idcomic=" + idcomic + ", nombre=" + nombre + ", estado=" + estado
				+ ", superpoder=" + superpoder + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + (int) (idcomic ^ (idcomic >>> 32));
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((superpoder == null) ? 0 : superpoder.hashCode());
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
		Personaje other = (Personaje) obj;
		if (estado == null) {
			if (other.estado != null)
				return false;
		} else if (!estado.equals(other.estado))
			return false;
		if (id != other.id)
			return false;
		if (idcomic != other.idcomic)
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (superpoder == null) {
			if (other.superpoder != null)
				return false;
		} else if (!superpoder.equals(other.superpoder))
			return false;
		return true;
	}	
	
	
	
	
}
