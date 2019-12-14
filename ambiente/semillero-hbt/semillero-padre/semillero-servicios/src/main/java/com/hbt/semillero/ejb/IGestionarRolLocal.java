package com.hbt.semillero.ejb;

import java.util.List;

import javax.ejb.Local;

import com.hbt.semillero.dto.PersonajeDTO;
import com.hbt.semillero.dto.RolDTO;


/**
 * Expone los métodos del EJB GestionarRol Las interfaces determinan una
 * especie de contrato donde se define las firmas de los metodos, define que se
 * necesita implementar pero no el como eso lo realiza la clase que la
 * implementa Palabras claves interface e implements
 * 
 * @author drageloz
 *
 */
@Local
public interface IGestionarRolLocal {
	
	/**
	 * 
	 * Metodo encargado de crear un rol y persistirlo
	 * 
	 * @author drageloz
	 * 
	 * @param rolDTO informacion nueva a crear
	 */
	public void crearRol(RolDTO rolDTO);

	
	/**
	 * 
	 * Metodo encargado de consultar un rol, modificarlo y guardarlo
	 * 
	 * @author drageloz
	 * 
	 * @param id, nombre, rolDTO informacion nueva a modificar
	 */
	public void modificarRol(Long id, String nombre,RolDTO rolDTO);

	
	/**
	 * 
	 * Metodo encargado de eliminar un rol, modificarlo y guardarlo
	 * 
	 * @author drageloz
	 * 
	 * @param idRol informacion del id del rol a eliminar
	 */
	public void eliminarRol(Long idRol);

	/**
	 * 
	 * Metodo encargado de retornar la informacion de un rol
	 * 
	 * @return List<RolDTO> Resultado de la consulta
	 */
	public  List<RolDTO> consultarRol();
	
	/**
	 * 
	 * Metodo encargado de retornar una lista de roles 
	 * @param idPersonaje El id del personaje a consultar
	 * @return List<RolDTO> Resultado de la consulta
	 */
	public List<RolDTO>  consultarRoles(Long idPersonaje);
	
}
