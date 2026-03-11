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

		List<InmobiliariaImagenDTO> dtos = inmobiliariaMapper.toDtoList(inmobiliarias); //Un list de inmuebles sin imagenes
		
		//Para crear un bulk necesitamos los id de los inmuebles que esten en dtos
		
		List<Long> inmobiliariaIds = dtos.stream()
			.map(InmobiliariaImagenDTO::getId)
			.toList();
		
		Map<Long, List<ImagenDTO>> imagenesInmobiliariaMap = imagenService.getImagenesPorEntidadBulk(EntidadImagen.INMOBILIARIA, inmobiliariaIds);
		
		for(InmobiliariaImagenDTO dto: dtos) {
			dto.setImagenes(imagenesInmobiliariaMap.getOrDefault(dto.getId(), List.of()));
		}
		
		return dtos;
	}
	
	public List<InmobiliariaImagenDTO> findAllActiveBulk(){

		List<Inmobiliaria> inmobiliarias = inmobiliariaRepository.findByActivo(1);

		List<InmobiliariaImagenDTO> dtos = inmobiliariaMapper.toDtoList(inmobiliarias); //Un list de inmuebles sin imagenes
		
		//Para crear un bulk necesitamos los id de los inmuebles que esten en dtos
		
		List<Long> inmobiliariaIds = dtos.stream()
			.map(InmobiliariaImagenDTO::getId)
			.toList();
		
		Map<Long, List<ImagenDTO>> imagenesInmobiliariaMap = imagenService.getImagenesPorEntidadBulk(EntidadImagen.INMOBILIARIA, inmobiliariaIds);
		
		for(InmobiliariaImagenDTO dto: dtos) {
			dto.setImagenes(imagenesInmobiliariaMap.getOrDefault(dto.getId(), List.of()));
		}
		
		return dtos;
		
	}
	
	public InmobiliariaImagenDTO save(Inmobiliaria inmobiliaria) {
		Inmobiliaria inmobiliariaSinImagenes = inmobiliariaRepository.save(inmobiliaria);
		
		InmobiliariaImagenDTO laInmobiliaria = inmobiliariaMapper.toDto(inmobiliariaSinImagenes);
		
		List<ImagenDTO> imagenesInmobiliaria = imagenService.getImagenes(EntidadImagen.INMOBILIARIA, laInmobiliaria.getId());
		
		laInmobiliaria.setImagenes(imagenesInmobiliaria);
		return laInmobiliaria;
	}
	
	public InmobiliariaImagenDTO findById(Long id) {
		Inmobiliaria inmobiliariaSinImagenes = inmobiliariaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("La inmobiliaria con id " + id + " no existe"));
		InmobiliariaImagenDTO laInmobiliaria = inmobiliariaMapper.toDto(inmobiliariaSinImagenes);
		
		List<ImagenDTO> imagenesInmobiliaria = imagenService.getImagenes(EntidadImagen.INMOBILIARIA, laInmobiliaria.getId());
		
		laInmobiliaria.setImagenes(imagenesInmobiliaria);
		return laInmobiliaria;
	}
	
	public Inmobiliaria deleteById(Long id) {
		//Una mejora seria borrar todas las imagenes del inmueble
		Inmobiliaria inmobiliaria = inmobiliariaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("La inmobiliaria con id " + id + " no existe"));
		
		inmobiliariaRepository.deleteById(id);
		return inmobiliaria;
	}

}
