package com.hbt.semillero.entidad;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/*INDICAMOS LA ENTIDAD EN LA ANOTACION Y LA TABLA DE LA BASE DE DATOS
 * A LA CUAL SE REALIZARA EL CRUD DEL ROL
 */
@Entity
@Table(name = "ROL")
public class Rol implements Serializable {
	
	/*@descripcion la anotacion @columname hace referencia a las columnas de la tabla 
	 * Rol en la base de datos para pasar los datos correspondientes*/
	
	public Rol() {}
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(allocationSize = 1, name = "ROL_ID_GENERATOR", sequenceName = "SEC_ROL")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ROL_ID_GENERATOR")
	@Column(name = "ROL_ID")
	private long id;
	
	@Column(name = "ROL_ID_PERSONAJE")
	private long idpersonaje;
	
	@Column(name = "ROL_NOMBRE")
	private String nombre;
	
	@Column(name = "ROL_ESTADO")
	private String estado;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getIdpersonaje() {
		return idpersonaje;
	}
	public void setIdpersonaje(long idpersonaje) {
		this.idpersonaje = idpersonaje;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	/*Metodo encargado de generar un array de tipo string para ver el valor de las variables*/
	@Override
	public String toString() {
		return "Rol [id=" + id + ", idpersonaje=" + idpersonaje + ", nombre=" + nombre + ", estado=" + estado + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + (int) (idpersonaje ^ (idpersonaje >>> 32));
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}
	
	/**
	 * @see java.lang.Object#equals(java.lang.Object) Metodo que permite comparar
	 *      objetos
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rol other = (Rol) obj;
		if (estado == null) {
			if (other.estado != null)
				return false;
		} else if (!estado.equals(other.estado))
			return false;
		if (id != other.id)
			return false;
		if (idpersonaje != other.idpersonaje)
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}
	
	
}
