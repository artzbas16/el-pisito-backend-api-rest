package com.ipartek.springboot.backend.apirest.elpisito.services;


import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipartek.springboot.backend.apirest.elpisito.dtos.ImagenDTO;
import com.ipartek.springboot.backend.apirest.elpisito.entities.Imagen;
import com.ipartek.springboot.backend.apirest.elpisito.enumerators.EntidadImagen;
import com.ipartek.springboot.backend.apirest.elpisito.mappers.ImagenMapper;
import com.ipartek.springboot.backend.apirest.elpisito.repositories.ImagenRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ImagenServiceImpl{
	
	@Autowired
	private ImagenRepository imagenRepository;
	
	@Autowired
	private ImagenMapper imagenMapper;
	
	//OBTENER IMAGENES POR ENTIDAD (BULK)
	//Este metodo es universal para todas las entidades que tienen imagen (representadas
	//en el enumerador EntidadImagen)
	//Con una sola llamada a la BBDD vamos a obtener un Map con el id de la entidad
	//(Inmueble, Banner,...cualquier entidad que tenga imagen o imagenes)
	//y su correspondiente listado de imagenes
	//Entrada: entidad --> por ejemplo EntidadImagen.INMUEBLE
	//ids --> lista de ids, de inmueble, banner, banner carousel, inmobiliaria,...
	//Salida: un Map donde cada id de EntidadImagen tiene su propia lista de ImagenDTO
	//Objetivo: Sacar todas las imagenes de todos los ids en una sola query y evitar
	//(n+1) queries ---> EFICIENCIA
	//Se invoca desde los servicios de las entidades (entidades que tienen imagen se entiende)
	//por ejemplo desde InmuebleServiceImpl 
	
	public Map<Long, List<ImagenDTO>> getImagenesPorENtidadBulk(EntidadImagen entidad, List<Long> ids){
		//Conseguimos todas las imagenes de una entidad
		List<Imagen> imagenes = imagenRepository.findByEntidadImagenAndEntidadIdIn(entidad, ids);
		
		//Conseguimos las imagenesDTO de la entidad
		List<ImagenDTO> imagenesDTO = imagenMapper.tDtoList(imagenes);
		
		return imagenesDTO.stream()
			.collect(Collectors.groupingBy(ImagenDTO::entidadId)); //ImagenDTO es un record, por lo tanto, no es getEntidadId sino entidadId

	}
	
	//OBTENER IMAGENES
	public List<ImagenDTO> getImagenes(EntidadImagen tipoEntidad, Long entidadId) {
		return imagenRepository.findByEntidadImagenAndEntidadId(tipoEntidad, entidadId).stream()
			.map(i -> this.toDTO(i))
			.toList();
	}
	
	//OBTENER UNA UNICA IMAGEN (LA PRIMERA)
	public ImagenDTO getUnicaImagen(EntidadImagen tipoEntidad, Long entidadId) {
		Imagen imagen = imagenRepository.findFirstByEntidadImagenAndEntidadId(tipoEntidad, entidadId).orElseThrow(() -> new EntityNotFoundException("La imagen con id " + entidadId + " perteneciente al tipo de entidad " + tipoEntidad.name().toLowerCase() +" no existe"));
		return this.toDTO(imagen);
	}
	
	//CONVERTIR IMAGEN EN IMAGENDTO
	private ImagenDTO toDTO(Imagen imagen) {
		String url = "/api/imagenes/" + imagen.getEntidadImagen().name().toLowerCase()+"/"+imagen.getEntidadId()+"/"+imagen.getNombre();
		return new ImagenDTO(imagen.getId(), url, imagen.getAltImagen(), imagen.getEntidadId());
	}
	

	public ImagenDTO save(Imagen t) {
		return this.toDTO(imagenRepository.save(t));
	}

	public Imagen findById(Long id) {
		return imagenRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("La imagen con id " + id + " no existe"));
	}

	public ImagenDTO deleteById(Long id) {
		Imagen imagen = imagenRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("La imagen con id " + id + " no existe"));
		imagenRepository.deleteById(id);
		return this.toDTO(imagen);
	}

}
