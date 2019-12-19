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
import javax.management.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import com.hbt.semillero.dto.ComicDTO;
import com.hbt.semillero.dto.ConsultaTotalPersonajesComicDTO;
import com.hbt.semillero.entidad.Comic;
import com.hbt.semillero.entidad.Personaje;
import com.hbt.semillero.entidad.TematicaEnum;
import com.hbt.semillero.exceptions.ComicException;
import com.hbt.semillero.exceptions.PersonajeException;

/**
 * <b>Descripción:<b> Clase que determina el bean para realizar las gestion de
 * los comics
 * 
 * @author Luis David Mercado
 * @version
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class GestionarComicBean implements IGestionarComicLocal {
	
	/**
	 * Atributo em que se usa para interacturar con los Log de la libreria log4j.
	 */
	final static Logger logger = Logger.getLogger(GestionarComicBean.class);
	
	/**
	 * Atributo em que se usa para interacturar con el contexto de persistencia.
	 */
	@PersistenceContext
	private EntityManager em;

	/**
	 * 
	 * @throws ComicException 
	 * @see com.hbt.semillero.ejb.IGestionarComicLocal#crearComic(com.hbt.semillero.dto.ComicDTO)
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void crearComic(ComicDTO comicNuevo) throws ComicException {
		try {
			logger.debug("inicio metodo  Crear comic");
			// Entidad nueva
			Comic comic = convertirComicDTOToComic(comicNuevo);
			// Se almacena la informacion y se maneja la enidad comic
			em.persist(comic);
			logger.debug("finalizo metodo  Crear comic");
		} catch (Exception e) {
			logger.error("Error al Crear comic"+e);
			throw new ComicException("CD-00f","error ejecutando creacion del comic", e);
		}
		
	}

	/**
	 * 
	 * @throws ComicException 
	 * @see com.hbt.semillero.ejb.IGestionarComicLocal#modificarComic(com.hbt.semillero.dto.ComicDTO)
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void modificarComic(Long id, String nombre, ComicDTO comicNuevo) throws ComicException {
		try {
			logger.debug("inicio el metodo modificar comic");
			Comic comicModificar ;
			if(comicNuevo==null) {
				// Entidad a modificar
				comicModificar = em.find(Comic.class, id);
			}else {
				comicModificar = convertirComicDTOToComic(comicNuevo);
			}
			comicModificar.setNombre(nombre);
			em.merge(comicModificar);
			logger.debug("finalizo el metodo modificar comic");
		} catch (Exception e) {
			logger.error("Error al editar comic"+e);
			throw new ComicException("CD-00f","error ejecutando la modificacion del comic", e);
		}
		
	}

	/**
	 * 
	 * @throws ComicException 
	 * @see com.hbt.semillero.ejb.IGestionarComicLocal#eliminarComic(java.lang.Long)
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void eliminarComic(Long idComic) throws ComicException {
		try {
			
			Comic comicEliminar = em.find(Comic.class, idComic);
			if (comicEliminar != null) {
				em.remove(comicEliminar);
			/*javax.persistence.Query query =em.createQuery("Delete From Comic c where c.id=:idComic").setParameter("idComic", idComic);
			 query.executeUpdate();
			em.flush();
			em.clear();*/
			
		}
			}catch (Exception e) {
			logger.error("Error al eliminar comic"+e);
			throw new ComicException("CD-00f","error ejecutando eliminacion del comic", e);
		}
		
	}

	/**
	 * 
	 * @throws ComicException 
	 * @see com.hbt.semillero.ejb.IGestionarComicLocal#consultarComic(java.lang.String)
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public ComicDTO consultarComic(String idComic) throws ComicException {
		try {
			Comic comic = null;
			comic = new Comic();
			comic = em.find(Comic.class, Long.parseLong(idComic));
			ComicDTO comicDTO = convertirComicToComicDTO(comic);
			return comicDTO;
		} catch (Exception e) {
			logger.error("Error al consultar comic por id"+e);
			throw new ComicException("CD-00f","error ejecutando eliminacion del comic", e);
		}
		
	}

	/**
	 * 
	 * @throws ComicException 
	 * @see com.hbt.semillero.ejb.IGestionarComicLocal#consultarComics()
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<ComicDTO> consultarComics() throws ComicException {
		try {
			logger.debug("se ejecuta el método consultar comics");
			List<ComicDTO> resultadosComicDTO = new ArrayList<ComicDTO>();
			List<Comic> resultados = em.createQuery("select c from Comic c").getResultList();
			for (Comic comic:resultados) {
				resultadosComicDTO.add(convertirComicToComicDTO(comic));
			}
			return resultadosComicDTO;
		} catch (Exception e) {
			logger.error("Error al consultar comic por id"+e);
			throw new ComicException("CD-00f","error ejecutando consulta de comic", e);
		}
		
	}

	/**
	 * 
	 * Metodo encargado de transformar un comic a un comicDTO
	 * 
	 * @param comic
	 * @return
	 * @throws ComicException 
	 */
	private ComicDTO convertirComicToComicDTO(Comic comic) throws ComicException {
		try {
			logger.debug("inicio metodo Convertir entidad a comicDTO");
			ComicDTO comicDTO = new ComicDTO();
			if(comic.getId()!=null) {
			 comicDTO.setId(comic.getId().toString());
			}
			comicDTO.setNombre(comic.getNombre());
			comicDTO.setEditorial(comic.getEditorial());
			comicDTO.setTematicaEnum(comic.getTematicaEnum());
			comicDTO.setColeccion(comic.getColeccion());
			comicDTO.setNumeroPaginas(comic.getNumeroPaginas());
			comicDTO.setPrecio(comic.getPrecio());
			comicDTO.setAutores(comic.getAutores());
			comicDTO.setColor(comic.getColor());
			comicDTO.setFechaVenta(comic.getFechaVenta());
			comicDTO.setEstadoEnum(comic.getEstadoEnum());
			comicDTO.setCantidad(comic.getCantidad());
			logger.debug("finalizo metodo Convertir entidad a comicDTO");
			return comicDTO;
		} catch (Exception e) {
			logger.error("Error al convertir  Entidad a DTO"+e);
			throw new ComicException("CD-00f","error al convertir Entidad a DTO comic", e);
		}
		
	}

	/**
	 * 
	 * Metodo encargado de transformar un comicDTO a un comic
	 * 
	 * @param comic
	 * @return
	 * @throws ComicException 
	 */
	private Comic convertirComicDTOToComic(ComicDTO comicDTO) throws ComicException {
		try {
			logger.debug("Inicio metodo Convertir comicDTO a entidad");
			Comic comic = new Comic();
			if(comicDTO.getId()!=null) {
				comic.setId(Long.parseLong(comicDTO.getId()));
			}
			comic.setNombre(comicDTO.getNombre());
			comic.setEditorial(comicDTO.getEditorial());
			comic.setTematicaEnum(comicDTO.getTematicaEnum());
			comic.setColeccion(comicDTO.getColeccion());
			comic.setNumeroPaginas(comicDTO.getNumeroPaginas());
			comic.setPrecio(comicDTO.getPrecio());
			comic.setAutores(comicDTO.getAutores());
			comic.setColor(comicDTO.getColor());
			comic.setFechaVenta(comicDTO.getFechaVenta());
			comic.setEstadoEnum(comicDTO.getEstadoEnum());
			comic.setCantidad(comicDTO.getCantidad());
			logger.debug("finalizo metodo Convertir comicDTO a entidad");
			return comic;
		} catch (Exception e) {
			logger.error("Error al convertir DTO a Entidad"+e);
			throw new ComicException("CD-00f","error al convertir DTO a Entidad", e);
		}
		
	}

	@Override
	public void CalcularTotal(TematicaEnum tema, Comic co) {
		// TODO Auto-generated method stub
		
	}

	/*
	 * @descripcion Metodo que calcula el total de personajes por comic
	 */
	@Override
	public List<ConsultaTotalPersonajesComicDTO> consultarTotalPersonajeComic() throws ComicException {
		try {
			StringBuilder sb= new StringBuilder();
			//se crea un objeto de tipo ConsultaTotalPersonajesComicDTO en la consulta jpa
			sb.append("select new com.hbt.semillero.dto.ConsultaTotalPersonajesComicDTO(count(per.id),com.nombre) ");
			sb.append("From Personaje per ");
			sb.append("Join per.comic com ");
			sb.append("GROUP BY com.nombre");
			return em.createQuery(sb.toString()).getResultList();
		} catch (Exception e) {
			throw new ComicException("COM-012", "Error en la consulta de los totales por personaje", e);
		}
	
	}

	@Override
	public ComicDTO modificar(ComicDTO comicDTO) throws ComicException {
		if(comicDTO.getId()==null) {
			throw new ComicException("CD-00f","error identificador requerido");
		}
		javax.persistence.Query query=em.createQuery("UPDATE Comic comic "
				+ "SET comic.estadoEnum = :estado, "
				+ "comic.editorial = :editorial "
				+ "WHERE comic.id = :id");	
		
		query.setParameter("estado", comicDTO.getEstadoEnum());
		query.setParameter("editorial", comicDTO.getEditorial());
		query.setParameter("id",comicDTO.getId());
		query.executeUpdate();
		return convertirComicToComicDTO(em.find(Comic.class, comicDTO.getId()));
	}

	
}