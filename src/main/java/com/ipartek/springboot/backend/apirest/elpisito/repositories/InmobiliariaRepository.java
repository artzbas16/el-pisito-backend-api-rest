package com.ipartek.springboot.backend.apirest.elpisito.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ipartek.springboot.backend.apirest.elpisito.entities.Inmobiliaria;

@Repository
public interface InmobiliariaRepository extends JpaRepository<Inmobiliaria, Long> {
	List<Inmobiliaria> findByActivo(Integer activo);
}
