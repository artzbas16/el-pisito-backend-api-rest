package com.ipartek.springboot.backend.apirest.elpisito.security;

import java.io.IOException;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTAccesDeniedHandler implements AccessDeniedHandler{

	/*Gestiona un error de acceso denegado (403) */
	
	//Su proposito principal es:
	//Devolver una respuesta adecuada para indicar que, aun que estes autentificado, el recurso no es 
	//accesible para tu grado de ROL
	
	//403 Forbidden --> autentificacion sin permisos --> AccesDeniedHandler
	//403 --> Se quien eres pero no puedes
	
	//Flujo de un 403
	//Request --> JWTFilter --> Authetification (OK) --> Authorization (permisos insuficientes) --> AcccesDeniedHandler --> 403 Forbidden
	
	@Override
	public void handle(HttpServletRequest request, 
						HttpServletResponse response,
						AccessDeniedException accessDeniedException) throws IOException, ServletException {
		response.sendError(HttpServletResponse.SC_FORBIDDEN, "Petición no autorizada. No tienes permisos para utilizar el recurso");
		
	}
}
