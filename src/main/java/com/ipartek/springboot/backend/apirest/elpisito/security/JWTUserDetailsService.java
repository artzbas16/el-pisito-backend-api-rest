package com.ipartek.springboot.backend.apirest.elpisito.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ipartek.springboot.backend.apirest.elpisito.entities.Usuario;
import com.ipartek.springboot.backend.apirest.elpisito.repositories.UsuarioRepository;

@Service
public class JWTUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//el username que llega al metodo como argumento es el que le llega de la request al controlador
		Usuario usuario = usuarioRepository.findByNombre(username).orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
		
		//User implementa UserDetails (por lo tanto un User es un UserDetails polimorficamente)
		//Ese objeto UserDetails que devuelve el metodo refleja los datos del USUARIO ACTIVO
		return User
				.withUsername(username)
				.password(usuario.getPassword())
				.authorities(usuario.getRol().toString())
				.build();
		
	}
	
	//Spring Security trabaja con autoridades (GrantedAuthority) que por convencion llevan el prefijo ROLE_
	//Si en vez de utilizar en la BBDD ROLE_ADMIN utilizamos ADMIN debemos añadirle ROLE_ para ello tenemos un 
	//metodo .roles
	
	
	
	
}
