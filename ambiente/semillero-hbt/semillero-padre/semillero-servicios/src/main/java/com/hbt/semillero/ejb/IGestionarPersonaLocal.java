package com.hbt.semillero.ejb;

import java.util.List;

import javax.ejb.Local;

import com.hbt.semillero.dto.ComicDTO;
import com.hbt.semillero.dto.PersonaDTO;
import com.hbt.semillero.exceptions.ComicException;

@Local
public interface IGestionarPersonaLocal {
	
	/**
	 * 
	 * Metodo encargado de crear un comic y persistirlo
	 * 
	 * @author Luis Mercado
	 * 
	 * @param comicNuevo informacion nueva a crear
	 * @throws ComicException 
	 */
	public void crearPersona(PersonaDTO personaNuevo) throws ComicException;

	/**
	 * 
	 * Metodo encargado de consultar un comic modificarlo y guardarlo
	 * 
	 * @author ccastano
	 * 
	 * @param comicModificar informacion nueva a modificar
	 * @throws ComicException 
	 */
	public void modificarPersona(Long id, String nombre, PersonaDTO personaNuevo) throws ComicException;

	
	public void eliminarPersona(String Ndocumento)throws ComicException;

	/**
	 * 
	 * Metodo encargado de retornar la informacion de una persona
	 * 
	 * @param idComic identificador del comic a ser consultado
	 * @return comic Resultado de la consulta
	 * @throws ComicException 
	 * @throws Exception si no se recibe idComic
	 */
	public PersonaDTO consultarPersona(String Ndocumento) throws ComicException;

	/**
	 * 
	 * Metodo encargado de retornar una lista de Personas
	 * 
	 * @return
	 * @throws ComicException 
	 */
	public List<PersonaDTO> consultarPersonas() throws ComicException;
	
	
	public PersonaDTO modificar(PersonaDTO personaDTO) throws ComicException;
	
}
