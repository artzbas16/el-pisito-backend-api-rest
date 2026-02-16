package com.ipartek.springboot.backend.apirest.elpisito.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ipartek.springboot.backend.apirest.elpisito.dtos.ImagenDTO;
import com.ipartek.springboot.backend.apirest.elpisito.entities.Imagen;
import com.ipartek.springboot.backend.apirest.elpisito.enumerators.EntidadImagen;
import com.ipartek.springboot.backend.apirest.elpisito.services.ImagenServiceImpl;

@RestController
@RequestMapping("/api")
public class ImagenRestController {
	
	@Autowired
	private ImagenServiceImpl imagenService;
	
	@GetMapping("/imagenes-inmueble/{id}")
	public ResponseEntity<List<ImagenDTO>> findImagenesInmueble(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(imagenService.getImagenes(EntidadImagen.INMUEBLE ,id));//200
	}
	
	@GetMapping("/imagen-banner/{id}")
	public ResponseEntity<ImagenDTO> findImagenBanner(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(imagenService.getUnicaImagen(EntidadImagen.BANNER ,id));//200
	}
	
	@GetMapping("/imagen-inmobiliaria/{id}")
	public ResponseEntity<ImagenDTO> findImagenInmobiliaria(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(imagenService.getUnicaImagen(EntidadImagen.INMOBILIARIA ,id));//200
	}
	
	@GetMapping("/imagen-banner-carousel/{id}")
	public ResponseEntity<ImagenDTO> findImagenBannerCarousel(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(imagenService.getUnicaImagen(EntidadImagen.BANNER_CAROUSEL ,id));//200
	}
	
	@PostMapping("/imagen")
	public ResponseEntity<ImagenDTO> create(@RequestBody Imagen imagen) {
		return ResponseEntity.status(HttpStatus.CREATED).body(imagenService.save(imagen));//201
	}
	
	@PutMapping("/imagen")
	public ResponseEntity<ImagenDTO> update(@RequestBody Imagen imagen) {
		return ResponseEntity.status(HttpStatus.CREATED).body(imagenService.save(imagen));//201
	}
	
	@DeleteMapping("/imagen/{id}")
	public ResponseEntity<ImagenDTO> deleteById(@PathVariable Long id) {
		//ire = imagen recien borrada
		ImagenDTO ire = imagenService.deleteById(id);
		return ResponseEntity.ok(ire);
	}
	
}
