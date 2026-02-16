package com.ipartek.springboot.backend.apirest.elpisito.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipartek.springboot.backend.apirest.elpisito.entities.Tematica;
import com.ipartek.springboot.backend.apirest.elpisito.repositories.TematicaRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TematicaServiceImpl implements GeneralService<Tematica>{
	
	@Autowired
	private TematicaRepository tematicaRepository;

	@Override
	public List<Tematica> findAll() {
		return tematicaRepository.findAll();
	}

	@Override
	public List<Tematica> findAllActive() {
		return tematicaRepository.findByActivo(1);
	}

	@Override
	public Tematica save(Tematica t) {
		return tematicaRepository.save(t);
	}

	@Override
	public Tematica findById(Long id) {
		return tematicaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("La temática con id " + id + " no existe"));
	}

	@Override
	public Tematica deleteById(Long id) {
		Tematica tematica =tematicaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("La temática con id " + id + " no existe"));
		tematicaRepository.deleteById(id);
		return tematica;
	}
	
	
	
}
