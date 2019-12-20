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
import com.hbt.semillero.dto.PersonaDTO;
import com.hbt.semillero.dto.PersonajeDTO;
import com.hbt.semillero.dto.ResultadoDTO;
import com.hbt.semillero.ejb.GestionarPersonaBean;
import com.hbt.semillero.ejb.IGestionarPersonaLocal;
import com.hbt.semillero.exceptions.ComicException;
import com.hbt.semillero.exceptions.PersonajeException;

@Path("/GestionarPersona")
public class GestionarPersonaRest {
	
	final static Logger logger = Logger.getLogger(GestionarPersonaBean.class);
	@EJB
	private IGestionarPersonaLocal gestionarPersonaEJB;
	
	@GET
	@Path("/prueba")
	@Produces(MediaType.APPLICATION_JSON)
	public Response primerRest() {
		String saludo="Funciona el Rest De Persona";
		return Response.status(Response.Status.OK).entity(saludo).type(MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("/consultarPersonas")
	@Produces(MediaType.APPLICATION_JSON)
	public List<PersonaDTO> consultarPersonas(){
		try {
			return gestionarPersonaEJB.consultarPersonas();
		} catch (ComicException e) {
			logger.error("excepcion Crear Comic capturada en el rest codigo "+e.getCodigo()+" mensaje "+e.getMensaje());
			return null;
		}
	}
	
	
	@POST
	@Path("/crear")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response crearPersona(PersonaDTO personaDTO) {
		try {
			gestionarPersonaEJB.crearPersona(personaDTO);
			ResultadoDTO resultadoDTO = new ResultadoDTO(Boolean.TRUE, "Persona creada exitosamente");
			return Response.status(Response.Status.CREATED).entity(resultadoDTO).build();
		} catch (ComicException e) {
			logger.error("excepcion Crear Persona capturada en el rest codigo "+e.getCodigo()+" mensaje "+e.getMensaje());
			return Response.status(Response.Status.BAD_REQUEST).entity("Se presento un erro al crear el Persona "+e).build();
		}
		
	}
	
	@POST
	@Path("/eliminar")
	@Produces(MediaType.APPLICATION_JSON)
	public void eliminarComic(@QueryParam("ndocumento") String ndocumento) {
		try {
			if (ndocumento != null) {
				 gestionarPersonaEJB.eliminarPersona(ndocumento);		 
			}
		} catch (ComicException e) {
			logger.error("excepcion elimanar capturada en el rest codigo "+e.getCodigo()+" mensaje "+e.getMensaje());
		}
		
	}
	
}
