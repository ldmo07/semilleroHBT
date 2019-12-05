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
import com.hbt.semillero.entidad.Comic;
import com.hbt.semillero.entidad.Personaje;

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
	public PersonajeDTO consultarPersonaje(String idPersonaje) {
		logger.debug("se Inicio el metodo  consultarPersonaje");

		Personaje personaje = null;
		personaje = new Personaje();
		personaje = em.find(Personaje.class, Long.parseLong(idPersonaje));
		PersonajeDTO personajeDTO = convertirPersonajeToPersonajeDTO(personaje);
		logger.debug("se Finalizo el metodo consultarPersonaje ");
		return personajeDTO;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<PersonajeDTO> consultarPersonaje() {
		logger.debug("se Inicio el metodo consultarPersonaje ");
		List<PersonajeDTO> resultadosPersonajeDTO = new ArrayList<PersonajeDTO>();
		List<Personaje> resultados = em.createQuery("select c from Comic c").getResultList();
		for (Personaje personaje:resultados) {
			resultadosPersonajeDTO.add(convertirPersonajeToPersonajeDTO(personaje));
		}
		logger.debug("se Finalizo el metodo consultarPersonaje ");
		return resultadosPersonajeDTO;
	}

	private Personaje convertirPersonajeDTOToPersonaje(PersonajeDTO personajeDTO) {
		Personaje personaje = new Personaje();
		if (personajeDTO.getId() != null) {
			personaje.setId(Long.parseLong(personajeDTO.getId()));
		}
		personaje.setNombre(personajeDTO.getNombre());
		personaje.setEditorial(personajeDTO.getEditorial());
		personaje.setTematicaEnum(personajeDTO.getTematicaEnum());
		personaje.setColeccion(personajeDTO.getColeccion());
		personaje.setNumeroPaginas(personajeDTO.getNumeroPaginas());
		personaje.setPrecio(personajeDTO.getPrecio());
		personaje.setAutores(personajeDTO.getAutores());
		personaje.setColor(personajeDTO.getColor());
		personaje.setFechaVenta(personajeDTO.getFechaVenta());
		personaje.setEstadoEnum(personajeDTO.getEstadoEnum());
		personaje.setCantidad(personajeDTO.getCantidad());
		return personaje;
	}

	private PersonajeDTO convertirPersonajeToPersonajeDTO(Personaje personaje) {
		PersonajeDTO personajeDTO = new PersonajeDTO();
		if (personaje.getId() != null) {
			personajeDTO.setId(personaje.getId().toString());
		}
		personajeDTO.setNombre(personaje.getNombre());
		personajeDTO.setEditorial(personaje.getEditorial());
		personajeDTO.setTematicaEnum(personaje.getTematicaEnum());
		personajeDTO.setColeccion(personaje.getColeccion());
		personajeDTO.setNumeroPaginas(personaje.getNumeroPaginas());
		personajeDTO.setPrecio(personaje.getPrecio());
		personajeDTO.setAutores(personaje.getAutores());
		personajeDTO.setColor(personaje.getColor());
		personajeDTO.setFechaVenta(personaje.getFechaVenta());
		personajeDTO.setEstadoEnum(personaje.getEstadoEnum());
		personajeDTO.setCantidad(personaje.getCantidad());
		return personajeDTO;
	}

}
