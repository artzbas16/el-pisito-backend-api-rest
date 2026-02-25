package com.ipartek.springboot.backend.apirest.elpisito.exceptions;

public class FileStorageException extends RuntimeException{
	
	private static final long serialVersionUID = -7466291412693570164L;

	public FileStorageException(String mensaje) {
		super(mensaje);
	}
	
	
	
}
