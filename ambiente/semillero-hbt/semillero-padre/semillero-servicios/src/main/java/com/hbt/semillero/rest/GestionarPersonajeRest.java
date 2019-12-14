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

import org.apache.log4j.Logger;

import com.hbt.semillero.dto.ComicDTO;
import com.hbt.semillero.dto.PersonajeDTO;
import com.hbt.semillero.ejb.GestionarComicBean;
import com.hbt.semillero.ejb.IGestionarComicLocal;
import com.hbt.semillero.ejb.IGestionarPersonajeLocal;
import com.hbt.semillero.exceptions.PersonajeException;

@Path("/GestionarPersonaje")
public class GestionarPersonajeRest {
	final static Logger logger = Logger.getLogger(GestionarComicBean.class);
	@EJB
	private IGestionarPersonajeLocal gestionarPersonajeEJB;
	
	@GET
	@Path("/saludo")
	@Produces(MediaType.APPLICATION_JSON)
	public String primerRest() {
		return "Prueba inicial servicios rest en el semillero java hbt";
	}
	
	@GET
	@Path("/consultarPersonaje")
	@Produces(MediaType.APPLICATION_JSON)
	public  List<PersonajeDTO> consultarPersonaje(){
		try {
			return gestionarPersonajeEJB.consultarPersonaje();
		} catch (PersonajeException e) {
			
			logger.error("excepcion consulta personaje capturada en el rest codigo "+e.getCodigo()+" mensaje "+e.getMensaje());
			return null;
		}
				
	};
	
	@GET
	@Path("/consultarPersonajeById")
	@Produces(MediaType.APPLICATION_JSON)
	public List<PersonajeDTO>  consultarPersonajes(@QueryParam("idComic") Long idComic){
		try {
			return gestionarPersonajeEJB.consultarPersonajes(idComic);
		} catch (PersonajeException e) {
			logger.error("excepcion consulta personaje capturada en el rest codigo "+e.getCodigo()+" mensaje "+e.getMensaje());
			return null;
		}
		
		
	};
	
	@POST
	@Path("/crearPersonaje")
	@Consumes(MediaType.APPLICATION_JSON)
	public void crearPersonaje(PersonajeDTO personajeDTO) {
		try {
			gestionarPersonajeEJB.crearPersonaje(personajeDTO);	
		} catch (PersonajeException e) {
			logger.error("excepcion crear personaje capturada en el rest codigo "+e.getCodigo()+" mensaje "+e.getMensaje());
			
		}
		
	}

}