package com.ipartek.springboot.backend.apirest.elpisito.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ipartek.springboot.backend.apirest.elpisito.entities.Pagina;

@Repository
public interface PaginaRepository extends JpaRepository<Pagina, Long> {
	List<Pagina> findByActivo(Integer activo);
}
