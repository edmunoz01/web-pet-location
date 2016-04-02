package com.abc.pet.socket;


import java.util.HashMap;
import java.util.Map;

import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/websocketABC/{idCollar}")
public class SocketABC {

	private static Map<Long, Session> sessiones;

	@OnMessage
	public void sayHello(String idCollar, Session session) {
		if (sessiones == null) {
			sessiones = new HashMap<Long, Session>();
		}
		try {
			this.sessiones.put(Long.valueOf(idCollar), session);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@OnOpen
	public void helloOnOpen(Session session, EndpointConfig config) {
		if (sessiones == null) {
			sessiones = new HashMap<Long, Session>();
		}
		System.out.println("WebSocket opened: " + session.getId());
	}

	@OnClose
	public void helloOnClose(CloseReason reason) {
		System.out.println("Closing a WebSocket due to "
				+ reason.getReasonPhrase());
	}

	public static Map<Long, Session> getSessiones() {
		return sessiones;
	}

	public static void setSessiones(Map<Long, Session> sessiones) {
		SocketABC.sessiones = sessiones;
	}

}
