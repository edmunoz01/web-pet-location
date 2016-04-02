package com.abc.pet.location.rest;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.websocket.Session;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.abc.pet.location.pojo.Mensaje;
import com.abc.pet.location.pojo.Respuesta;

/**
 * Clase encargada de definir los servicios REST que reciben la informacion de
 * las mascotas
 * 
 * @author Carlos Mario
 * 
 */
@Path("/InformacionMascota")
public class InformacionMascota {

	@Context
	private ServletContext context;

	/**
	 * Servicio que recibe la informacion de las mascotas: Ubicacion geografica
	 * a traves de coordenadas, id del collar, frecuencia respiratoria y
	 * frecuencia cardiaca
	 * 
	 * @return response
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response recibirInformacionMascota(Mensaje mensaje) {
		if (mensaje == null) {
			return Response.serverError().build();
		}
		if (mensaje.getIdCollar() != null) {
			Respuesta respuesta = new Respuesta();
			respuesta.setLatitud(mensaje.getLatitud());
			respuesta.setLongitud(mensaje.getLongitud());

			if (((Map<Long, Session>) context.getAttribute("MapaWEBSockets")) != null
					&& ((Map<Long, Session>) context
							.getAttribute("MapaWEBSockets")).get(mensaje
							.getIdCollar()) != null) {
				try {
					((Map<Long, Session>) context
							.getAttribute("MapaWEBSockets"))
							.get(mensaje.getIdCollar()).getBasicRemote()
							.sendText(mensaje.getLongitud().toString());
				} catch (IOException e) {
					e.printStackTrace();
					return Response.serverError().build();
				}
			}
			return Response.ok().entity(respuesta).build();
		} else {
			return Response.serverError().build();
		}
	}

	@GET
	@Path("/prueba")
	@Produces(MediaType.TEXT_PLAIN)
	public Response prueba() {
		return Response.ok().entity("Servicio REST de prueba metodo GET")
				.build();
	}
}