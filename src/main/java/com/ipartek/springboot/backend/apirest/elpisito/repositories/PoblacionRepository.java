package com.ipartek.springboot.backend.apirest.elpisito.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ipartek.springboot.backend.apirest.elpisito.entities.Poblacion;

@Repository
public interface PoblacionRepository extends JpaRepository<Poblacion, Long>{
	
	List<Poblacion> findByActivo(Integer activo);
	
}
