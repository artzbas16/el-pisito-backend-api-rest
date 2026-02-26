package com.ipartek.springboot.backend.apirest.elpisito.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ipartek.springboot.backend.apirest.elpisito.entities.Usuario;
import com.ipartek.springboot.backend.apirest.elpisito.repositories.UsuarioRepository;

@RestController
@RequestMapping("/api/auth")
public class AuthRestController {
	
	@Autowired AuthenticationManager authenticationManager;
	
	@Autowired JWTService jwtService;
	
	@Autowired UsuarioRepository usuarioRepository;
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody JWTRequest request){
		
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
		
		Authentication auth = authenticationManager.authenticate(
													new UsernamePasswordAuthenticationToken(request.username(), request.password())			
													);
		Usuario usuario = usuarioRepository.findByNombre(request.username()).orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
		
		String token = jwtService.generateToken(usuario);
		
		return null;
		
	}
	
	

}
