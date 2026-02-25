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
		List<Inmueble> inmuebles = inmuebleRepository.findAll();//1) Conseguimos todos los muebles (findAll)	
		List<Long> ids = inmuebles.stream() //2)Extrae los ids de todos los inmuebles			
			.map(Inmueble::getId)
			.toList();											
		
		Map<Long, List<ImagenDTO>> mapaImagenes = imagenService.getImagenesPorEntidadBulk(EntidadImagen.INMUEBLE, ids); //3)Llamamos imagenService para traer todos los ids de una vez
		//4) Tenemos un Map con unos ids asociados a su list de imagenes (en este caso todos los INMUEBLES)
		
		//5) Pasamos la lista de inmuebles y el mapa de imagenes a inmuebleMapper.toDtoBulk en InmuebleMapper
		return inmuebleMapper.toDtoBulk(inmuebles, mapaImagenes);//toDtoBulk() combina los dtos con las imagenes 
		//y devuelve la lista final de inmuebles con sus imagenes
	}
	
	public List<InmuebleImagenDTO> findAllActiveBulk(){

		List<Inmueble> inmuebles = inmuebleRepository.findByActivo(1);
		List<Long> ids = inmuebles.stream()		
			.map(Inmueble::getId)
			.toList();											
		
		Map<Long, List<ImagenDTO>> mapaImagenes = imagenService.getImagenesPorEntidadBulk(EntidadImagen.INMUEBLE, ids); 
		
		return inmuebleMapper.toDtoBulk(inmuebles, mapaImagenes);
	}
	
	public List<InmuebleImagenDTO> findAllPortadaBulk(){

		List<Inmueble> inmuebles = inmuebleRepository.findByActivoAndPortada(1, 1);
		List<Long> ids = inmuebles.stream()		
			.map(Inmueble::getId)
			.toList();											
		
		Map<Long, List<ImagenDTO>> mapaImagenes = imagenService.getImagenesPorEntidadBulk(EntidadImagen.INMUEBLE, ids); 
		
		return inmuebleMapper.toDtoBulk(inmuebles, mapaImagenes);
	}
	
	public List<InmuebleImagenDTO> findInmueblesInmobiliariaBulk(Long id){
		
		Inmobiliaria inmobiliaria = inmobiliariaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("La inmobiliaria con id " + id + " no existe"));

		List<Inmueble> inmuebles = inmuebleRepository.findByInmobiliaria(inmobiliaria);
		List<Long> ids = inmuebles.stream()		
			.map(Inmueble::getId)
			.toList();											
		
		Map<Long, List<ImagenDTO>> mapaImagenes = imagenService.getImagenesPorEntidadBulk(EntidadImagen.INMUEBLE, ids); 
		
		return inmuebleMapper.toDtoBulk(inmuebles, mapaImagenes);
	}
	
	public List<InmuebleImagenDTO> finderBulk(Long tipoId, Long poblacionId, Long operacionId){
		
		Tipo tipo = tipoService.findById(tipoId);
		
		Poblacion poblacion = poblacionService.findById(poblacionId);
		
		Operacion operacion = operacionService.findById(operacionId);
		
		List<Inmueble> inmuebles = inmuebleRepository.findByTipoAndPoblacionAndOperacionAndActivo(tipo, poblacion, operacion, 1);

		List<Long> ids = inmuebles.stream()		
			.map(Inmueble::getId)
			.toList();											
		
		Map<Long, List<ImagenDTO>> mapaImagenes = imagenService.getImagenesPorEntidadBulk(EntidadImagen.INMUEBLE, ids); 
		
		return inmuebleMapper.toDtoBulk(inmuebles, mapaImagenes);
	}
	
	public InmuebleImagenDTO save(Inmueble inmueble) {
		Inmueble inmuebleSinImagenes = inmuebleRepository.save(inmueble);
		return inmuebleMapper.toDto(inmuebleSinImagenes, imagenService);
	}
	
	public InmuebleImagenDTO findById(Long id) {
		Inmueble inmuebleSinImagenes = inmuebleRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("El inmueble con id " + id + " no existe"));
		return inmuebleMapper.toDto(inmuebleSinImagenes, imagenService);
	}
	
	public InmuebleImagenDTO deleteById(Long id) {
		//Una mejora seria borrar todas las imagenes del inmueble
		Inmueble inmueble = inmuebleRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("El inmueble con id " + id + " no existe"));
		
		inmuebleRepository.deleteById(id);
		return inmuebleMapper.toDto(inmueble, imagenService);
	}	
	

}
