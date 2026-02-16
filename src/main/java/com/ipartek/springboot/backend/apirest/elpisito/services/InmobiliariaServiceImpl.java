package com.ipartek.springboot.backend.apirest.elpisito.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipartek.springboot.backend.apirest.elpisito.entities.Inmobiliaria;
import com.ipartek.springboot.backend.apirest.elpisito.repositories.InmobiliariaRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class InmobiliariaServiceImpl implements GeneralService<Inmobiliaria>{
	
	@Autowired
	private InmobiliariaRepository inmobiliariaRepository;
	
	@Override
	public List<Inmobiliaria> findAll() {
		return inmobiliariaRepository.findAll();
	}

	@Override
	public List<Inmobiliaria> findAllActive() {
		return inmobiliariaRepository.findByActivo(1);
	}

	@Override
	public Inmobiliaria save(Inmobiliaria t) {
		return inmobiliariaRepository.save(t);
	}

	@Override
	public Inmobiliaria findById(Long id) {
		return inmobiliariaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("La inmobiliaria con id " + id + " no existe"));
	}

	@Override
	public Inmobiliaria deleteById(Long id) {
		Inmobiliaria inmobiliaria = inmobiliariaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("La inmobiliaria con id " + id + " no existe"));
		
		inmobiliariaRepository.deleteById(id);
		return inmobiliaria;
	}

}
