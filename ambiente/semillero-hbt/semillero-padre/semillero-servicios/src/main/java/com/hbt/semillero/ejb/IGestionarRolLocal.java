package com.hbt.semillero.ejb;

import java.util.List;

import javax.ejb.Local;

import com.hbt.semillero.dto.ComicDTO;
import com.hbt.semillero.dto.PersonajeDTO;
import com.hbt.semillero.dto.RolDTO;

/**
 * Expone los métodos del EJB GestionarComic Las interfaces determinan una
 * especie de contrato donde se define las firmas de los metodos, define que se
 * necesita implementar pero no el como eso lo realiza la clase que la
 * implementa Palabras claves interface e implements
 * 
 * @author Luis David Mercado Ortega
 *
 */
@Local
public interface IGestionarRolLocal {

	/**
	 * 
	 * Metodo encargado de crear un ROL y persistirlo
	 * 
	 * @author Luis David Mercado Ortega
	 * 
	 * @param ROLNuevo informacion nueva a crear
	 */
	public void crearRol(RolDTO rolNuevo);

	/**
	 * 
	 * Metodo encargado de consultar un ROL modificarlo y guardarlo
	 * 
	 * @author Luis David Mercado Ortega
	 * 
	 * @param ROL informacion nueva a modificar
	 */
	public void modificarRol(Long id, String nombre, RolDTO rolNuevo);

	/**
	 * 
	 * Metodo encargado de eliminar un ROL modificarlo y guardarlo
	 * 
	 * @author Luis David Mercado Ortega
	 * 
	 * @param eliminarROl informacion a eliminar
	 */
	public void eliminarRol(Long idRol);

	/**
	 * 
	 * Metodo encargado de retornar la informacion de un rol
	 * 
	 * @param idRol identificador del Rol a ser consultado
	 * @return Rol Resultado de la consulta
	 * @throws Exception si no se recibe idPersonaje
	 */
	public List<RolDTO> consultarRol(long idRol);

	/**
	 * 
	 * Metodo encargado de retornar una lista de Roles
	 * 
	 * @return
	 */
	public List<RolDTO> consultarRol();
}
