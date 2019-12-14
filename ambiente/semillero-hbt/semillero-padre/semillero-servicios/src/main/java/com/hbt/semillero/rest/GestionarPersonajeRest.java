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

import com.hbt.semillero.dto.ComicDTO;
import com.hbt.semillero.dto.PersonajeDTO;
import com.hbt.semillero.ejb.IGestionarComicLocal;
import com.hbt.semillero.ejb.IGestionarPersonajeLocal;

@Path("/GestionarPersonaje")
public class GestionarPersonajeRest {
	
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
		return gestionarPersonajeEJB.consultarPersonaje();
		
	};
	
	@GET
	@Path("/consultarPersonajeById")
	@Produces(MediaType.APPLICATION_JSON)
	public List<PersonajeDTO>  consultarPersonajes(@QueryParam("idComic") Long idComic){
		return gestionarPersonajeEJB.consultarPersonajes(idComic);
		
	};
	
	@POST
	@Path("/crearPersonaje")
	@Consumes(MediaType.APPLICATION_JSON)
	public void crearPersonaje(PersonajeDTO personajeDTO) {
		gestionarPersonajeEJB.crearPersonaje(personajeDTO);	
	}

}