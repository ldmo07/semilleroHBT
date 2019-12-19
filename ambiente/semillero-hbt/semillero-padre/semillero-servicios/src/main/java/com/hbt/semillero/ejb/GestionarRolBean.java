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

import com.hbt.semillero.dto.ComicDTO;
import com.hbt.semillero.dto.PersonajeDTO;
import com.hbt.semillero.dto.RolDTO;
import com.hbt.semillero.entidad.Comic;
import com.hbt.semillero.entidad.Personaje;
import com.hbt.semillero.entidad.Rol;
import com.hbt.semillero.exceptions.ComicException;
import com.hbt.semillero.exceptions.PersonajeException;
import com.hbt.semillero.exceptions.RolException;

/*
 * @Autor Luis david Mercado 
 */

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class GestionarRolBean implements IGestionarRolLocal{
	final static Logger logger = Logger.getLogger(GestionarComicBean.class);
	
	@PersistenceContext
	private EntityManager entityManager;
	
	/**
	 * 
	 * @throws RolException 
	 * @see com.hbt.semillero.ejb.IGestionarRolLocal#crearRol(com.hbt.semillero.dto.RolDTO)
	 */
	@Override
	public void crearRol(RolDTO rolDTO) throws RolException{
		try {
			Rol rol = convertirDTOEntidad(rolDTO);
			entityManager.persist(rol);
		} catch (Exception e) {
			logger.error("Error al crear rol"+e);
			throw new RolException("CD-00f","error creando ROL", e);
		}
		
		
	}

	/**
	 * 
	 * @throws RolException 
	 * @see com.hbt.semillero.ejb.IGestionarRolLocal#modificarRol(com.hbt.semillero.dto.RolDTO)
	 */
	@Override
	public void modificarRol(Long id, String nombre,RolDTO rolDTO) throws RolException {
		try {
			logger.error("inicio el metodo modificar Rol");
			Rol rolModificar ;
			if(rolDTO==null) {
				// Entidad a modificar
				rolModificar = entityManager.find(Rol.class, id);
			}else {
				rolModificar = convertirDTOEntidad(rolDTO);
			}
			rolModificar.setNombre(nombre);
			entityManager.merge(rolModificar);
			logger.error("finalizo el metodo modificar Rol");
		} catch (Exception e) {
			logger.error("Error al editar ROl"+e);
			throw new RolException("CD-00f","error ejecutando Actualizacion del ROl", e);
		}
	}
	
	/**
	 * 
	 * @throws RolException 
	 * @see com.hbt.semillero.ejb.IGestionarRolLocal#eliminarRol(com.hbt.semillero.dto.RolDTO)
	 */
	@Override
	public void eliminarRol(Long idRol) throws RolException {
try {
	logger.debug("Aqui inicia el metodo EliminarRol");
			Query query = (Query) entityManager.createQuery("Delete From Rol r where r.idRol=:idRol").setParameter("idRol", idRol);
			((javax.persistence.Query) query).executeUpdate();
			logger.debug("Aqui finaliza el metodo EliminarRol");
			entityManager.flush();
			entityManager.clear();
		} catch (Exception e) {
			logger.error("Error al eliminar Rol"+e);
			throw new RolException("CD-00f","error ejecutando eliminacion del Rol", e);
		}
	}

	/**
	 * 
	 * @throws RolException 
	 * @see com.hbt.semillero.ejb.IGestionarRolLocal#consultarRol(com.hbt.semillero.dto.RolDTO)
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public  List<RolDTO> consultarRol() throws RolException {
		/*try {
			logger.debug("Aqui inicia el metodo ConsultarRol");

			String query = "SELECT rol FROM Rol rol";
			List<Rol> resultados = entityManager.createQuery(query).getResultList();
			List<RolDTO> resultadosRolDTO = new ArrayList<>();
			for (Rol rol : resultados) {
				resultadosRolDTO.add(convertirEntidadDTO(rol));
			}
			logger.debug("Aqui finaliza el metodo ConsultarRol");

			return resultadosRolDTO;
		} catch (Exception e) {
			logger.error("Error al consultar Rol"+e);
			throw new RolException("CD-00f","error al listar Rol ", e);
		}*/
		try {
			logger.debug("se ejecuta el método consultar comics");
			List<RolDTO> resultadosComicDTO = new ArrayList<RolDTO>();
			List<Rol> resultados = entityManager.createQuery("SELECT rol FROM Rol rol").getResultList();
			for (Rol comic:resultados) {
				resultadosComicDTO.add(convertirEntidadDTO(comic));
			}
			return resultadosComicDTO;
		} catch (Exception e) {
			logger.error("Error al consultar Rol @@@@@@@"+e);
			throw new RolException("CD-00fxx32@@@","error ejecutando consulta del Rol enn GROLB", e);
		}
		
	}

	/**
	 * 
	 * @throws RolException 
	 * @see com.hbt.semillero.ejb.IGestionarRolLocal#consultarRoles(com.hbt.semillero.dto.RolDTO)
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<RolDTO>  consultarRoles(Long idPersonaje) throws RolException {
		try {
			logger.debug("Aqui inicia el metodo ConsultarRoles");

			String query = "SELECT rol FROM Rol rol WHERE rol.personaje.id = :idPersonaje ";
			List<Rol> resultados = entityManager.createQuery(query).setParameter("idPersonaje", idPersonaje).getResultList();
			List<RolDTO> resultadosRolDTO = new ArrayList<RolDTO>();
			for (Rol rol : resultados) {
				resultadosRolDTO.add(convertirEntidadDTO(rol));
			}
			logger.debug("Aqui finaliza el metodo ConsultarRoles");
			return resultadosRolDTO;
		} catch (Exception e) {
			logger.error("Error al listar rol por id"+e);
			throw new RolException("CD-00f","error al listar Rol ", e);
		}
		
	}
	
	/**
	 * Metodo encargado de transformar un rolDTO a un rol
	 * 
	 * @param rolDTO
	 * @return
	 * @throws RolException 
	 */
	public Rol convertirDTOEntidad(RolDTO rolDTO) throws RolException  {
		try {
			Rol rol = new Rol();
			rol.setId(rolDTO.getId());
			rol.setNombre(rolDTO.getNombre());
			rol.setPersonaje(new Personaje());
			rol.getPersonaje().setId(rolDTO.getIdPersonaje());
			rol.setEstado(rolDTO.getEstado());
			return rol;
		} catch (Exception e) {
			logger.error("Error al convertir rol"+e);
			throw new RolException("CD-00f","error convirtiendo  DTO a entidad", e);
		}
		
	}
	
	/**
	 * Metodo encargado de transformar un rol a un rolDTO
	 * 
	 * @param rol
	 * @return
	 * @throws RolException 
	 */
	private RolDTO convertirEntidadDTO(Rol rol) throws RolException  {
		try {
			RolDTO rolDTO = new RolDTO();
			rolDTO.setIdPersonaje(rol.getPersonaje().getId());
			rolDTO.setEstado(rol.getEstado());
			rolDTO.setNombre(rol.getNombre());
			rolDTO.setId(rol.getId());
			return rolDTO;
		} catch (Exception e) {
			logger.error("Error al convertir DTO"+e);
			throw new RolException("CD-00f","error convirtiendo entida a DTO", e);
		}
	
	}


}