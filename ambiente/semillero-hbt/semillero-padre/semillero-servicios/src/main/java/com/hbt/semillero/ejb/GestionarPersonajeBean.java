package com.hbt.semillero.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.management.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import com.hbt.semillero.dto.PersonajeDTO;
import com.hbt.semillero.dto.PersonajeDTO;
import com.hbt.semillero.entidad.Comic;
import com.hbt.semillero.entidad.Personaje;
import com.hbt.semillero.exceptions.ComicException;
import com.hbt.semillero.exceptions.PersonajeException;
import com.hbt.semillero.exceptions.RolException;
import com.hbt.semillero.entidad.Personaje;

/*
 * @autor Luis David Mercado Ortega
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class GestionarPersonajeBean implements IGestionarPersonajeLocal{
	
	//atributo que permite hacer logger con la libreria log4j
	final static Logger logger = Logger.getLogger(GestionarPersonajeBean.class);
	
	//atributo que permite ejecutar las consultas jpa
	@PersistenceContext
	private EntityManager entityManager;
	
	/**
	 * 
	 * @throws PersonajeException 
	 * @see com.hbt.semillero.ejb.IGestionarPersonajeLocal#crearPersonaje(com.hbt.semillero.dto.PersonajeDTO)
	 */
	@Override
	public PersonajeDTO crearPersonaje(PersonajeDTO personajeDTO) throws PersonajeException{
		try {
			Personaje personaje = convertirDTOEntidad(personajeDTO);
			entityManager.persist(personaje);
			return convertirEntidadDTO(personaje);
		} catch (Exception e) {
			logger.error("Error al crear Personaje"+e);
			throw new PersonajeException("CD-00f","error creando los personajes", e);
		}
	}

	/**
	 * 
	 * @throws PersonajeException 
	 * @see com.hbt.semillero.ejb.IGestionarPersonajeLocal#modificarPersonaje(com.hbt.semillero.dto.PersonajeDTO)
	 */
	@Override
	public void modificarPersonaje(Long id, String nombre,PersonajeDTO personajeDTO) throws PersonajeException {
		try {
			logger.error("inicio el metodo modificar Personaje");
			Personaje personajeModificar ;
			if(personajeDTO==null) {
				// Entidad a modificar
				personajeModificar = entityManager.find(Personaje.class, id);
			}else {
				personajeModificar = convertirDTOEntidad(personajeDTO);
			}
			personajeModificar.setNombre(nombre);
			entityManager.merge(personajeModificar);
			logger.error("finalizo el metodo modificar comic");
		} catch (Exception e) {
			logger.error("Error al editar comic"+e);
			throw new PersonajeException("CD-00f","error ejecutando eliminacion del comic", e);
		}
	}

	/**
	 * 
	 * @throws PersonajeException 
	 * @see com.hbt.semillero.ejb.IGestionarPersonajeLocal#eliminarPersonaje(com.hbt.semillero.dto.PersonajeDTO)
	 */
	@Override
	public void eliminarPersonaje(Long id) throws PersonajeException {
		
		try {
			logger.debug("Aqui inicia el metodo EliminarPerosnaje");
					Query query = (Query) entityManager.createQuery("Delete From Personaje p where p.id=:id").setParameter("id", id);
					((javax.persistence.Query) query).executeUpdate();
					logger.debug("Aqui finaliza el metodo EliminarRol");
					entityManager.flush();
					entityManager.clear();
				} catch (Exception e) {
					logger.error("Error al eliminar Personaje"+e);
					throw new PersonajeException("CD-00f","error ejecutando eliminacion del Rol", e);
				}
	}

	/**
	 * 
	 * @throws PersonajeException 
	 * @see com.hbt.semillero.ejb.IGestionarPersonajeLocal#consultarPersonaje(com.hbt.semillero.dto.PersonajeDTO)
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public  List<PersonajeDTO> consultarPersonaje() throws PersonajeException {
		try {
			logger.debug("Aqui inicia el metodo ConsultarPersonaje");

			String query = "SELECT personaje FROM Personaje personaje";
			List<Personaje> resultados = entityManager.createQuery(query).getResultList();
			List<PersonajeDTO> resultadosPersonajeDTO = new ArrayList<>();
			for (Personaje personaje : resultados) {
				resultadosPersonajeDTO.add(convertirEntidadDTO(personaje));
			}
			logger.debug("Aqui finaliza el metodo ConsultarPersonaje");

			return resultadosPersonajeDTO;
		} catch (Exception e) {
			logger.error("Error al listar personaje"+e);
			throw new PersonajeException("CD-00f","error Listando los personajes", e);
		}
		
	}

	/**
	 * 
	 * @throws PersonajeException 
	 * @see com.hbt.semillero.ejb.IGestionarPersonajeLocal#eliminarPersonajes(com.hbt.semillero.dto.PersonajeDTO)
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<PersonajeDTO>  consultarPersonajes(Long idComic) throws PersonajeException {
		try {
			logger.debug("Aqui inicia el metodo ConsultarPersonajes");

			String query = "SELECT personaje FROM Personaje personaje WHERE personaje.comic.id = :idComic ";
			List<Personaje> resultados = entityManager.createQuery(query).setParameter("idComic", idComic).getResultList();
			List<PersonajeDTO> resultadosPersonajeDTO = new ArrayList<PersonajeDTO>();
			for (Personaje personaje : resultados) {
				resultadosPersonajeDTO.add(convertirEntidadDTO(personaje));
			}
			logger.debug("Aqui finaliza el metodo ConsultarPersonajes");
			return resultadosPersonajeDTO;
		} catch (Exception e) {
			logger.error("Error al consultar personaje por  id"+e);
			throw new PersonajeException("CD-00f","error Listando los Personajes", e);
		}
		
	}
	
	/**
	 * Metodo encargado de transformar un personajeDTO a un personaje
	 * 
	 * @param personajeDTO
	 * @return
	 * @throws PersonajeException 
	 */
	public Personaje convertirDTOEntidad(PersonajeDTO personajeDTO) throws PersonajeException {
		try {
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
		} catch (Exception e) {
			logger.error("Error al convertir DTO a entiad del Personaje"+e);
			throw new PersonajeException("CD-00f","error convirtiendo DTO a entiad del Personaje", e);
		}
		
	}
	
	
	/**
	 * Metodo encargado de transformar un personaje a un personajeDTO
	 * 
	 * @param personaje
	 * @return
	 * @throws PersonajeException 
	 */
	private PersonajeDTO convertirEntidadDTO(Personaje personaje) throws PersonajeException {
		try {
			logger.debug("Aqui inicia el metodo convertirEntidadDTO");
			PersonajeDTO personajeDTO = new PersonajeDTO();
			personajeDTO.setIdComic(personaje.getComic().getId());
			personajeDTO.setEstado(personaje.getEstado());
			personajeDTO.setNombre(personaje.getNombre());
			personajeDTO.setId(personaje.getId());
			personajeDTO.setSuperPoder(personaje.getSuperPoder());
			logger.debug("Aqui finaliza el metodo convertirEntidadDTO");
			return personajeDTO;
		} catch (Exception e) {
			logger.error("Error al convertir entidad a DTO comic"+e);
			throw new PersonajeException("CD-00f","error convirtiendo entiad a DTo del Personaje", e);
		}
		
	}

	@Override
	public PersonajeDTO modificar(PersonajeDTO personajeDTO)throws PersonajeException {
		if(personajeDTO.getId()==null) {
			throw new PersonajeException("CD-00f","error identificador requerido");
		}
		
		Query query=(Query) entityManager.createQuery("UPDATE Personaje personaje set personaje.estado=: estado , "
				+ "personaje.comic.id =: idComic where personaje.id=:idPersonaje");
		((javax.persistence.Query) query).setParameter("estado",personajeDTO.getEstado());
		((javax.persistence.Query) query).setParameter("idComic",personajeDTO.getIdComic());
		((javax.persistence.Query) query).setParameter("idPersonaje",personajeDTO.getId());
		((javax.persistence.Query) query).executeUpdate();
		return convertirEntidadDTO(entityManager.find(Personaje.class, personajeDTO.getId()));
	}


}