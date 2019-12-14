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

import com.hbt.semillero.dto.PersonajeDTO;
import com.hbt.semillero.dto.PersonajeDTO;
import com.hbt.semillero.entidad.Comic;
import com.hbt.semillero.entidad.Personaje;
import com.hbt.semillero.entidad.Personaje;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class GestionarPersonajeBean implements IGestionarPersonajeLocal{
	final static Logger logger = Logger.getLogger(GestionarPersonajeBean.class);
	
	@PersistenceContext
	private EntityManager entityManager;
	
	/**
	 * 
	 * @see com.hbt.semillero.ejb.IGestionarPersonajeLocal#crearPersonaje(com.hbt.semillero.dto.PersonajeDTO)
	 */
	@Override
	public void crearPersonaje(PersonajeDTO personajeDTO){
		Personaje personaje = convertirDTOEntidad(personajeDTO);
		entityManager.persist(personaje);
		
	}

	/**
	 * 
	 * @see com.hbt.semillero.ejb.IGestionarPersonajeLocal#modificarPersonaje(com.hbt.semillero.dto.PersonajeDTO)
	 */
	@Override
	public void modificarPersonaje(Long id, String nombre,PersonajeDTO personajeDTO) {
		logger.debug("Aqui inicia el metodo ModificarPersonaje");

		logger.debug("Aqui finaliza el metodo ModificarPersonaje");
	}

	/**
	 * 
	 * @see com.hbt.semillero.ejb.IGestionarPersonajeLocal#eliminarPersonaje(com.hbt.semillero.dto.PersonajeDTO)
	 */
	@Override
	public void eliminarPersonaje(Long idComic) {
		logger.debug("Aqui inicia el metodo EliminarPersonaje");

		logger.debug("Aqui finaliza el metodo EliminarPersonaje");
	}

	/**
	 * 
	 * @see com.hbt.semillero.ejb.IGestionarPersonajeLocal#consultarPersonaje(com.hbt.semillero.dto.PersonajeDTO)
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public  List<PersonajeDTO> consultarPersonaje() {
		logger.debug("Aqui inicia el metodo ConsultarPersonaje");

		String query = "SELECT personaje FROM Personaje personaje";
		List<Personaje> resultados = entityManager.createQuery(query).getResultList();
		List<PersonajeDTO> resultadosPersonajeDTO = new ArrayList<>();
		for (Personaje personaje : resultados) {
			resultadosPersonajeDTO.add(convertirEntidadDTO(personaje));
		}
		logger.debug("Aqui finaliza el metodo ConsultarPersonaje");

		return resultadosPersonajeDTO;
	}

	/**
	 * 
	 * @see com.hbt.semillero.ejb.IGestionarPersonajeLocal#eliminarPersonajes(com.hbt.semillero.dto.PersonajeDTO)
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<PersonajeDTO>  consultarPersonajes(Long idComic) {
		logger.debug("Aqui inicia el metodo ConsultarPersonajes");

		String query = "SELECT personaje FROM Personaje personaje WHERE personaje.comic.id = :idComic ";
		List<Personaje> resultados = entityManager.createQuery(query).setParameter("idComic", idComic).getResultList();
		List<PersonajeDTO> resultadosPersonajeDTO = new ArrayList<PersonajeDTO>();
		for (Personaje personaje : resultados) {
			resultadosPersonajeDTO.add(convertirEntidadDTO(personaje));
		}
		logger.debug("Aqui finaliza el metodo ConsultarPersonajes");
		return resultadosPersonajeDTO;
	}
	
	/**
	 * Metodo encargado de transformar un personajeDTO a un personaje
	 * 
	 * @param personajeDTO
	 * @return
	 */
	public Personaje convertirDTOEntidad(PersonajeDTO personajeDTO) {
		logger.debug("Aqui inicia el metodo convertirDTOEntidad");
		Personaje personaje = new Personaje();
		personaje.setId(personajeDTO.getId());
		personaje.setNombre(personajeDTO.getNombre());
		personaje.setComic(new Comic());
		personaje.getComic().setId(personajeDTO.getIdComic());
		personaje.setEstado(personajeDTO.getEstado());
		personaje.setSuperPoder(personajeDTO.getSuperPoder());
		logger.debug("Aqui finaliza el metodo convertirDTOEntidad");
		return personaje;
	}
	
	
	/**
	 * Metodo encargado de transformar un personaje a un personajeDTO
	 * 
	 * @param personaje
	 * @return
	 */
	private PersonajeDTO convertirEntidadDTO(Personaje personaje) {
		logger.debug("Aqui inicia el metodo convertirEntidadDTO");
		PersonajeDTO personajeDTO = new PersonajeDTO();
		personajeDTO.setIdComic(personaje.getComic().getId());
		personajeDTO.setEstado(personaje.getEstado());
		personajeDTO.setNombre(personaje.getNombre());
		personajeDTO.setId(personaje.getId());
		personajeDTO.setSuperPoder(personaje.getSuperPoder());
		logger.debug("Aqui finaliza el metodo convertirEntidadDTO");
		return personajeDTO;
	}


}