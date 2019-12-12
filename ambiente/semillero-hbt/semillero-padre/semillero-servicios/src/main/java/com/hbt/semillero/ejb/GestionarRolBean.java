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

/**
 * <b>Descripción:<b> Clase que determina el bean para realizar las gestion de
 * los roles
 */

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class GestionarRolBean implements IGestionarRolLocal {

	/*
	 * @linea agregada desde la pagina url suministrada con el instructor para
	 * generar log
	 */
	final static Logger logger = Logger.getLogger(GestionarComicBean.class);

	/**
	 * Atributo em que se usa para interacturar con el contexto de persistencia.
	 */
	@PersistenceContext
	private EntityManager em;

	/* Metodo que Registra un Rol */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void crearRol(RolDTO rolNuevo) {
		logger.debug("se Inicio el metodo  crearRol");
		// Entidad nueva
		Rol rol = convertirRolDTOToRol(rolNuevo);
		// Se almacena la informacion y se maneja la enidad Rol
		em.persist(rol);
		logger.debug("se Finalizo el metodo  crearRol ");
	}

	/* Metodo para modificar un ROl */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void modificarRol(Long id, String nombre, RolDTO rolNuevo) {
		logger.debug("se Inicio el metodo  modificarRol ");
		Rol rolModificar;
		if (rolNuevo == null) {
			// Entidad a modificar
			rolModificar = em.find(Rol.class, id);
		} else {
			rolModificar = convertirRolDTOToRol(rolNuevo);
		}
		rolModificar.setNombre(nombre);
		em.merge(rolModificar);
		logger.debug("se Finalizo el metodo  modificarRol ");

	}

	/* Metodo para eliminar un ROl */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void eliminarRol(Long idRol) {
		logger.debug("se Inicio el metodo  eliminarRol ");
		Rol rolEliminar = em.find(Rol.class, idRol);
		if (rolEliminar != null) {
			em.remove(rolEliminar);
		}
		logger.debug("se Finalizo el metodo  eliminarRol ");
	}

	/* METODO DE RETORNA UN SOLO ROL DEPENDIENDO SU ID */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<RolDTO> consultarRol(long idRol /* String idRol */) {
		logger.debug("se Inicio el metodo  consultarROL");

		/*
		 * Rol rol = null; rol = new Rol(); rol = em.find(Rol.class,
		 * Long.parseLong(idRol)); RolDTO rolDTO = convertirRolToRolDTO(rol);
		 * logger.debug("se Finalizo el metodo consultarROL ");
		 */
		List<RolDTO> resultadosRolDTO = new ArrayList<RolDTO>();
		List<Rol> resultados = em.createQuery("select r from Rol r where r.id=: idRol").setParameter("idRol", idRol)
				.getResultList();
		for (Rol rol : resultados) {
			resultadosRolDTO.add(convertirRolToRolDTO(rol));
		}
		logger.debug("se finalizo el metodo  consultarROL");
		return resultadosRolDTO;
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<RolDTO> consultarRol() {
		logger.debug("se Inicio el metodo consultarRol ");
		List<RolDTO> resultadosRolDTO = new ArrayList<RolDTO>();
		List<Rol> resultados = em.createQuery("select r from Rol r").getResultList();
		for (Rol rol : resultados) {
			resultadosRolDTO.add(convertirRolToRolDTO(rol));
		}
		logger.debug("se Finalizo el metodo consultarRol ");
		return resultadosRolDTO;
	}

	private Rol convertirRolDTOToRol(RolDTO rolDTO) {
		Rol rol = new Rol();
		rol.setId(rolDTO.getId());
		rol.setIdpersonaje(rolDTO.getIdpersonaje());
		rol.setNombre(rolDTO.getNombre());
		rol.setEstado(rolDTO.getEstado());
		return rol;
	}

	private RolDTO convertirRolToRolDTO(Rol rol) {

		RolDTO rolDTO = new RolDTO();
		rolDTO.setId(rol.getId());
		rolDTO.setIdpersonaje(rol.getIdpersonaje());
		rolDTO.setNombre(rol.getNombre());
		rolDTO.setEstado(rol.getEstado());

		return rolDTO;
	}

}
