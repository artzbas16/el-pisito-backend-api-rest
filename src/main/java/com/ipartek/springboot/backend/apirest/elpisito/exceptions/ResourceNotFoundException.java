package com.ipartek.springboot.backend.apirest.elpisito.exceptions;

public class ResourceNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = -286662164824553721L;

	public ResourceNotFoundException(String mensaje) {
		super(mensaje);
	}
	
	
}
