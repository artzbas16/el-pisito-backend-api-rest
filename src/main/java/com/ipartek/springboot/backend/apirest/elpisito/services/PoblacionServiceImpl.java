package com.ipartek.springboot.backend.apirest.elpisito.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipartek.springboot.backend.apirest.elpisito.entities.Poblacion;
import com.ipartek.springboot.backend.apirest.elpisito.repositories.PoblacionRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PoblacionServiceImpl implements GeneralService<Poblacion>{
	
	@Autowired
	private PoblacionRepository poblacionRepository;

	@Override
	public List<Poblacion> findAll() {
		return poblacionRepository.findAll();
	}

	@Override
	public List<Poblacion> findAllActive() {
		return poblacionRepository.findByActivo(1);
	}

	@Override
	public Poblacion save(Poblacion t) {
		return poblacionRepository.save(t);
	}

	@Override
	public Poblacion findById(Long id) {
		return poblacionRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("La población con id " + id + " no existe"));
	}

	@Override
	public Poblacion deleteById(Long id) {
		Poblacion poblacion = poblacionRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("La población con id " + id + " que estas intentando eliminar no existe"));
		
		poblacionRepository.deleteById(id);//DataAccessException (EmptyResultDataAccessException que hereda de DataAccessException)
	
		return poblacion;
	}

}
