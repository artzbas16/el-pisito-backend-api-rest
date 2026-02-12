package com.ipartek.springboot.backend.apirest.elpisito.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ipartek.springboot.backend.apirest.elpisito.entities.Provincia;

@Repository
public interface ProvinciaRepository extends JpaRepository<Provincia, Long>{
	List<Provincia> findByActivo(Integer activo);
}
