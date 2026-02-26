package com.ipartek.springboot.backend.apirest.elpisito.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ipartek.springboot.backend.apirest.elpisito.dtos.ImagenDTO;
import com.ipartek.springboot.backend.apirest.elpisito.entities.Imagen;
import com.ipartek.springboot.backend.apirest.elpisito.enumerators.EntidadImagen;
import com.ipartek.springboot.backend.apirest.elpisito.services.ImagenServiceImpl;

@RestController
@RequestMapping("/api/imagenes")
public class ImagenRestController {
	
	@Autowired
	private ImagenServiceImpl imagenService;
	
	@PostMapping("/upload")
	public ResponseEntity<ImagenDTO> upload(@RequestParam MultipartFile file, 
											@RequestParam EntidadImagen entidadImagen,
											@RequestParam Long entidadId,
											@RequestParam String alt){
		
		return ResponseEntity.status(HttpStatus.CREATED).body(imagenService.store(file, entidadImagen, entidadId, alt));
	}
	
	@GetMapping("/{nombreEntidadImagenMinusculas}/{entidadId}/{nombre}")
	public ResponseEntity<Resource> rederizarImagen(@PathVariable String nombreEntidadImagenMinusculas, 
													@PathVariable Long entidadId, 
													@PathVariable String nombre){
		EntidadImagen entidadImagen = EntidadImagen.valueOf(nombreEntidadImagenMinusculas.toUpperCase());
		return imagenService.renderizarImagen(entidadImagen, entidadId, nombre);
		
	}
	
	@GetMapping("/inmueble/{id}")
	public ResponseEntity<List<ImagenDTO>> findImagenesInmueble(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(imagenService.getImagenes(EntidadImagen.INMUEBLE ,id));//200
	}
	
	@GetMapping("/banner/{id}")
	public ResponseEntity<List<ImagenDTO>> findImagenBanner(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(imagenService.getImagenes(EntidadImagen.BANNER ,id));//200
	}
	
	@GetMapping("/inmobiliaria/{id}")
	public ResponseEntity<List<ImagenDTO>> findLogoInmobiliaria(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(imagenService.getImagenes(EntidadImagen.INMOBILIARIA ,id));//200
	}
	
	@GetMapping("/banner-carousel/{id}")
	public ResponseEntity<List<ImagenDTO>> findImagenBannerCarousel(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(imagenService.getImagenes(EntidadImagen.BANNER_CAROUSEL ,id));//200
	}
	
	//Creamos imagen en la BBDD
	@PostMapping("/imagen")
	public ResponseEntity<ImagenDTO> create(@RequestBody Imagen imagen) {
		return ResponseEntity.status(HttpStatus.CREATED).body(imagenService.save(imagen));//201
	}
	
	//Modificamos imagen en la BBD
	@PutMapping("/imagen")
	public ResponseEntity<ImagenDTO> update(@RequestBody Imagen imagen) {
		return ResponseEntity.status(HttpStatus.CREATED).body(imagenService.save(imagen));//201
	}
	
	//ELiminamos imagen en la BBDD
	@DeleteMapping("/imagen/{id}")
	public ResponseEntity<ImagenDTO> deleteById(@PathVariable Long id) {
		//ire = imagen recien borrada
		ImagenDTO ire = imagenService.deleteImagenFisicaYBBDD(id);
		return ResponseEntity.ok(ire);
	}
	
}
