package com.ipartek.springboot.backend.apirest.elpisito.services;

import java.util.List;

public interface GeneralService<T> {
	
	//Creamos unos metodos abstractos BASADOS en el JpaRepository (por arquitectura)
	//Podemos elegir los que nos interesen (no hace falta crear todos)
	//Tambien podemos añadir metodos personalizados (es decir que no esten en el JpaRepository)
	
	List<T> findAll();
	List<T> findAllActive();
	T save(T t);
	T findById(Long id);
	T deleteById(Long id);
	

}