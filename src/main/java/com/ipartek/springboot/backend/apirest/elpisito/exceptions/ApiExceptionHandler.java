package com.ipartek.springboot.backend.apirest.elpisito.exceptions;

import java.nio.file.FileAlreadyExistsException;
import java.nio.file.FileSystemException;
import java.nio.file.NoSuchFileException;
import java.time.LocalDateTime;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ipartek.springboot.backend.apirest.elpisito.dtos.ErrorResponseDTO;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;

//¿Una aplicacion Spring puede tener varios @RestControllerAdvice? Si
//No solo puede tener varios sino ademas esta recomendado. Es buena practica tener un 
//@RestControllerAdvice general y tener otros mas especializados (dependiendo de nuestra aplicacion)
//Cuando se produce una excepcion y se encuentra con @RestControllerAdvice la excepcion salta
//el stack-trace y pasa a ser gestionada por el automaticamente.
//Si tenemos varios, ¿Cual se llama el primero y en que orden? Para esto existe una anotacion
//complementaria llamada @Order en la que estableceremos el orden de llamada @Order(1)
//¿Podriamos tener una excepcio, en concreto, tratada en varios @RestControllerAdvice? en teoria
//si, pero existe una norma de buena practica que dice que no debemos duplicar handlers.

//Spring Security (API de Spring especializada en seguridad) no envia exepciones a @RestControllerAdvice

//Las excepciones que se produzcan en general en nuestra aplicacion y esten gestionada por SpringSecurity
//ejemplo: excepciones en Controladores, Servicios son eviadas automaticamente aqui

@RestControllerAdvice
public class ApiExceptionHandler {
	
	//ESTE ES EL CONTROLADOR GLOBAL (SOLO TENEMOS 1) DE ERRORES. SUS METODOS SON INVOCADOS AUTOMATICAMENTE
	//POR SPRING CUANDO SE PRODUCE UNA EXCEPTION SALTANDOSE LA CADENA "STACK TRACE"
	
	//Si se produce una excepcion el flujo es el siguiente:
	//cliente --> Middlewares --> Controladores --> Servicios (se produce la excepcion) --> @RestControllerAdvice --> cLiente
	
	//Si se produce una excepcion el flujo es el siguiente:
	//cliente --> Middlewares --> Controladores --> Servicios --> Repositorio --> BBDD --> Repositorio --> Servicios --> Controladores --> cLiente

	private ResponseEntity<ErrorResponseDTO> build(HttpStatus status, String mensaje, Exception ex, HttpServletRequest req){
		ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(
				LocalDateTime.now(), 
				status.value(), 
				status.getReasonPhrase(),
				mensaje, 
				ex.getMessage(),
				req.getRequestURI()
				);
		
		return ResponseEntity.status(status).body(errorResponseDTO);
	}
	
	//JPA+HIBERNATE
	//Intentamos insertar un registro con un campo inexistente relacionado con otra tabla
	//Ejemplo (Violacion de una clave foranea FK) error por la integridad relacional
	//Ejemplo: tenemos una imagen y queremos subirla al servidar y queremos relacionarla con un inmueble cuyo id no existe (rompemos la integridad relacional de la tabla)
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ErrorResponseDTO> dataIntegrityViolation(DataIntegrityViolationException ex, HttpServletRequest req){
		
		String elMensaje = "Estás intentando hacer una anotación en la BBDD que rompe su integridad relacional";
		return build(HttpStatus.CONFLICT, elMensaje, ex, req); //409 
		
	}
	
	//JPA+HIBERNATE
	//Intentamos actualizar un objeto de una tabla y el id de ese objeto no existe
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ResponseEntity<ErrorResponseDTO> emptyResultDataAccess(EmptyResultDataAccessException ex, HttpServletRequest req){
		
		String elMensaje = "Estás intentando actualizar un item inexistente en la BBDD";
		return build(HttpStatus.NOT_FOUND, elMensaje, ex, req); //404 
		
	}
	
	//JPA+HIBERNATE
	//Pasamos un formato incorrecto (2023     -     31 -12)
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ErrorResponseDTO> constraintViolation(ConstraintViolationException ex, HttpServletRequest req){
		
		String elMensaje = "Estás intentando crear un registro en la BBDD con formato incorrecto";
		return build(HttpStatus.BAD_REQUEST, elMensaje, ex, req); //400
		
	}
	
	
	//Intentamos hacer registros en la BBDD que exceden su rango, longitud,...
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ErrorResponseDTO> illegalArgument(IllegalArgumentException ex, HttpServletRequest req){
		
		String elMensaje = "Estás intentando crear o modificar un registro en la BBDD que excede tamaño, longitud o rango";
		return build(HttpStatus.BAD_REQUEST, elMensaje, ex, req); //400
		
	}
	
