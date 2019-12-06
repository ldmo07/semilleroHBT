package com.hbt.semillero.ejb;

import java.util.List;

import javax.ejb.Local;

import com.hbt.semillero.dto.ComicDTO;
import com.hbt.semillero.dto.PersonajeDTO;

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
public interface IGestionarPersonajeLocal {

	/**
	 * 
	 * Metodo encargado de crear un Personaje y persistirlo
	 * 
	 * @author Luis David Mercado Ortega
	 * 
	 * @param personajeNuevo informacion nueva a crear
	 */
	public void crearPersonaje(PersonajeDTO personajeNuevo);

	/**
	 * 
	 * Metodo encargado de consultar un Personaje modificarlo y guardarlo
	 * 
	 * @author Luis David Mercado Ortega
	 * 
	 * @param Personaje informacion nueva a modificar
	 */
	public void modificarPersonaje(Long id, String nombre, PersonajeDTO comicNuevo);

	/**
	 * 
	 * Metodo encargado de eliminar un comic modificarlo y guardarlo
	 * 
	 * @author Luis David Mercado Ortega
	 * 
	 * @param eliminarPersonaje informacion a eliminar
	 */
	public void eliminarPersonaje(Long idPersonaje);

	/**
	 * 
	 * Metodo encargado de retornar la informacion de un Personaje
	 * 
	 * @param idPersonaje identificador del Personaje a ser consultado
	 * @return Personaje Resultado de la consulta
	 * @throws Exception si no se recibe idPersonaje
	 */
	public List<PersonajeDTO>consultarPersonaje(long idPersonaje);

	/**
	 * 
	 * Metodo encargado de retornar una lista de Personajes
	 * 
	 * @return
	 */
	public List<PersonajeDTO> consultarPersonaje();
}
