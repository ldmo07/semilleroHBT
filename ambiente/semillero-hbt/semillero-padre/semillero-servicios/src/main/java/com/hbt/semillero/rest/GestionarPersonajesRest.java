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

import com.hbt.semillero.dto.PersonajeDTO;
import com.hbt.semillero.ejb.IGestionarPersonajeLocal;

@Path("/GestionarPersonaje")
public class GestionarPersonajesRest {
	
	/**
	 * Atriburo que permite gestionar un Perosnaje
	 */
	@EJB
	private IGestionarPersonajeLocal gestionarPersonajeEJB;
	@POST
	@Path("/crearPerosnaje")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void crearPersonaje(PersonajeDTO personajeNuevo) {
		gestionarPersonajeEJB.crearPersonaje(personajeNuevo);
	}
	
	@GET
	@Path("/consultarPersonaje")
	@Produces(MediaType.APPLICATION_JSON)
	public List<PersonajeDTO>consultarPersonaje(@QueryParam("idComic") long idPersonaje){
		return gestionarPersonajeEJB.consultarPersonaje(idPersonaje);
	}
	
	@GET
	@Path("/consultarPersonajes")
	@Produces(MediaType.APPLICATION_JSON)
	public List<PersonajeDTO> consultarPersonaje(){
		return gestionarPersonajeEJB.consultarPersonaje();
		}
	
}
