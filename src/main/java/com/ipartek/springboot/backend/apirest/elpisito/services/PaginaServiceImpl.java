package com.ipartek.springboot.backend.apirest.elpisito.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipartek.springboot.backend.apirest.elpisito.entities.Pagina;
import com.ipartek.springboot.backend.apirest.elpisito.repositories.PaginaRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PaginaServiceImpl implements GeneralService<Pagina>{
	
	@Autowired
	private PaginaRepository paginaRepository;
	
	@Override
	public List<Pagina> findAll() {
		return paginaRepository.findAll();
	}

	@Override
	public List<Pagina> findAllActive() {
		return paginaRepository.findByActivo(1);
	}

	@Override
	public Pagina save(Pagina t) {
		return paginaRepository.save(t);
	}

	@Override
	public Pagina findById(Long id) {
		return paginaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("La página con id " + id + " no existe"));
	}

	@Override
	public Pagina deleteById(Long id) {
		Pagina pagina = paginaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("La página con id " + id + " no existe"));
		
		paginaRepository.deleteById(id);
		return pagina;
	}

}
