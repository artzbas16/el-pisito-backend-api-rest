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

import com.ipartek.springboot.backend.apirest.elpisito.entities.Banner;
import com.ipartek.springboot.backend.apirest.elpisito.services.BannerServiceImpl;

@RestController
@RequestMapping("/api")
public class BannerRestController {
	
	@Autowired
	private BannerServiceImpl bannerService;
	
	@GetMapping("/banners")
	public ResponseEntity<List<Banner>> findAll(){
		return ResponseEntity.ok(bannerService.findAll()); //200
	}
	
	@GetMapping("/banners-activos")
	public ResponseEntity<List<Banner>> findAllActive(){		
		return ResponseEntity.ok(bannerService.findAllActive()); //200
	}
	
	@GetMapping("/banner/{id}")
	public ResponseEntity<Banner> findById(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(bannerService.findById(id));//200
	}
	
	@PostMapping("/banner")
	public ResponseEntity<Banner> create(@RequestBody Banner banner) {
		return ResponseEntity.status(HttpStatus.CREATED).body(bannerService.save(banner));//201
	}
	
	@PutMapping("/banner")
	public ResponseEntity<Banner> update(@RequestBody Banner banner) {
		return ResponseEntity.status(HttpStatus.CREATED).body(bannerService.save(banner));//201
	}
	
	@DeleteMapping("/banner/{id}")
	public ResponseEntity<Banner> deleteById(@PathVariable Long id) {
		return ResponseEntity.ok(bannerService.deleteById(id));
	}

}
