package com.ipartek.springboot.backend.apirest.elpisito.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ipartek.springboot.backend.apirest.elpisito.dtos.UsuarioDTO;
import com.ipartek.springboot.backend.apirest.elpisito.entities.Usuario;
import com.ipartek.springboot.backend.apirest.elpisito.mappers.UsuarioMapper;
import com.ipartek.springboot.backend.apirest.elpisito.repositories.UsuarioRepository;

@RestController
@RequestMapping("/api/auth")
public class AuthRestController {
	
	@Autowired AuthenticationManager authenticationManager;
	
	@Autowired JWTService jwtService;
	
	@Autowired UsuarioRepository usuarioRepository;
	
	@Autowired UsuarioMapper usuarioMapper;
	
	@PostMapping("/login")
	public ResponseEntity<UsuarioDTO> login(@RequestBody JWTRequest request){
		
		//Aunque parezca que la variable auth no se usa, en realidad cumbre dos funciones muy importantes...
		//1) Hace que Spring Security:
		//	- llame a tu UserDetailService.loadUserByUserName(username)
		//	- compara la contraseña que tiene el usuario en la BBDD con el password recibido
		//	- verifica si el usuario existe
		//	- si falla lanza la excepcion (BadCredentialsException)
		//	- si todo va bien devuelve un objeto Authentication autentificado
		//	- En resumen si las credenciales son incorrectas nunca se llega a generar un JWT
		//	- por lo tanto, aunque no usemos auth directamente en este metodo, este objeto ya contiene
		//	- la info de si el usuario es valido o no
		//2) Contiene la informacion de autentificacion del usuario
		//¿Que datos contiene?
		//auth.getPrincipal()	Es el objeto UserDetails
		//auth.getAuthorities() Roles
		//auth.isAuthentificated() Boolean
		//3) Spring Security lo guarda en el Security Context
		//SUPER RESUMEN: el objeto auth es el resultado oficial del login
		
		@SuppressWarnings("unused")
		Authentication auth = authenticationManager.authenticate(
													new UsernamePasswordAuthenticationToken(request.username(), request.password())			
													);
		//Aquí llegamos si todo ha ido bien... si ha ido mal se ha lanzado una BadCredentialsException
		Usuario usuario = usuarioRepository.findByNombre(request.username()).orElseThrow();
		
		String token = jwtService.generateToken(usuario);
		
		//Vamos a crear una Cookie donde albergamos el token para mandarlo a Cliente
		//.httpOnly(true) vital para que el cookie no sea legible en cliente
		ResponseCookie accesCookie = ResponseCookie.from("access_token", token)
												.httpOnly(true)
												.secure(true)
												.sameSite("None")
												.path("/")
												.build();
		
		//Map<String, String> response = new HashMap<>();
		//response.put("access token", token); //lo mandamos para verlo... no se debe enviar...
		//response.put("mensaje", "Login OK");
		
		
		return ResponseEntity.ok()
				.header(HttpHeaders.SET_COOKIE, accesCookie.toString())
				.body(usuarioMapper.toDto(usuario));
		
	}
	
	@GetMapping("/logout")
	public ResponseEntity<JWTResponse> logout(){
		
		//Los datos del cookie de borrado tienen que coincidir con los datos del cookie creado (accessCookie)
		ResponseCookie deleteAccess = ResponseCookie.from("access_token", "")
													.httpOnly(true)
													.secure(true)
													.sameSite("None")
													.path("/")
													.maxAge(0)
													.build();
		
		Map<String, String> response = new HashMap<>();
		response.put("mensaje", "Logout OK");
		
		return ResponseEntity.ok()
				.header(HttpHeaders.SET_COOKIE, deleteAccess.toString())
				.body(new JWTResponse(response));
	}
	
	

}
