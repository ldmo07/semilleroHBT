package com.hbt.semillero.ejb;

import java.util.List;

import javax.ejb.Local;

import com.hbt.semillero.dto.ComicDTO;
import com.hbt.semillero.dto.PersonajeDTO;
import com.hbt.semillero.exceptions.PersonajeException;


/**
 * Expone los métodos del EJB GestionarPersonaje Las interfaces determinan una
 * especie de contrato donde se define las firmas de los metodos, define que se
 * necesita implementar pero no el como eso lo realiza la clase que la
 * implementa Palabras claves interface e implements
 * 
 * @author drageloz
 *
 */
@Local
public interface IGestionarPersonajeLocal {
	
	/**
	 * 
	 * Metodo encargado de crear un personaje y persistirlo
	 * 
	 * @author drageloz
	 * 
	 * @param personajeDTO informacion nueva a crear
	 */
	public PersonajeDTO crearPersonaje(PersonajeDTO personajeDTO)throws PersonajeException;

	
	/**
	 * 
	 * Metodo encargado de consultar un personaje, modificarlo y guardarlo
	 * 
	 * @author drageloz
	 * 
	 * @param id, nombre, personajeDTO informacion nueva a modificar
	 */
	public void modificarPersonaje(Long id, String nombre,PersonajeDTO personajeDTO)throws PersonajeException;

	
	/**
	 * 
	 * Metodo encargado de eliminar un personaje, modificarlo y guardarlo
	 * 
	 * @author drageloz
	 * 
	 * @param idPersonaje informacion del id del personaje a eliminar
	 */
	public void eliminarPersonaje(Long idPersonaje)throws PersonajeException;

	/**
	 * 
	 * Metodo encargado de retornar la informacion de un personaje
	 * 
	 * @return List<PersonajeDTO> Resultado de la consulta
	 */
	public  List<PersonajeDTO> consultarPersonaje()throws PersonajeException;
	
	/**
	 * 
	 * Metodo encargado de retornar una lista de personajees 
	 * @param idComic El id del comic a consultar
	 * @return List<PersonajeDTO> Resultado de la consulta
	 */
	public List<PersonajeDTO>  consultarPersonajes(Long idComic)throws PersonajeException;
	
	public PersonajeDTO modificar(PersonajeDTO personajeDTO)throws PersonajeException;
	
}