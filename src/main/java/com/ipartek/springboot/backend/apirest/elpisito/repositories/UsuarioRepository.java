package com.ipartek.springboot.backend.apirest.elpisito.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ipartek.springboot.backend.apirest.elpisito.entities.Usuario;

//Cuando Spring detecta que una interface extiende de JpaRepository 
//interpreta que es un @Repository. Por lo tanto no es obligatorio
//crear la anotacion @Repository
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	//Cuando necesitemos que Hibernate implemente metodos que no esten en el JpaRepository
	//(metodos personalizados de nuestra aplicación) podemos utilizar los DELIVERED QUERY METHODS
	//El secreto de su utilización está en el nombre del método (cuando utilicemos DERIVED
	//QUERY METHODS) no podemos poner a los métodos cualquier nombre. Deben seguir unas pautas
	//https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html
	
	List<Usuario> findByActivo(Integer activo); //Este metodo lo implementa Hibernate siguiendo las pautas 
	
}
