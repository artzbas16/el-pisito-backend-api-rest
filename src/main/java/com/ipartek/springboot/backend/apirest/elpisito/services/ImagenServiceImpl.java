package com.ipartek.springboot.backend.apirest.elpisito.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipartek.springboot.backend.apirest.elpisito.dtos.ImagenDTO;
import com.ipartek.springboot.backend.apirest.elpisito.entities.Imagen;
import com.ipartek.springboot.backend.apirest.elpisito.repositories.ImagenRepository;
import com.ipartek.springboot.backend.apirest.elpisito.utilities.EntidadImagen;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ImagenServiceImpl{
	
	@Autowired
	private ImagenRepository imagenRepository;
	
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
		return new ImagenDTO(imagen.getId(), url, imagen.getAltImagen());
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
