package com.ipartek.springboot.backend.apirest.elpisito.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipartek.springboot.backend.apirest.elpisito.dtos.ImagenDTO;
import com.ipartek.springboot.backend.apirest.elpisito.dtos.InmobiliariaImagenDTO;
import com.ipartek.springboot.backend.apirest.elpisito.entities.Inmobiliaria;
import com.ipartek.springboot.backend.apirest.elpisito.enumerators.EntidadImagen;
import com.ipartek.springboot.backend.apirest.elpisito.mappers.InmobiliariaMapper;
import com.ipartek.springboot.backend.apirest.elpisito.repositories.InmobiliariaRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class InmobiliariaServiceImpl{
	
	@Autowired
	private InmobiliariaRepository inmobiliariaRepository;
	
	@Autowired
	private ImagenServiceImpl imagenService;
	
	@Autowired
	private InmobiliariaMapper inmobiliariaMapper;
	
	public List<InmobiliariaImagenDTO> findAllBulk(){

		List<Inmobiliaria> inmobiliarias = inmobiliariaRepository.findAll();
		List<Long> ids = inmobiliarias.stream()			
			.map(Inmobiliaria::getId)
			.toList();											
		
		Map<Long, List<ImagenDTO>> mapaImagenes = imagenService.getImagenesPorEntidadBulk(EntidadImagen.INMOBILIARIA, ids);
		
		return inmobiliariaMapper.toDtoBulk(inmobiliarias, mapaImagenes);
	}
	
	public List<InmobiliariaImagenDTO> findAllActiveBulk(){

		List<Inmobiliaria> inmobiliarias = inmobiliariaRepository.findByActivo(1);
		List<Long> ids = inmobiliarias.stream()		
			.map(Inmobiliaria::getId)
			.toList();											
		
		Map<Long, List<ImagenDTO>> mapaImagenes = imagenService.getImagenesPorEntidadBulk(EntidadImagen.INMOBILIARIA, ids); 
		
		return inmobiliariaMapper.toDtoBulk(inmobiliarias, mapaImagenes);
	}
	
	public InmobiliariaImagenDTO save(Inmobiliaria inmobiliaria) {
		Inmobiliaria inmobiliariaSinImagenes = inmobiliariaRepository.save(inmobiliaria);
		return inmobiliariaMapper.toDto(inmobiliariaSinImagenes, imagenService);
	}
	
	public InmobiliariaImagenDTO findById(Long id) {
		Inmobiliaria inmobiliariaSinImagenes = inmobiliariaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("La inmobiliaria con id " + id + " no existe"));
		return inmobiliariaMapper.toDto(inmobiliariaSinImagenes, imagenService);
	}
	
	public Inmobiliaria deleteById(Long id) {
		//Una mejora seria borrar todas las imagenes del inmueble
		Inmobiliaria inmobiliaria = inmobiliariaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("La inmobiliaria con id " + id + " no existe"));
		
		inmobiliariaRepository.deleteById(id);
		return inmobiliaria;
	}

}
