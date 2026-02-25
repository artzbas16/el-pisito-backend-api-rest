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

import com.ipartek.springboot.backend.apirest.elpisito.dtos.UsuarioDTO;
import com.ipartek.springboot.backend.apirest.elpisito.entities.Usuario;
import com.ipartek.springboot.backend.apirest.elpisito.services.UsuarioServiceImpl;

@RestController
@RequestMapping("/api")
public class UsuarioRestController {
	
	//Una de las caracteristicas mas significativas de un controlador
	//es que sus atributos pueden ser servicios
	
	//Cuando un controlador recibe los datos de un servicio (no se ha producido ninguna 
	//excepcion) LA API ENVIA SIEMPRE POR DEFECTO un 200 al cliente
	//pero si queremos personalizar la respuesta: 200, 201, ..., tambien podremos hacerlo
	//Solo los controladores devuelven codigos status 200, 201, ...etc (todo va bien)
	//¿Que sucede si se produce una excepcion en el servicio que invocamos? En este caso
	//vamos a tener una o varias clases anotadas como @RestControllerAdvice que son las 
	//encargadas de devolver los errores a cliente (Son "exception handlers").
	
	//NO UTILIZAR NUNCA try/catch en los Controladores
	@Autowired
	private UsuarioServiceImpl usuarioService;
	
	@GetMapping("/usuarios")
	public ResponseEntity<List<UsuarioDTO>> findAll(){
		return ResponseEntity.ok(usuarioService.findAll()); //200
	}
	
	@GetMapping("/usuarios-activos")
	public ResponseEntity<List<UsuarioDTO>> findAllActive(){
		return ResponseEntity.ok(usuarioService.findAllActive()); //200
	}
	
	@GetMapping("/usuario/{id}")
	public ResponseEntity<UsuarioDTO> findById(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findById(id));
	}
	
	@PostMapping("/usuario")
	public ResponseEntity<UsuarioDTO> create(@RequestBody Usuario usuario) {
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuario));//201
	}
	
	
	@PutMapping("/usuario")
	public ResponseEntity<UsuarioDTO> update(@RequestBody Usuario usuario) {
		//usuarioCompleto es un Usuario con todos los datos con las modificaciones incorporadas
		Usuario usuarioCompleto = usuarioService.completaUsuarioRequestRespetandoNull(usuario);
		
		UsuarioDTO usuarioF = usuarioService.save(usuarioCompleto);
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioF);//201
	}
	
	@DeleteMapping("/usuario/{id}")
	public ResponseEntity<UsuarioDTO> deleteById(@PathVariable Long id) {
		//ure = usuario recien eliminado
		
		return ResponseEntity.ok(usuarioService.deleteById(id));
	}
	
	
	
	
}
