/**
 * GestionarComicBean.java
 */
package com.hbt.semillero.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import com.hbt.semillero.dto.ComicDTO;
import com.hbt.semillero.dto.PersonajeDTO;
import com.hbt.semillero.dto.RolDTO;
import com.hbt.semillero.entidad.Comic;
import com.hbt.semillero.entidad.Personaje;
import com.hbt.semillero.entidad.Rol;

/**
 * <b>Descripción:<b> Clase que determina el bean para realizar las gestion de
 * los comics
 * 
 * @author Luis David Mercado Ortega
 * @version
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class GestionarPersonajeBean implements IGestionarPersonajeLocal {

	/*
	 * @linea agregada desde la pagina url suministrada con el instructor
	 */

	final static Logger logger = Logger.getLogger(GestionarPersonajeBean.class);

	/**
	 * Atributo em que se usa para interacturar con el contexto de persistencia.
	 */
	@PersistenceContext
	private EntityManager em;

	@Override
	public void crearPersonaje(PersonajeDTO personajeNuevo) {
		logger.debug("se Inicio el metodo  crearPersonaje ");
		// Entidad nueva
		Personaje personaje = convertirPersonajeDTOToPersonaje(personajeNuevo);
		// Se almacena la informacion y se maneja la enidad Personaje
		em.persist(personaje);
		logger.debug("se Finalizo el metodo  crearPersonaje");

	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void modificarPersonaje(Long id, String nombre, PersonajeDTO personajeNuevo) {
		logger.debug("se Inicio el metodo  modificarPersonaje");
		Personaje personajeModificar;
		if (personajeNuevo == null) {
			// Entidad a modificar
			personajeModificar = em.find(Personaje.class, id);
		} else {
			personajeModificar = convertirPersonajeDTOToPersonaje(personajeNuevo);
		}
		personajeModificar.setNombre(nombre);
		em.merge(personajeModificar);
		logger.debug("se Finalizo el metodo modificarPersonaje");

	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void eliminarPersonaje(Long idPersonaje) {
		logger.debug("se Inicio el metodo  eliminarPersonaje");
		Personaje personajeEliminar = em.find(Personaje.class, idPersonaje);
		if (personajeEliminar != null) {
			em.remove(personajeEliminar);
		}
		logger.debug("se Finalizo el metodo eliminarPersonaje");

	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<PersonajeDTO> consultarPersonaje(long idPersonaje) {
		logger.debug("se Inicio el metodo  consultarPersonaje");
		List<PersonajeDTO> resultadosPersonajeDTO = new ArrayList<PersonajeDTO>();
		List<Personaje> resultados = em.createQuery("select p from Personaje p where PERS_ID =:idPersonaje")
				.setParameter("idPersonaje", idPersonaje).getResultList();
		for (Personaje personaje : resultados) {
			resultadosPersonajeDTO.add(convertirPersonajeToPersonajeDTO(personaje));
		}
		logger.debug("se Finalizo el metodo consultarPersonaje ");
		return resultadosPersonajeDTO;

	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<PersonajeDTO> consultarPersonaje() {
		logger.debug("se Inicio el metodo consultarPersonaje ");
		List<PersonajeDTO> resultadosPersonajeDTO = new ArrayList<PersonajeDTO>();
		List<Personaje> resultados = em.createQuery("select p from Personaje p").getResultList();
		for (Personaje personaje : resultados) {
			resultadosPersonajeDTO.add(convertirPersonajeToPersonajeDTO(personaje));
		}
		logger.debug("se Finalizo el metodo consultarPersonaje ");
		return resultadosPersonajeDTO;
	}

	private Personaje convertirPersonajeDTOToPersonaje(PersonajeDTO personajeDTO) {
		Personaje personaje = new Personaje();

		personaje.setId(personajeDTO.getId());
		personaje.setId(personajeDTO.getId());
		personaje.setNombre(personajeDTO.getNombre());
		personaje.setEstado(personajeDTO.getEstado());
		personaje.setSuperpoder(personajeDTO.getSuperpoder());
		personaje.setIdcomic(personajeDTO.getIdcomic());
		return personaje;
	}

	private PersonajeDTO convertirPersonajeToPersonajeDTO(Personaje personaje) {

		PersonajeDTO personajeDTO = new PersonajeDTO();

		personajeDTO.setId(personaje.getId());
		personajeDTO.setNombre(personaje.getNombre());
		personajeDTO.setEstado(personaje.getEstado());
		personajeDTO.setSuperpoder(personaje.getSuperpoder());
		personajeDTO.setIdcomic(personaje.getIdcomic());
		return personajeDTO;
	}

}
