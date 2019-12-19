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

import com.hbt.semillero.dto.PersonajeDTO;
import com.hbt.semillero.dto.ResultadoDTO;
import com.hbt.semillero.dto.RolDTO;
import com.hbt.semillero.ejb.GestionarComicBean;
import com.hbt.semillero.ejb.IGestionarPersonajeLocal;
import com.hbt.semillero.ejb.IGestionarRolLocal;
import com.hbt.semillero.exceptions.ComicException;
import com.hbt.semillero.exceptions.RolException;

@Path("/GestionarRol")
public class GestionarRolRest {
	final static Logger logger = Logger.getLogger(GestionarComicBean.class);
	
	@EJB
	private IGestionarRolLocal gestionarRolEJB;
	
	@GET
	@Path("/saludo")
	@Produces(MediaType.APPLICATION_JSON)
	public String primerRest() {
		return "Prueba inicial servicios rest en el semillero java hbt";
	}
	
	@GET
	@Path("/consultarRol")
	@Produces(MediaType.APPLICATION_JSON)
	public  List<RolDTO> consultarRol(){
		/*try {
			return gestionarRolEJB.consultarRol();
		} catch (RolException e) {
			logger.error("excepcion consultar por id capturada en el rest codigo "+e.getCodigo()+" mensaje "+e.getMensaje());
			return null;
		}*/
		
		try {
			return gestionarRolEJB.consultarRol();
		} catch (RolException e) {
			logger.error("excepcion Crear Comic capturada en el rest codigo "+e.getCodigo()+" mensaje "+e.getMensaje());
			return null;
		}
		

	}
	
	@GET
	@Path("/consultarRolById")
	@Produces(MediaType.APPLICATION_JSON)
	public List<RolDTO>  consultarRoles(@QueryParam("idPersonaje") Long idPersonaje){
		try {
			return gestionarRolEJB.consultarRoles(idPersonaje);
		} catch (RolException e) {
			logger.error("excepcion consultar capturada en el rest codigo "+e.getCodigo()+" mensaje "+e.getMensaje());// TODO: handle exception
			return null;
		}
		
		
		
	};
	
	@POST
	@Path("/crearRol")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response crearRol(RolDTO rolDTO) {
		try {
			gestionarRolEJB.crearRol(rolDTO);	
			ResultadoDTO resultadoDTO = new ResultadoDTO(Boolean.TRUE, "ROL creado exitosamente");
			return Response.status(Response.Status.CREATED).entity(resultadoDTO).build();
		} catch (RolException e) {
			logger.error("excepcion Crear capturada en el rest codigo "+e.getCodigo()+" mensaje "+e.getMensaje());
			return Response.status(Response.Status.CREATED).entity("error al Crear el ROL" +e).build();
		}
		
	}

}