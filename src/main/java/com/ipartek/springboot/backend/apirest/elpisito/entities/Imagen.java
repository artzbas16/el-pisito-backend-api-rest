package com.ipartek.springboot.backend.apirest.elpisito.entities;

import com.ipartek.springboot.backend.apirest.elpisito.enumerators.EntidadImagen;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity 
@Table(name="imagenes")
public class Imagen {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)//Identity es un incremental para MySQL
	@Column
	private Long id;
	
	@Column
	private String nombre; //09090200932432312.jpg
	
	@Enumerated(EnumType.STRING)
	@Column(name = "entidad_imagen")
	private EntidadImagen entidadImagen;
	
	@Column(name = "entidad_id")
	private Long entidadId;
	
	@Column(name = "alt_imagen")
	private String altImagen;
	
	//No vamos a poner activo porque las imagenes si no son necesarias van a ser borradas
	
}
