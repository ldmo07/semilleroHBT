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
import com.hbt.semillero.dto.PersonajeDTO;
import com.hbt.semillero.dto.ResultadoDTO;
import com.hbt.semillero.ejb.GestionarComicBean;
import com.hbt.semillero.ejb.IGestionarComicLocal;
import com.hbt.semillero.ejb.IGestionarPersonajeLocal;
import com.hbt.semillero.exceptions.PersonajeException;

@Path("/GestionarPersonaje")
public class GestionarPersonajeRest {
	final static Logger logger = Logger.getLogger(GestionarComicBean.class);
	@EJB
	private IGestionarPersonajeLocal gestionarPersonajeEJB;
	
	/*
	 * @descripcion metodo Response que retorna una string que contiene un saludo
	 */
	@GET
	@Path("/saludo")
	@Produces(MediaType.APPLICATION_JSON)
	public String primerRest() {
		return "Prueba inicial servicios rest en el semillero java hbt";
	}
	
	/*
	 * @descripcion metodo Response que retorna una lista de personajes
	 */
	@GET
	@Path("/consultarPersonaje")
	@Produces(MediaType.APPLICATION_JSON)
	public Response  consultarPersonaje(){
		try {
			List<PersonajeDTO> listaPersonaje=gestionarPersonajeEJB.consultarPersonaje();
			return Response.status(Response.Status.OK).entity(listaPersonaje).build();
		} catch (PersonajeException e) {
			
			logger.error("excepcion consulta personaje capturada en el rest codigo "+e.getCodigo()+" mensaje "+e.getMensaje());
			return Response.status(Response.Status.BAD_REQUEST).entity(e).build();
		}
				
	};
	
	/*
	 * @descripcion metodo Response que retorna un  personaje segun su id
	 */
	@GET
	@Path("/consultarPersonajeById")
	@Produces(MediaType.APPLICATION_JSON)
	public Response  consultarPersonajes(@QueryParam("idComic") Long idComic){
		try {
			List<PersonajeDTO> personaje=gestionarPersonajeEJB.consultarPersonajes(idComic);
			return Response.status(Response.Status.OK).entity(personaje).build();
		} catch (PersonajeException e) {
			logger.error("excepcion consulta personaje capturada en el rest codigo "+e.getCodigo()+" mensaje "+e.getMensaje());
			return null;
		}
		
		
	}
		
	/*
	 * @descripcion metodo Response que retorna un estatus 201 si se crea un personaje
	 */
	@POST
	@Path("/crearPersonaje")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response crearPersonaje(PersonajeDTO personajeDTO) {
		try {
			gestionarPersonajeEJB.crearPersonaje(personajeDTO);	
			return Response.status(Response.Status.CREATED).entity(personajeDTO).build();
		} catch (PersonajeException e) {
			logger.error("excepcion crear personaje capturada en el rest codigo "+e.getCodigo()+" mensaje "+e.getMensaje());
			return Response.status(Response.Status.BAD_REQUEST).entity("Error Creando el Personaje "+e).build();
		}
		
	}
	
	/*
	 * @descripcion metodo Response que retorna un estatus 200 si se modifica un personaje
	 */
	@POST
	@Path("/modificar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response modificarPersonaje(PersonajeDTO personajeDTO) {
		try {
			gestionarPersonajeEJB.modificar(personajeDTO);	
			return Response.status(Response.Status.OK).entity(personajeDTO).build();
		} catch (PersonajeException e) {
			logger.error("excepcion crear personaje capturada en el rest codigo "+e.getCodigo()+" mensaje "+e.getMensaje());
			return Response.status(Response.Status.BAD_REQUEST).entity("Error Creando el Personaje "+e).build();
		}
		
	}

}