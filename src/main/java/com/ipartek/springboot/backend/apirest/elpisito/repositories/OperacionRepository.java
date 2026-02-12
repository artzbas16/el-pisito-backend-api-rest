package com.ipartek.springboot.backend.apirest.elpisito.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ipartek.springboot.backend.apirest.elpisito.entities.Operacion;

@Repository
public interface OperacionRepository extends JpaRepository<Operacion,Long>{
	List<Operacion> findByActivo(Integer activo);
}
