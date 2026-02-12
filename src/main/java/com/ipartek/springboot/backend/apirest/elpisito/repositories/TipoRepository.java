package com.ipartek.springboot.backend.apirest.elpisito.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ipartek.springboot.backend.apirest.elpisito.entities.Tipo;

@Repository
public interface TipoRepository extends JpaRepository<Tipo, Long>{
	List<Tipo> findByActivo(Integer activo);
}
