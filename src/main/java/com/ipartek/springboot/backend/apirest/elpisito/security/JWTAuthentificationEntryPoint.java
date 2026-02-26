package com.ipartek.springboot.backend.apirest.elpisito.security;

import java.io.IOException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTAuthentificationEntryPoint implements AuthenticationEntryPoint{
	//Recibimos la request (peticion del cliente) e intercepta todos los pasos posteriores en el caso de que
	//se produzca una IOException o una ServletException
	
	//Lo utilizamos para manejar solicitudes no autentificadas
	
	//Spring Security lo invoca cuando
	//1) un usuario no autentificado intenta acceder a una URI que esta protegida
	//2) no se ha proporcionado un token valido (en aplicaciones JWT)
	
	//Su proposito es
	//1) devolver una respuesta adecuada para indicar que el recurso necesita autentificacion
	//por ejemplo en aplicaciones web tradicionales redirigir al usuario a la pagina login (nosotros lo haremos en angular)
	@Override
	public void commence(HttpServletRequest request, 
						HttpServletResponse response,
						AuthenticationException authException) throws IOException, ServletException {

		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Petición no autorizada. EL usuario necesita autentificarse");
		
	}
	
}
