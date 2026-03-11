package com.ipartek.springboot.backend.apirest.elpisito.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipartek.springboot.backend.apirest.elpisito.dtos.ImagenDTO;
import com.ipartek.springboot.backend.apirest.elpisito.dtos.InmuebleImagenDTO;
import com.ipartek.springboot.backend.apirest.elpisito.entities.Inmobiliaria;
import com.ipartek.springboot.backend.apirest.elpisito.entities.Inmueble;
import com.ipartek.springboot.backend.apirest.elpisito.entities.Operacion;
import com.ipartek.springboot.backend.apirest.elpisito.entities.Poblacion;
import com.ipartek.springboot.backend.apirest.elpisito.entities.Tipo;
import com.ipartek.springboot.backend.apirest.elpisito.enumerators.EntidadImagen;
import com.ipartek.springboot.backend.apirest.elpisito.mappers.InmuebleMapper;
import com.ipartek.springboot.backend.apirest.elpisito.repositories.InmobiliariaRepository;
import com.ipartek.springboot.backend.apirest.elpisito.repositories.InmuebleRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class InmuebleServiceImpl{
	
	@Autowired
	private InmuebleRepository inmuebleRepository;
	
	@Autowired
	private ImagenServiceImpl imagenService;
	
	@Autowired
	private InmuebleMapper inmuebleMapper;
	
	@Autowired
	private InmobiliariaRepository inmobiliariaRepository;
	
	@Autowired
	private TipoServiceImpl tipoService;
	
	@Autowired
	private OperacionServiceImpl operacionService;
	
	@Autowired
	private PoblacionServiceImpl poblacionService;
	

	public List<InmuebleImagenDTO> findAllBulk(){
		//Conseguimos todos los inmuebles (findAll)
		List<Inmueble> inmuebles = inmuebleRepository.findAll();
		
		List<InmuebleImagenDTO> dtos = inmuebleMapper.toDtoList(inmuebles); //Un list de inmuebles sin imagenes
		
		//Para crear un bulk necesitamos los id de los inmuebles que esten en dtos
		
		List<Long> inmuebleIds = dtos.stream()
			.map(InmuebleImagenDTO::getId)
			.toList();
		
		List<Long> inmobiliariaIds = dtos.stream()
										.map(dto -> dto.getInmobiliaria().getId())
										.distinct() //porque varios inmuebles puedes pertenecer a la misma inmobiliaria
										.toList();
		
		Map<Long, List<ImagenDTO>> imagenesInmuebleMap = imagenService.getImagenesPorEntidadBulk(EntidadImagen.INMUEBLE, inmuebleIds);
		
		Map<Long, List<ImagenDTO>> imagenesInmobiliariaMap = imagenService.getImagenesPorEntidadBulk(EntidadImagen.INMOBILIARIA, inmobiliariaIds);
		
		for(InmuebleImagenDTO dto: dtos) {
			dto.setImagenes(imagenesInmuebleMap.getOrDefault(dto.getId(), List.of()));
			dto.getInmobiliaria().setImagenes(imagenesInmobiliariaMap.getOrDefault(dto.getInmobiliaria().getId(), List.of()));
		}
		
		return dtos;
	}
	
	public List<InmuebleImagenDTO> findAllActiveBulk(){

		List<Inmueble> inmuebles = inmuebleRepository.findByActivo(1);

		List<InmuebleImagenDTO> dtos = inmuebleMapper.toDtoList(inmuebles); //Un list de inmuebles sin imagenes
		
		//Para crear un bulk necesitamos los id de los inmuebles que esten en dtos
		
		List<Long> inmuebleIds = dtos.stream()
			.map(InmuebleImagenDTO::getId)
			.toList();
		
		List<Long> inmobiliariaIds = dtos.stream()
										.map(dto -> dto.getInmobiliaria().getId())
										.distinct() //porque varios inmuebles puedes pertenecer a la misma inmobiliaria
										.toList();
		
		Map<Long, List<ImagenDTO>> imagenesInmuebleMap = imagenService.getImagenesPorEntidadBulk(EntidadImagen.INMUEBLE, inmuebleIds);
		
		Map<Long, List<ImagenDTO>> imagenesInmobiliariaMap = imagenService.getImagenesPorEntidadBulk(EntidadImagen.INMOBILIARIA, inmobiliariaIds);
		
		for(InmuebleImagenDTO dto: dtos) {
			dto.setImagenes(imagenesInmuebleMap.getOrDefault(dto.getId(), List.of()));
			dto.getInmobiliaria().setImagenes(imagenesInmobiliariaMap.getOrDefault(dto.getInmobiliaria().getId(), List.of()));
		}
		
		return dtos;
	}
	
	public List<InmuebleImagenDTO> findAllPortadaBulk(){

		List<Inmueble> inmuebles = inmuebleRepository.findByActivoAndPortada(1, 1);

		List<InmuebleImagenDTO> dtos = inmuebleMapper.toDtoList(inmuebles); //Un list de inmuebles sin imagenes
		
		//Para crear un bulk necesitamos los id de los inmuebles que esten en dtos
		
		List<Long> inmuebleIds = dtos.stream()
			.map(InmuebleImagenDTO::getId)
			.toList();
		
		List<Long> inmobiliariaIds = dtos.stream()
										.map(dto -> dto.getInmobiliaria().getId())
										.distinct() //porque varios inmuebles puedes pertenecer a la misma inmobiliaria
										.toList();
		
		Map<Long, List<ImagenDTO>> imagenesInmuebleMap = imagenService.getImagenesPorEntidadBulk(EntidadImagen.INMUEBLE, inmuebleIds);
		
		Map<Long, List<ImagenDTO>> imagenesInmobiliariaMap = imagenService.getImagenesPorEntidadBulk(EntidadImagen.INMOBILIARIA, inmobiliariaIds);
		
		for(InmuebleImagenDTO dto: dtos) {
			dto.setImagenes(imagenesInmuebleMap.getOrDefault(dto.getId(), List.of()));
			dto.getInmobiliaria().setImagenes(imagenesInmobiliariaMap.getOrDefault(dto.getInmobiliaria().getId(), List.of()));
		}
		
		return dtos;
		
	}
	
	public List<InmuebleImagenDTO> findInmueblesInmobiliariaBulk(Long id){
		
		Inmobiliaria inmobiliaria = inmobiliariaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("La inmobiliaria con id " + id + " no existe"));

		List<Inmueble> inmuebles = inmuebleRepository.findByInmobiliaria(inmobiliaria);

		List<InmuebleImagenDTO> dtos = inmuebleMapper.toDtoList(inmuebles); //Un list de inmuebles sin imagenes
		
		//Para crear un bulk necesitamos los id de los inmuebles que esten en dtos
		
		List<Long> inmuebleIds = dtos.stream()
			.map(InmuebleImagenDTO::getId)
			.toList();
		
		List<Long> inmobiliariaIds = dtos.stream()
										.map(dto -> dto.getInmobiliaria().getId())
										.distinct() //porque varios inmuebles puedes pertenecer a la misma inmobiliaria
										.toList();
		
		Map<Long, List<ImagenDTO>> imagenesInmuebleMap = imagenService.getImagenesPorEntidadBulk(EntidadImagen.INMUEBLE, inmuebleIds);
		
		Map<Long, List<ImagenDTO>> imagenesInmobiliariaMap = imagenService.getImagenesPorEntidadBulk(EntidadImagen.INMOBILIARIA, inmobiliariaIds);
		
		for(InmuebleImagenDTO dto: dtos) {
			dto.setImagenes(imagenesInmuebleMap.getOrDefault(dto.getId(), List.of()));
			dto.getInmobiliaria().setImagenes(imagenesInmobiliariaMap.getOrDefault(dto.getInmobiliaria().getId(), List.of()));
		}
		
		return dtos;
		
	}
	
	public List<InmuebleImagenDTO> finderBulk(Long tipoId, Long poblacionId, Long operacionId){
		
		Tipo tipo = tipoService.findById(tipoId);
		
		Poblacion poblacion = poblacionService.findById(poblacionId);
		
		Operacion operacion = operacionService.findById(operacionId);
		
		List<Inmueble> inmuebles = inmuebleRepository.findByTipoAndPoblacionAndOperacionAndActivo(tipo, poblacion, operacion, 1);

		List<InmuebleImagenDTO> dtos = inmuebleMapper.toDtoList(inmuebles); //Un list de inmuebles sin imagenes
		
		//Para crear un bulk necesitamos los id de los inmuebles que esten en dtos
		
		List<Long> inmuebleIds = dtos.stream()
			.map(InmuebleImagenDTO::getId)
			.toList();
		
		List<Long> inmobiliariaIds = dtos.stream()
										.map(dto -> dto.getInmobiliaria().getId())
										.distinct() //porque varios inmuebles puedes pertenecer a la misma inmobiliaria
										.toList();
		
		Map<Long, List<ImagenDTO>> imagenesInmuebleMap = imagenService.getImagenesPorEntidadBulk(EntidadImagen.INMUEBLE, inmuebleIds);
		
		Map<Long, List<ImagenDTO>> imagenesInmobiliariaMap = imagenService.getImagenesPorEntidadBulk(EntidadImagen.INMOBILIARIA, inmobiliariaIds);
		
		for(InmuebleImagenDTO dto: dtos) {
			dto.setImagenes(imagenesInmuebleMap.getOrDefault(dto.getId(), List.of()));
			dto.getInmobiliaria().setImagenes(imagenesInmobiliariaMap.getOrDefault(dto.getInmobiliaria().getId(), List.of()));
		}
		
		return dtos;
	}
	
	public InmuebleImagenDTO save(Inmueble inmueble) {
		Inmueble inmuebleSinImagenes = inmuebleRepository.save(inmueble);
		
		InmuebleImagenDTO elInmueble = inmuebleMapper.toDto(inmuebleSinImagenes);
		
		List<ImagenDTO> imagenesInmueble = imagenService.getImagenes(EntidadImagen.INMUEBLE, elInmueble.getId());
		
		List<ImagenDTO> imagenesInmobiliaria = imagenService.getImagenes(EntidadImagen.INMOBILIARIA, elInmueble.getInmobiliaria().getId());
		
		elInmueble.setImagenes(imagenesInmueble);
		elInmueble.getInmobiliaria().setImagenes(imagenesInmobiliaria);
		
		return elInmueble;
	}
	
	public InmuebleImagenDTO findById(Long id) {
		Inmueble inmuebleSinImagenes = inmuebleRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("El inmueble con id " + id + " no existe"));

		InmuebleImagenDTO elInmueble = inmuebleMapper.toDto(inmuebleSinImagenes);
		
		List<ImagenDTO> imagenesInmueble = imagenService.getImagenes(EntidadImagen.INMUEBLE, elInmueble.getId());
		
		List<ImagenDTO> imagenesInmobiliaria = imagenService.getImagenes(EntidadImagen.INMOBILIARIA, elInmueble.getInmobiliaria().getId());
		
		elInmueble.setImagenes(imagenesInmueble);
		elInmueble.getInmobiliaria().setImagenes(imagenesInmobiliaria);
		
		return elInmueble;
	}
	
	public InmuebleImagenDTO deleteById(Long id) {
		//Una mejora seria borrar todas las imagenes del inmueble
		Inmueble inmueble = inmuebleRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("El inmueble con id " + id + " no existe"));
		
		inmuebleRepository.deleteById(id);

		InmuebleImagenDTO elInmueble = inmuebleMapper.toDto(inmueble);
		
		List<ImagenDTO> imagenesInmueble = imagenService.getImagenes(EntidadImagen.INMUEBLE, elInmueble.getId());
		
		List<ImagenDTO> imagenesInmobiliaria = imagenService.getImagenes(EntidadImagen.INMOBILIARIA, elInmueble.getInmobiliaria().getId());
		
		elInmueble.setImagenes(imagenesInmueble);
		elInmueble.getInmobiliaria().setImagenes(imagenesInmobiliaria);
		
		return elInmueble;
	}	
	

}
