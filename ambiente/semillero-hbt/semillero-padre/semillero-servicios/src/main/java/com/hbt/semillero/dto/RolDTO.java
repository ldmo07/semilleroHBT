package com.hbt.semillero.dto;

import java.io.Serializable;

public class RolDTO implements Serializable{
	/*
	 * @descripcion Esta clase se hace para hacer el mapeo con los campos de la 
	 * tabla Rol en la base de datos 
	 */
	
	private static final long serialVersionUID = 1L;
	
	private long id;
	private long idpersonaje;
	private String nombre;
	private String estado;
	
	public RolDTO() {}

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
	
	
	/**
	 * Método encargado de convertir los datos recibidos en JSON al tipo RolDTO.
	*/
	public static RolDTO valueOf(String arg) {
		return JsonUtils.valueOf(arg, RolDTO.class);
	}

	/**
	 * Método encargado de convertir los datos recibidos en RolDTO al JSON
	 * esperado
	 */
	
	@Override
	public String toString() {
		return JsonUtils.toStringJson(this);
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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RolDTO other = (RolDTO) obj;
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
