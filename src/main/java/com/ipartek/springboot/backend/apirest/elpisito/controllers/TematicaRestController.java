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

import com.ipartek.springboot.backend.apirest.elpisito.entities.Tematica;
import com.ipartek.springboot.backend.apirest.elpisito.services.TematicaServiceImpl;

@RestController
@RequestMapping("/api")
public class TematicaRestController {
	
	@Autowired
	private TematicaServiceImpl tematicaService;
	
	@GetMapping("/tematicas")
	public ResponseEntity<List<Tematica>> findAll(){
		return ResponseEntity.ok(tematicaService.findAll()); //200
	}
	
	@GetMapping("/tematicas-activas")
	public ResponseEntity<List<Tematica>> findAllActive(){		
		return ResponseEntity.ok(tematicaService.findAllActive()); //200
	}
	
	@GetMapping("/tematica/{id}")
	public ResponseEntity<Tematica> findById(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(tematicaService.findById(id));//200
	}
	
	@PostMapping("/tematica")
	public ResponseEntity<Tematica> create(@RequestBody Tematica tematica) {
		return ResponseEntity.status(HttpStatus.CREATED).body(tematicaService.save(tematica));//201
	}
	
	@PutMapping("/tematica")
	public ResponseEntity<Tematica> update(@RequestBody Tematica tematica) {
		return ResponseEntity.status(HttpStatus.CREATED).body(tematicaService.save(tematica));//201
	}
	
	@DeleteMapping("/tematica/{id}")
	public ResponseEntity<Tematica> deleteById(@PathVariable Long id) {
		return ResponseEntity.ok(tematicaService.deleteById(id));
	}

}
