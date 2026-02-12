package com.ipartek.springboot.backend.apirest.elpisito.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipartek.springboot.backend.apirest.elpisito.entities.Operacion;
import com.ipartek.springboot.backend.apirest.elpisito.repositories.OperacionRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class OperacionServiceImpl implements GeneralService<Operacion>{
	
	@Autowired
	private OperacionRepository operacionRepository;
	
	@Override
	public List<Operacion> findAll() {
		return operacionRepository.findAll();
	}

	@Override
	public List<Operacion> findAllActive() {
		return operacionRepository.findByActivo(1);
	}

	@Override
	public Operacion save(Operacion t) {
		return operacionRepository.save(t);
	}

	@Override
	public Operacion findById(Long id) {
		return operacionRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("La operación con id " + id + " no existe"));
	}

	@Override
	public Operacion deleteById(Long id) {
		Operacion operacion = operacionRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("La operación con id " + id + " no existe"));
		
		operacionRepository.deleteById(id);
		return operacion;
	}

}
