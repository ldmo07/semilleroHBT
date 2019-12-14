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
import com.hbt.semillero.dto.RolDTO;
import com.hbt.semillero.entidad.Comic;
import com.hbt.semillero.entidad.Personaje;
import com.hbt.semillero.entidad.Rol;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class GestionarRolBean implements IGestionarRolLocal{
	final static Logger logger = Logger.getLogger(GestionarComicBean.class);
	
	@PersistenceContext
	private EntityManager entityManager;
	
	/**
	 * 
	 * @see com.hbt.semillero.ejb.IGestionarRolLocal#crearRol(com.hbt.semillero.dto.RolDTO)
	 */
	@Override
	public void crearRol(RolDTO rolDTO){
		Rol rol = convertirDTOEntidad(rolDTO);
		entityManager.persist(rol);
		
	}

	/**
	 * 
	 * @see com.hbt.semillero.ejb.IGestionarRolLocal#modificarRol(com.hbt.semillero.dto.RolDTO)
	 */
	@Override
	public void modificarRol(Long id, String nombre,RolDTO rolDTO) {
		logger.debug("Aqui inicia el metodo ModificarRol");

		logger.debug("Aqui finaliza el metodo ModificarRol");
	}
	
	/**
	 * 
	 * @see com.hbt.semillero.ejb.IGestionarRolLocal#eliminarRol(com.hbt.semillero.dto.RolDTO)
	 */
	@Override
	public void eliminarRol(Long idRol) {
		logger.debug("Aqui inicia el metodo EliminarRol");

		logger.debug("Aqui finaliza el metodo EliminarRol");
	}

	/**
	 * 
	 * @see com.hbt.semillero.ejb.IGestionarRolLocal#consultarRol(com.hbt.semillero.dto.RolDTO)
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public  List<RolDTO> consultarRol() {
		logger.debug("Aqui inicia el metodo ConsultarRol");

		String query = "SELECT rol FROM Rol rol";
		List<Rol> resultados = entityManager.createQuery(query).getResultList();
		List<RolDTO> resultadosRolDTO = new ArrayList<>();
		for (Rol rol : resultados) {
			resultadosRolDTO.add(convertirEntidadDTO(rol));
		}
		logger.debug("Aqui finaliza el metodo ConsultarRol");

		return resultadosRolDTO;
	}

	/**
	 * 
	 * @see com.hbt.semillero.ejb.IGestionarRolLocal#consultarRoles(com.hbt.semillero.dto.RolDTO)
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<RolDTO>  consultarRoles(Long idPersonaje) {
		logger.debug("Aqui inicia el metodo ConsultarRoles");

		String query = "SELECT rol FROM Rol rol WHERE rol.personaje.id = :idPersonaje ";
		List<Rol> resultados = entityManager.createQuery(query).setParameter("idPersonaje", idPersonaje).getResultList();
		List<RolDTO> resultadosRolDTO = new ArrayList<RolDTO>();
		for (Rol rol : resultados) {
			resultadosRolDTO.add(convertirEntidadDTO(rol));
		}
		logger.debug("Aqui finaliza el metodo ConsultarRoles");
		return resultadosRolDTO;
	}
	
	/**
	 * Metodo encargado de transformar un rolDTO a un rol
	 * 
	 * @param rolDTO
	 * @return
	 */
	public Rol convertirDTOEntidad(RolDTO rolDTO) {
		Rol rol = new Rol();
		rol.setId(rolDTO.getId());
		rol.setNombre(rolDTO.getNombre());
		rol.setPersonaje(new Personaje());
		rol.getPersonaje().setId(rolDTO.getIdPersonaje());
		rol.setEstado(rolDTO.getEstado());
		return rol;
	}
	
	/**
	 * Metodo encargado de transformar un rol a un rolDTO
	 * 
	 * @param rol
	 * @return
	 */
	private RolDTO convertirEntidadDTO(Rol rol) {
		RolDTO rolDTO = new RolDTO();
		rolDTO.setIdPersonaje(rol.getPersonaje().getId());
		rolDTO.setEstado(rol.getEstado());
		rolDTO.setNombre(rol.getNombre());
		rolDTO.setId(rol.getId());
		return rolDTO;
	}


}