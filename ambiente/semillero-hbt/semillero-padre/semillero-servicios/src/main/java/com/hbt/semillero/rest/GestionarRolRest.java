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
import com.hbt.semillero.dto.RolDTO;
import com.hbt.semillero.ejb.IGestionarPersonajeLocal;
import com.hbt.semillero.ejb.IGestionarRolLocal;

@Path("/GestionarRoles")
public class GestionarRolRest {
	
	/**
	 * Atriburo que permite gestionar un rol
	 */
	@EJB
	private IGestionarRolLocal gestionarRolEJB;
	@POST
	@Path("/crearRol")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void crearRol(RolDTO rolNuevo) {
		gestionarRolEJB.crearRol(rolNuevo);
	}
	
	@GET
	@Path("/consultarRol")
	@Produces(MediaType.APPLICATION_JSON)
	public List<RolDTO>consultarRol(@QueryParam("idRol") long idRol){
		return gestionarRolEJB.consultarRol(idRol);
	}
	
	@GET
	@Path("/consultarRoles")
	@Produces(MediaType.APPLICATION_JSON)
	public List<RolDTO> consultarRol(){
		return gestionarRolEJB.consultarRol();
		}
	
}
