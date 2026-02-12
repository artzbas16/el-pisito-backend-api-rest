package com.ipartek.springboot.backend.apirest.elpisito.dtos;

import java.time.LocalDateTime;

public record ErrorResponseDTO(LocalDateTime timeStamp, int status, String error, String mensaje, String message, String path) {
	
	//Los records fueron implementados en Java 16 que sirven para crear clases INMUTABLES
	//Los records son unas clases especiales pensadas para transportar datos (data carriers)
	//Evitan escribir mucho codigo repititivo como:
	//1) constructores
	//2) getters (no tienen setters al ser objetos inmutables)
	//3) equals()
	//4) hashCode()
	//5) toString()
	
	//DTOs
	//Un DTO (Data Transfer Object) sirve para separar las capas de una aplicacion
	//especialmente la capa de presentacion de una API Rest de la capa de datos (entidades de la BBDD)
	//Esto se logra creando clases simples (generalmente Records) que representan los datos que necesitamos
	//transferir, evitando exponer la estructura interna de las entidades de negocio al exterior
	
	//¿Para que sirve un DTO en Spring?
	//1) SEPARACION DE RESPONSABILIDADES
	//Permite que las distintas capas de una aplicacion tengan distintas responsabilidades y no dependan
	//indirectamente unas de otras
	//2) FLEXIBILIDAD Y CONTROL
	//Un DTO puede contener solo los datos necesarios para cada operacion especifica evitando exponer informacion
	//sensible o irrelevante de las entidades de la BBDD
	//3) TRANSFERENCIA DE DATOS SIMPLIFICADA
	//Facilita la transferencia de datos entre las distintas partes de la aplicacion (cliente-servidor) en volumen
	//haciendo este proceso mas ligero
	//4) MEJORA EL RENDIMIENTO:
	//Al enviar menos datos en una sola respuesta se puede reducir el trafico en la red y mejorar el rendimiento 
	//de la aplicación.
	//5) FACILITA LA REUTILIZACION DE CODIGO
	//Los DTOs pueden ser reutilizados en diferentes partes de la aplicacion que requieran los mismos datos
	//6) PROTECCION DE ENTIDADES
	//Los DTOs ayudan a proteger las entidades de la BBDD de cambios no deseados o de exposicion 

}
