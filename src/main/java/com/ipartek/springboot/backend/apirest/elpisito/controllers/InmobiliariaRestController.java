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

import com.ipartek.springboot.backend.apirest.elpisito.dtos.InmobiliariaImagenDTO;
import com.ipartek.springboot.backend.apirest.elpisito.entities.Inmobiliaria;
import com.ipartek.springboot.backend.apirest.elpisito.services.InmobiliariaServiceImpl;

@RestController
@RequestMapping("/api")
public class InmobiliariaRestController {
	
	@Autowired
	private InmobiliariaServiceImpl inmobiliariaService;
	
	@GetMapping("/inmobiliarias")
	public ResponseEntity<List<InmobiliariaImagenDTO>> findAll(){
		return ResponseEntity.ok(inmobiliariaService.findAllBulk()); //200
	}
	
	@GetMapping("/inmobiliarias-activas")
	public ResponseEntity<List<InmobiliariaImagenDTO>> findAllActive(){		
		return ResponseEntity.ok(inmobiliariaService.findAllActiveBulk()); //200
	}
	
	@GetMapping("/inmobiliaria/{id}")
	public ResponseEntity<InmobiliariaImagenDTO> findById(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(inmobiliariaService.findById(id));//200
	}
	
	@PostMapping("/inmobiliaria")
	public ResponseEntity<InmobiliariaImagenDTO> create(@RequestBody Inmobiliaria inmobiliaria) {
		return ResponseEntity.status(HttpStatus.CREATED).body(inmobiliariaService.save(inmobiliaria));//201
	}
	
	@PutMapping("/inmobiliaria")
	public ResponseEntity<InmobiliariaImagenDTO> update(@RequestBody Inmobiliaria inmobiliaria) {
		return ResponseEntity.status(HttpStatus.CREATED).body(inmobiliariaService.save(inmobiliaria));//201
	}
	
	@DeleteMapping("/inmobiliaria/{id}")
	public ResponseEntity<Inmobiliaria> deleteById(@PathVariable Long id) {
		return ResponseEntity.ok(inmobiliariaService.deleteById(id));
	}
}
