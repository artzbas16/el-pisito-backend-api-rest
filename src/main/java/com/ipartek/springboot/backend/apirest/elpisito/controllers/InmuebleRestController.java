package com.ipartek.springboot.backend.apirest.elpisito.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ipartek.springboot.backend.apirest.elpisito.entities.Inmueble;
import com.ipartek.springboot.backend.apirest.elpisito.services.InmuebleServiceImpl;

@RestController
@RequestMapping("/api")
public class InmuebleRestController {
	
	@Autowired
	private InmuebleServiceImpl inmuebleService;
	
	@GetMapping("/inmuebles")
	public ResponseEntity<List<Inmueble>> findAll(){
		return ResponseEntity.ok(inmuebleService.findAll()); //200
	}
	
}
