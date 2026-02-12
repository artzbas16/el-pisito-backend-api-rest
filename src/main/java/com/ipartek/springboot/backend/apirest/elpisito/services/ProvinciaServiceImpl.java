package com.ipartek.springboot.backend.apirest.elpisito.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipartek.springboot.backend.apirest.elpisito.entities.Provincia;
import com.ipartek.springboot.backend.apirest.elpisito.repositories.ProvinciaRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProvinciaServiceImpl implements GeneralService<Provincia>{
	
	@Autowired
	private ProvinciaRepository provinciaRepository;
	
	@Override
	public List<Provincia> findAll() {
		return provinciaRepository.findAll();
	}

	@Override
	public List<Provincia> findAllActive() {
		return provinciaRepository.findByActivo(1);
	}

	@Override
	public Provincia save(Provincia t) {
		return provinciaRepository.save(t);
	}

	@Override
	public Provincia findById(Long id) {
		return provinciaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("La provincia con id " + id + " no existe"));
	}

	@Override
	public Provincia deleteById(Long id) {
		Provincia provincia = provinciaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("La provincia con id " + id + " que estas intentando eliminar no existe"));
		
		provinciaRepository.deleteById(id);//DataAccessException (EmptyResultDataAccessException que hereda de DataAccessException)
	
		return provincia;
	}

}
