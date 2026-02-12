package com.ipartek.springboot.backend.apirest.elpisito.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipartek.springboot.backend.apirest.elpisito.entities.Usuario;
import com.ipartek.springboot.backend.apirest.elpisito.repositories.UsuarioRepository;
import com.ipartek.springboot.backend.apirest.elpisito.utilities.UsuarioMapper;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UsuarioServiceImpl implements GeneralService<Usuario> {
	
	//Una de las principales caracteristicas que puede tener un @Service
	//es que casi todos sus atributos pueden ser repositorios
	
	//UsuarioRepository tiene todos los metodos de JpaRepository (mas los suyos)
	//implementados por HIBERNATE
	//¿Donde ha hecho Hibernate la implementación? En el contexto de Spring
	//En el contexto de Spring, Hibernate ha creado una clase implementadora (una clase anonima)
	//y en esa implementacion ha puesto la logica SQL
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private UsuarioMapper usuarioMapper;

	@Override
	public List<Usuario> findAll() {
		return usuarioRepository.findAll();
	}

	@Override
	public List<Usuario> findAllActive() {
		//¿Esta haciendo la API (Servidor) un trabajo que deberia estar haciendo 
		//la BBDD? Si. ¿Deberia estarlo haciendo? No
		//Estamos sobrecargando el Servidor con una tarea en la que no esta especializado
		//En resumen, un motor de BBDD haria este trabajo de una manera mas rapida y eficiente
		//¿Deberiamos utilizar "Derived Query Methods? Si
		//(Mas a delante aprenderemos a utilizarlos)
		/*return findAll().stream()
		.filter(u -> u.getActivo().equals(1))
		.toList();*/
		
		return usuarioRepository.findByActivo(1);
	}

	@Override
	public Usuario save(Usuario t) {
		//Este metodo recibe un Usuario
		//Si el usuario que recibimos tiene id, 
		//hibernate considera que estamos haciendo un update
		//Si el usario que recibimos no tiene id, hibernate
		//considera que estamos haciendo un create
		//Podemos concluir que el JpaRepository solo tiene
		//un metodo save para ambas tareas
		
		//Aqui llegan todos los datos del Usuario (entre ellos el password sin hashear
		//¿Seria este un buen sitio para hashear el password antes de escribirlo en la BBDD? 
		//Si, este seria el lugar indicado
		
		//Si el usuario llega sin Id es que estoy creando un usuario
		//Si llega con Id estoy modificando 
		t.setPasswordOpen(t.getPassword());
		
		//DataIntegrityViolationException (campos unique)
		return usuarioRepository.save(t);
	}

	@Override
	public Usuario findById(Long id) {
		//El findById recibe un Optional<Usuario>. Si la BBDD devuelve un Optional con Usuario
		//dentro, el metodo devuelve el Usuario. SI dentro del Optional hay null se lanza una exception
		// y el metodo, logicamente, no devuelve nada al controlador
		
		return usuarioRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("El usuario con id " + id + " no existe"));
	}

	@Override
	public Usuario deleteById(Long id) {
		Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("El usuario con id " + id + " que estas intentando eliminar no existe"));
		
		usuarioRepository.deleteById(id);//DataAccessException (EmptyResultDataAccessException que hereda de DataAccessException)
	
		return usuario;
	}
	
	public Usuario completaUsuarioRequestRespetandoNull(Usuario usuarioRequest) {
		Usuario usuarioDB = usuarioRepository.findById(usuarioRequest.getId()).orElseThrow(() -> new EntityNotFoundException("El usuario con id " + usuarioRequest.getId() + " que estas intentando eliminar no existe"));
		
		usuarioMapper.updateUsuarioFromDto(usuarioRequest, usuarioDB);
		
		return usuarioDB;
	}

}

