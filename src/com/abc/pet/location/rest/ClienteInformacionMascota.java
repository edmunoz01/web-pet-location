package com.abc.pet.location.rest;

import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.websocket.Session;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.abc.pet.socket.SocketABC;

/**
 * Clase encargada de definir los servicios REST en donde se registra el cliente
 * para el envio de informacion
 * 
 * @author Carlos Mario
 * 
 */
@Path("/ClienteInformacionMascota")
public class ClienteInformacionMascota {

	@Context
	private ServletContext context;

	/**
	 * Servicio que recibe un idCollar para crear un webSocket a traves del cual
	 * se enviara la informacion al navegador del cliente
	 * 
	 * @return response
	 */
	@GET
	@Path("/{idCollar}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response recibirInformacionMascota(
			@PathParam("idCollar") Long idCollar) {
		if (context.getAttribute("MapaWEBSockets") == null) {
			context.setAttribute("MapaWEBSockets",
					new java.util.HashMap<Long, Session>());
		}
		if (idCollar == null) {
			return Response.serverError().build();
		}
		if (((Map<Long, Session>) context.getAttribute("MapaWEBSockets"))
				.get(idCollar) == null) {
			((Map<Long, Session>) context.getAttribute("MapaWEBSockets")).put(
					idCollar, SocketABC.getSessiones().get(idCollar));
		}
		return Response.ok().build();
	}

}