package com.ipartek.springboot.backend.apirest.elpisito.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipartek.springboot.backend.apirest.elpisito.entities.Tipo;
import com.ipartek.springboot.backend.apirest.elpisito.repositories.TipoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TipoServiceImpl implements GeneralService<Tipo>{
	
	@Autowired
	private TipoRepository tipoRepository;

	@Override
	public List<Tipo> findAll() {
		return tipoRepository.findAll();
	}

	@Override
	public List<Tipo> findAllActive() {
		return tipoRepository.findByActivo(1);
	}

	@Override
	public Tipo save(Tipo t) {
		return tipoRepository.save(t);
	}

	@Override
	public Tipo findById(Long id) {
		return tipoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("El tipo con id " + id + " no existe"));
	}

	@Override
	public Tipo deleteById(Long id) {
		Tipo tipo = tipoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("El tipo con id " + id + " no existe"));
		
		tipoRepository.deleteById(id);
		return tipo;
	}
	
}
