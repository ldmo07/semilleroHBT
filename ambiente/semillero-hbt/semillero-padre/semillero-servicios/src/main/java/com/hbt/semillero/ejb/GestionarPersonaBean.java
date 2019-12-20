package com.hbt.semillero.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import com.hbt.semillero.dto.ComicDTO;
import com.hbt.semillero.dto.PersonaDTO;
import com.hbt.semillero.dto.PersonajeDTO;
import com.hbt.semillero.entidad.Comic;
import com.hbt.semillero.entidad.Persona;
import com.hbt.semillero.entidad.Personaje;
import com.hbt.semillero.exceptions.ComicException;
import com.hbt.semillero.exceptions.PersonajeException;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class GestionarPersonaBean implements IGestionarPersonaLocal{
	
	/**
	 * Atributo em que se usa para interacturar con los Log de la libreria log4j.
	 */
	final static Logger logger = Logger.getLogger(GestionarPersonaBean.class);
	
	/**
	 * Atributo em que se usa para interacturar con el contexto de persistencia.
	 */
	@PersistenceContext
	private EntityManager em;

	@Override
	public void crearPersona(PersonaDTO personaNuevo) throws ComicException {
		try {
			logger.debug("inicio metodo  Crear Persona");
			// Entidad nueva
			Persona persona = convertirPersonaDTOToPersona(personaNuevo);
			// Se almacena la informacion y se maneja la enidad persona
			em.persist(persona);
			logger.debug("finalizo metodo  Crear persona");
		} catch (Exception e) {
			logger.error("Error al Crear persona"+e);
			throw new ComicException("CD-00f","error ejecutando creacion de la Persona", e);
		}
		
	}

	@Override
	public void modificarPersona(Long id, String nombre, PersonaDTO personaNuevo) throws ComicException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminarPersona(String Ndocumento) throws ComicException {
	try {
			
			Persona personaEliminar = em.find(Persona.class, Ndocumento);
			if (personaEliminar != null) {
				em.remove(personaEliminar);
			
		}
			}catch (Exception e) {
			logger.error("Error al eliminar Persona"+e);
			throw new ComicException("CD-00f","error ejecutando eliminacion de la persona ", e);
		}
		
	}

	@Override
	public PersonaDTO consultarPersona(String Ndocumento) throws ComicException {
		try {
			Persona persona = null;
			persona = new Persona();
			persona = em.find(Persona.class, Long.parseLong(Ndocumento));
			PersonaDTO personaDTO = convertirPersonaToPersonaDTO(persona);
			return personaDTO;
		} catch (Exception e) {
			logger.error("Error al consultar comic por id"+e);
			throw new ComicException("CD-00f","error ejecutando eliminacion del comic", e);
		}
	}

	@Override
	public List<PersonaDTO> consultarPersonas() throws ComicException {
		try {
			logger.debug("Aqui inicia el metodo ConsultarPersona");

			String query = "SELECT persona FROM Persona persona";
			List<Persona> resultados = em.createQuery(query).getResultList();
			List<PersonaDTO> resultadosPersonaDTO = new ArrayList<>();
			for (Persona persona : resultados) {
				resultadosPersonaDTO.add(convertirPersonaToPersonaDTO(persona));
			}
			logger.debug("Aqui finaliza el metodo ConsultarPersona");

			return resultadosPersonaDTO;
		} catch (Exception e) {
			logger.error("Error al listar personaje"+e);
			throw new ComicException("CD-00f","error Listando los personajes", e);
		}			
	}

	@Override
	public PersonaDTO modificar(PersonaDTO personaDTO) throws ComicException {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 
	 * Metodo encargado de transformar un comic a un comicDTO
	 * 
	 * @param persona
	 * @return
	 * @throws ComicException 
	 */
	private PersonaDTO convertirPersonaToPersonaDTO(Persona persona) throws ComicException {
		try {
			logger.debug("inicio metodo Convertir persona a personaDTO");
			PersonaDTO personaDTO = new PersonaDTO();
			if(persona.getNdocumento()!=null) {
			 personaDTO.setNdocumento(persona.getNdocumento().toString());
			}
			personaDTO.setNdocumento(persona.getNdocumento());
			personaDTO.setTipoDoc(persona.getTipoDoc());
			personaDTO.setNombre(persona.getNombre());
			personaDTO.setFechaNaci(persona.getFechaNaci());
			logger.debug("finalizo metodo Convertir persona a personaDTO");
			return personaDTO;
		} catch (Exception e) {
			logger.error("Error al convertir  persona a personaDTO"+e);
			throw new ComicException("CD-00f","error al convertir persona a personaDTO", e);
		}
		
		
	}
	
	/*@Descripcion Metodo que Convierte de DTO a entidad Persona*/
	private Persona convertirPersonaDTOToPersona(PersonaDTO personaDTO) throws ComicException {
		try {
			logger.debug("Inicio metodo Convertir PersonaDTO a entidad");
			Persona persona = new Persona();
			if(personaDTO.getNdocumento()!=null) {
				persona.setNdocumento(personaDTO.getNdocumento());
			}
			persona.setNdocumento(personaDTO.getNdocumento());
			persona.setTipoDoc(personaDTO.getTipoDoc());
			persona.setNombre(personaDTO.getNombre());
			persona.setFechaNaci(personaDTO.getFechaNaci());
			logger.debug("finalizo metodo Convertir PersonaDTO a entidad");
			return persona;
		} catch (Exception e) {
			logger.error("Error al convertir PersonaDTO a entidad"+e);
			throw new ComicException("CD-00f","error al PersonaDTO a entidad", e);
		}
		
	}
	
}
