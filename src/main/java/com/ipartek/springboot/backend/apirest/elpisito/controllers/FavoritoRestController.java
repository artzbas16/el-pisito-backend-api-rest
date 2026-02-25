package com.ipartek.springboot.backend.apirest.elpisito.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ipartek.springboot.backend.apirest.elpisito.dtos.InmuebleIdDTO;
import com.ipartek.springboot.backend.apirest.elpisito.dtos.InmuebleImagenDTO;
import com.ipartek.springboot.backend.apirest.elpisito.services.FavoritoServiceImpl;

@RestController
@RequestMapping("/api")
public class FavoritoRestController {
	
	@Autowired
	private FavoritoServiceImpl favoritoService;
	
	@PostMapping("/favorito")
	public ResponseEntity<InmuebleImagenDTO> addFavorito(@RequestParam Long usuarioId, @RequestParam Long inmuebleId){
		return ResponseEntity.status(HttpStatus.CREATED).body(favoritoService.addFavorito(usuarioId, inmuebleId));
	}
	
	@DeleteMapping("/favorito")
	public ResponseEntity<InmuebleImagenDTO> deleteFavorito(@RequestParam Long usuarioId, @RequestParam Long inmuebleId){
		return ResponseEntity.status(HttpStatus.CREATED).body(favoritoService.deleteFavorito(usuarioId, inmuebleId));
	}
	
	@GetMapping("/favoritos-usuario/{id}")
	public ResponseEntity<List<InmuebleImagenDTO>> findFavoritos(@PathVariable Long id){
		return ResponseEntity.status(HttpStatus.CREATED).body(favoritoService.findFavoritos(id));
	}
	
	@GetMapping("/favoritosid-usuario/{id}")
	public ResponseEntity<List<InmuebleIdDTO>> findFavoritosId(@PathVariable Long id){
		return ResponseEntity.status(HttpStatus.CREATED).body(favoritoService.findFavoritosId(id));
	}
	
	
}