	//JPA+HIBERNATE
	//BBDD caida (no confundir con API caida. Si la API esta caida no puede devolver nada!!!)
	@ExceptionHandler(DataAccessResourceFailureException.class)
	public ResponseEntity<ErrorResponseDTO> dataAccessResourceFailure(DataAccessResourceFailureException ex, HttpServletRequest req){
		
		String elMensaje = "La BBDD está caida o inoperativa";
		return build(HttpStatus.SERVICE_UNAVAILABLE, elMensaje, ex, req); //503
		
	}
	
	//JPA+HIBERNATE
	//No se ha encontrado la entidad en la BBDD
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<ErrorResponseDTO> entityNotFound(EntityNotFoundException ex, HttpServletRequest req){
		
		String elMensaje = "Estás intentando acceder a una tabla o un registro que no existe en la BBDD";
		return build(HttpStatus.SERVICE_UNAVAILABLE, elMensaje, ex, req); //503
		
	}
	
	//JPA+HIBERNATE
	//Estás intentando llamar a un metodo pasandole argumentos no permitidos
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponseDTO> methodArgumentNotValid(MethodArgumentNotValidException ex, HttpServletRequest req){
		
		String elMensaje = "El dato que estás utilizando tiene un formato inválido";
		return build(HttpStatus.BAD_REQUEST, elMensaje, ex, req); //400
		
	}
	
	
	//FILE
	//Intentamos crear un archivo (jpg, pdf,...) en nuestro directorio de archivos (mediafiles)
	//y ya existe
	@ExceptionHandler(FileAlreadyExistsException.class)
	public ResponseEntity<ErrorResponseDTO> fileAlreadyExists(FileAlreadyExistsException ex, HttpServletRequest req){
		
		String elMensaje = "Estás intentando almacenar un archivo cuyo nombre ya existe";
		return build(HttpStatus.INTERNAL_SERVER_ERROR, elMensaje, ex, req); //500
		
	}
	
	//FILE
	//Puede ocurrir si algún componente intermedio de una ruta (URI) no existe y el sistema no encuentra el recurso
	@ExceptionHandler(NoSuchFileException.class)
	public ResponseEntity<ErrorResponseDTO> noSuchFile(NoSuchFileException ex, HttpServletRequest req){
		
		String elMensaje = "Estás intentando acceder a un archivo cuya ruta no existe";
		return build(HttpStatus.INTERNAL_SERVER_ERROR, elMensaje, ex, req); //500
		
	}
	
	//FILE
	//Errror generico del sistema de archivos (disco lleno, archivo corrupto,...)
	@ExceptionHandler(FileSystemException.class)
	public ResponseEntity<ErrorResponseDTO> fileSystem(FileSystemException ex, HttpServletRequest req){
		
		String elMensaje = "La BBDD está caida o inoperativa";
		return build(HttpStatus.INTERNAL_SERVER_ERROR, elMensaje, ex, req); //500
		
	}
	
	//JPA+HIBERNATE
	//La ponemos la ultima porque es muy generica
	//Es un error muy generico. No es un error de la capa de negocio sino de la capa de persistencia
	//Se produciría cuando estamos intentando utilizar (borrar, modificar,...) un recurso en la BBDD y se produce
	//un error cuya causa no es, por ejemplo, que el recurso no exista sino mas bien un error de la BBDD
	@ExceptionHandler(DataAccessException.class)
	public ResponseEntity<ErrorResponseDTO> dataAccess(DataAccessException ex, HttpServletRequest req){
		
		String elMensaje = "Fallo al intentar acceder a la BBDD";
		return build(HttpStatus.INTERNAL_SERVER_ERROR, elMensaje, ex, req); //500
		
	}
	
	//HTTP
	//Estamos llamando a un endpoint que no existe
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<ErrorResponseDTO> httpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpServletRequest req){
		
		String elMensaje = "El endpoint al que estas intentando acceder no existe";
		return build(HttpStatus.METHOD_NOT_ALLOWED, elMensaje, ex, req); //405
		
	}
	/////////////////////////////////////////////////////////////////////////////////////////////
	//PERSONALIZADAS
	/////////////////////////////////////////////////////////////////////////////////////////////
	
	

}
