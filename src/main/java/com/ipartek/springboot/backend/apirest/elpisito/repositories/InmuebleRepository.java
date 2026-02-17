package com.ipartek.springboot.backend.apirest.elpisito.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ipartek.springboot.backend.apirest.elpisito.entities.Inmueble;
import com.ipartek.springboot.backend.apirest.elpisito.dtos.InmuebleImagenDTO;
import com.ipartek.springboot.backend.apirest.elpisito.entities.Inmobiliaria;
import com.ipartek.springboot.backend.apirest.elpisito.entities.Poblacion;
import com.ipartek.springboot.backend.apirest.elpisito.entities.Operacion;
import com.ipartek.springboot.backend.apirest.elpisito.entities.Tipo;



@Repository
public interface InmuebleRepository extends JpaRepository<Inmueble, Long>{
	List<InmuebleImagenDTO> findByActivo(Integer activo);
	List<InmuebleImagenDTO> findByActivoAndPortada(Integer activo, Integer portada);
	List<InmuebleImagenDTO> findByInmobiliaria(Inmobiliaria inmobiliaria);
	List<InmuebleImagenDTO> findByTipoAndPoblacionAndOperacionAndActivo(Tipo tipo, Poblacion poblacion, Operacion operacion, Integer activo);
}
