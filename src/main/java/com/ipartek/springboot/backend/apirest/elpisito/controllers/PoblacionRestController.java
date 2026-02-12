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

import com.ipartek.springboot.backend.apirest.elpisito.entities.Poblacion;
import com.ipartek.springboot.backend.apirest.elpisito.services.PoblacionServiceImpl;

@RestController
@RequestMapping("/api")
public class PoblacionRestController {
	
	@Autowired
	private PoblacionServiceImpl poblacionService;
	
	@GetMapping("/poblaciones")
	public ResponseEntity<List<Poblacion>> findAll(){
		return ResponseEntity.ok(poblacionService.findAll()); //200
	}
	
	@GetMapping("/poblaciones-activas")
	public ResponseEntity<List<Poblacion>> findAllActive(){		
		return ResponseEntity.ok(poblacionService.findAllActive()); //200
	}
	
	@GetMapping("/poblacion/{id}")
	public ResponseEntity<Poblacion> findById(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(poblacionService.findById(id));//200
	}
	
	@PostMapping("/poblacion")
	public ResponseEntity<Poblacion> create(@RequestBody Poblacion poblacion) {
		return ResponseEntity.status(HttpStatus.CREATED).body(poblacionService.save(poblacion));//201
	}
	
	@PutMapping("/poblacion")
	public ResponseEntity<Poblacion> update(@RequestBody Poblacion poblacion) {
		return ResponseEntity.status(HttpStatus.CREATED).body(poblacionService.save(poblacion));//201
	}
	
	@DeleteMapping("/poblacion/{id}")
	public ResponseEntity<Poblacion> deleteById(@PathVariable Long id) {
		return ResponseEntity.ok(poblacionService.deleteById(id));
	}
	
}
