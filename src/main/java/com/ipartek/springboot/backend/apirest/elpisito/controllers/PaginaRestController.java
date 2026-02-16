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

import com.ipartek.springboot.backend.apirest.elpisito.entities.Pagina;
import com.ipartek.springboot.backend.apirest.elpisito.services.PaginaServiceImpl;

@RestController
@RequestMapping("/api")
public class PaginaRestController {
	
	@Autowired
	private PaginaServiceImpl paginaService;
	
	@GetMapping("/paginas")
	public ResponseEntity<List<Pagina>> findAll(){
		return ResponseEntity.ok(paginaService.findAll()); //200
	}
	
	@GetMapping("/paginas-activas")
	public ResponseEntity<List<Pagina>> findAllActive(){		
		return ResponseEntity.ok(paginaService.findAllActive()); //200
	}
	
	@GetMapping("/pagina/{id}")
	public ResponseEntity<Pagina> findById(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(paginaService.findById(id));//200
	}
	
	@PostMapping("/pagina")
	public ResponseEntity<Pagina> create(@RequestBody Pagina pagina) {
		return ResponseEntity.status(HttpStatus.CREATED).body(paginaService.save(pagina));//201
	}
	
	@PutMapping("/pagina")
	public ResponseEntity<Pagina> update(@RequestBody Pagina pagina) {
		return ResponseEntity.status(HttpStatus.CREATED).body(paginaService.save(pagina));//201
	}
	
	@DeleteMapping("/pagina/{id}")
	public ResponseEntity<Pagina> deleteById(@PathVariable Long id) {
		return ResponseEntity.ok(paginaService.deleteById(id));
	}

}
