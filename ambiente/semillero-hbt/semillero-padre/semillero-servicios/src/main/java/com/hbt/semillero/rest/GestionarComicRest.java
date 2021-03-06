/**
 * GestionarComicRest.java
 */
package com.hbt.semillero.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.hbt.semillero.dto.ComicDTO;
import com.hbt.semillero.dto.ConsultaTotalPersonajesComicDTO;
import com.hbt.semillero.dto.PersonajeDTO;
import com.hbt.semillero.dto.ResultadoDTO;
import com.hbt.semillero.ejb.GestionarComicBean;
import com.hbt.semillero.ejb.IGestionarComicLocal;
import com.hbt.semillero.exceptions.ComicException;
import com.hbt.semillero.exceptions.PersonajeException;

/**
 * <b>Descripción:<b> Clase que determina el servicio rest que permite gestionar
 * un comic
 * 
 * @author ccastano
 * @version
 */
@Path("/GestionarComic")
public class GestionarComicRest {

	final static Logger logger = Logger.getLogger(GestionarComicBean.class);
	/**
	 * Atriburo que permite gestionar un comic
	 */
	@EJB
	private IGestionarComicLocal gestionarComicEJB;

	/**
	 * 
	 * Metodo encargado de traer la informacion de un comic determiando
	 * http://localhost:8085/semillero-servicios/rest/GestionarComic/saludo
	 * 
	 * @param idComic
	 * @return
	 */
	@GET
	@Path("/saludo")
	@Produces(MediaType.APPLICATION_JSON)
	public Response primerRest() {
		String saludo="Prueba inicial servicios rest en el semillero java hbt";
		return Response.status(Response.Status.OK).entity(saludo).type(MediaType.APPLICATION_JSON).build();
	}

	/**
	 * 
	 * Metodo encargado de traer la informacion de un comic determiando
	 * http://localhost:8085/semillero-servicios/rest/GestionarComic/consultarComics
	 * 
	 * @param idComic
	 * @return
	 */
	@GET
	@Path("/consultarComics")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ComicDTO> consultarComic() {
		try {
			return gestionarComicEJB.consultarComics();
		} catch (ComicException e) {
			logger.error("excepcion Crear Comic capturada en el rest codigo "+e.getCodigo()+" mensaje "+e.getMensaje());
			return null;
		}
		

	}

	/**
	 * 
	 * Metodo encargado de traer la informacion de un comic determiando
	 * http://localhost:8085/semillero-servicios/rest/GestionarComic/consultarComic?idComic=1
	 * 
	 * @param idComic
	 * @return
	 */
	@GET
	@Path("/consultarComic")
	@Produces(MediaType.APPLICATION_JSON)
	public ComicDTO consultarComic(@QueryParam("idComic") Long idComic) {
		try {
			//if (idComic != null) {
				/*ComicDTO comicDTO =*/return gestionarComicEJB.consultarComic(idComic.toString());
				//return comicDTO;
			//}
		} catch (ComicException e) {
			logger.error("excepcion Modificar comic capturada en el rest codigo "+e.getCodigo()+" mensaje "+e.getMensaje());
			return null;
		}
		
	
	}

	/**
	 * Crea las personas en sus diferentes roles dentro del sistema.
	 * http://localhost:8085/semillero-servicios/rest/GestionarComic/crear
	 * @param persona
	 * @return
	 */
	@POST
	@Path("/crear")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response crearComic(ComicDTO comicDTO) {
		try {
			gestionarComicEJB.crearComic(comicDTO);
			ResultadoDTO resultadoDTO = new ResultadoDTO(Boolean.TRUE, "Comic creado exitosamente");
			return Response.status(Response.Status.CREATED).entity(resultadoDTO).build();
		} catch (ComicException e) {
			logger.error("excepcion Crear Comic capturada en el rest codigo "+e.getCodigo()+" mensaje "+e.getMensaje());
			return Response.status(Response.Status.BAD_REQUEST).entity("Se presento un erro al crear el comic "+e).build();
		}
		
		
		
	}

	/**
	 * 
	 * Metodo encargado de modificar el nombre de un comic
	 * http://localhost:8085/semillero-servicios/rest/GestionarComic/modificar?idComic=1&nombre=nuevonombre
	 * @param idComic identificador del comic a buscar
	 * @param nombre nombre nuevo del comic
	 */
	@POST
	@Path("/modificar")
	@Produces(MediaType.APPLICATION_JSON)
	public void modificarComic(@QueryParam("idComic") Long idComic, @QueryParam("nombre") String nombre) {
		try {
			gestionarComicEJB.modificarComic(idComic, nombre, null);
		}catch (ComicException e) {
			logger.error("excepcion Modificar comic capturada en el rest codigo "+e.getCodigo()+" mensaje "+e.getMensaje());
		}
		
	}

	/**
	 * 
	 * Metodo encargado de eliminar un comic dado el id
	 * 
	 * @param idComic identificador del comic
	 */
	@POST
	@Path("/eliminar")
	@Produces(MediaType.APPLICATION_JSON)
	public void eliminarComic(@QueryParam("idComic") Long idComic) {
		try {
			if (idComic != null) {
				 gestionarComicEJB.eliminarComic(idComic);
			}
		} catch (ComicException e) {
			logger.error("excepcion elimanar capturada en el rest codigo "+e.getCodigo()+" mensaje "+e.getMensaje());
		}
		
	}
	
	@GET
	@Path("/consultarPersonajesPorComic")
	@Produces(MediaType.APPLICATION_JSON)
	public Response  consultarPersonajes(){
		try {
			List<ConsultaTotalPersonajesComicDTO> listaTotales= gestionarComicEJB.consultarTotalPersonajeComic();
			return Response.status(Response.Status.OK).entity(listaTotales).build();
			 
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(e).build();
		}	
	}
	
	
	//////////////////////////////////////////////////////////////
	
	@POST
	@Path("/modificar2")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response modificarPersonaje(ComicDTO comicDTO) {
		try {
			gestionarComicEJB.modificar(comicDTO);	
			return Response.status(Response.Status.OK).entity(comicDTO).build();
		} catch (ComicException e) {
			logger.error("excepcion crear personaje capturada en el rest codigo "+e.getCodigo()+" mensaje "+e.getMensaje());
			return Response.status(Response.Status.BAD_REQUEST).entity("Error Creando el Personaje "+e).build();
		}
		
	}
	
}
