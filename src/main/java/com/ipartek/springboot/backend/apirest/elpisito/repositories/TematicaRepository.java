package com.ipartek.springboot.backend.apirest.elpisito.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ipartek.springboot.backend.apirest.elpisito.entities.Tematica;

@Repository
public interface TematicaRepository extends JpaRepository<Tematica, Long>{
	List<Tematica> findByActivo(Integer activo);
}
