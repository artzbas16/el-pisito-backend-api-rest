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

import com.ipartek.springboot.backend.apirest.elpisito.dtos.InmuebleImagenDTO;
import com.ipartek.springboot.backend.apirest.elpisito.entities.Inmueble;
import com.ipartek.springboot.backend.apirest.elpisito.services.InmuebleServiceImpl;

@RestController
@RequestMapping("/api")
public class InmuebleRestController {
	
	@Autowired
	private InmuebleServiceImpl inmuebleService;
	
	@GetMapping("/inmuebles")
	public ResponseEntity<List<InmuebleImagenDTO>> findAll(){
		return ResponseEntity.ok(inmuebleService.findAllBulk()); //200
	}
	
	@GetMapping("/inmuebles-activos")
	public ResponseEntity<List<InmuebleImagenDTO>> findAllActive(){
		return ResponseEntity.ok(inmuebleService.findAllActiveBulk());
	}
	
	@GetMapping("/inmuebles-portada")
	public ResponseEntity<List<InmuebleImagenDTO>> findAllPortada(){
		return ResponseEntity.ok(inmuebleService.findAllPortadaBulk());
	}
	
	@GetMapping("/inmuebles-inmobiliaria/{inmobiliariaId}")
	public ResponseEntity<List<InmuebleImagenDTO>> findInmueblesInmobiliaria(@PathVariable Long inmobiliariaId){
		return ResponseEntity.ok(inmuebleService.findInmueblesInmobiliariaBulk(inmobiliariaId));
	}
	
	@GetMapping("/finder/{tipoId}/{poblacionId}/{operacionId}")
	public ResponseEntity<List<InmuebleImagenDTO>> finderBulk(@PathVariable Long tipoId, @PathVariable Long poblacionId, @PathVariable Long operacionId){
		return ResponseEntity.ok(inmuebleService.finderBulk(tipoId, poblacionId, operacionId));
	}
	
	@PostMapping("/inmueble")
	public ResponseEntity<InmuebleImagenDTO> create(@RequestBody Inmueble inmueble) {
		return ResponseEntity.status(HttpStatus.CREATED).body(inmuebleService.save(inmueble));//201
	}
	
	@PutMapping("/inmueble")
	public ResponseEntity<InmuebleImagenDTO> update(@RequestBody Inmueble inmueble) {
		return ResponseEntity.status(HttpStatus.CREATED).body(inmuebleService.save(inmueble));//201
	}
	
	@GetMapping("/inmueble/{id}")
	public ResponseEntity<InmuebleImagenDTO> findInmuebleById(@PathVariable Long id){
		return ResponseEntity.ok(inmuebleService.findById(id));
	}
	
	@DeleteMapping("/inmueble/{id}")
	public ResponseEntity<InmuebleImagenDTO> deleteById(@PathVariable Long id) {
		return ResponseEntity.ok(inmuebleService.deleteById(id));
	}
	
}
