package com.hbt.semillero.dto;

import java.io.Serializable;

import org.apache.log4j.Logger;

import com.hbt.semillero.ejb.GestionarComicBean;
import com.hbt.semillero.entidad.EstadoEnum;



/**
 * <b>Descripción:<b> Clase que determina el dto a usar para modificar,
 * consultar y posteriormente eliminar un Rol
 * 
 * @author Luis David Mercado Ortega
 */
public class RolDTO implements Serializable{
	
	final static Logger logger = Logger.getLogger(GestionarComicBean.class);
	private static final long serialVersionUID = 1L;
	private Long id;
	private String nombre;
	private Long idPersonaje;
	private EstadoEnum estado;
	
	
	/**
	 * Metodo encargado de retornar el valor del atributo id
	 * 
	 * @return El id asociado a la clase
	 */
	public Long getId() {
		return id;
	}
	/**
	 * Metodo encargado de modificar el valor del atributo id
	 * 
	 * @param id El nuevo id a modificar.
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * Metodo encargado de retornar el valor del atributo nombre
	 * 
	 * @return El nombre asociado a la clase
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * Metodo encargado de modificar el valor del atributo nombre
	 * 
	 * @param nombre El nuevo nombre a modificar.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * Metodo encargado de retornar el valor del atributo idPersonaje
	 * 
	 * @return El idPersonaje asociado a la clase
	 */
	public Long getIdPersonaje() {
		return idPersonaje;
	}
	
	/**
	 * Metodo encargado de modificar el valor del atributo idPersonaje
	 * 
	 * @param idPersonaje El nuevo idPersonaje a modificar.
	 */
	public void setIdPersonaje(Long idPersonaje) {
		this.idPersonaje = idPersonaje;
	}
	
	/**
	 * Metodo encargado de retornar el valor del atributo estado
	 * 
	 * @return El estado asociado a la clase
	 */
	public EstadoEnum getEstado() {
		return estado;
	}
	/**
	 * Metodo encargado de modificar el valor del atributo estado
	 * 
	 * @param estado El nuevo estado a modificar.
	 */
	public void setEstado(EstadoEnum estado) {
		this.estado = estado;
	}

	/**
	 * Método encargado de convertir los datos recibidos en JSON al tipo ComicDTO.
	 * <b>Caso de Uso:</b>
	 * 
	 * @param arg Cadena que representa el objeto complejo JSON.
	 * @return Instancia con los datos recibidos.
	 */
	public static RolDTO valueOf(String arg) {
		return JsonUtils.valueOf(arg, RolDTO.class);
	}
	
	
	/**
	 * Método encargado de convertir los datos recibidos en ComicDTO al JSON
	 * esperado
	 * 
	 * @param dto DTO
	 * 
	 * @return Json
	 */
	@Override
	public String toString() {
		return JsonUtils.toStringJson(this);
	}
	
}