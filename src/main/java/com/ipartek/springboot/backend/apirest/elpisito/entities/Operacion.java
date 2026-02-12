package com.ipartek.springboot.backend.apirest.elpisito.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
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
@Table(name="operaciones")
public class Operacion {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)//Identity es un incremental para MySQL
	@Column
	private Long id;
	
	@Column
	private String nombre;//VENTA, ALQUILER, TRASPASO,...
	
	@Column
	private Integer activo;
	
	@PrePersist	
	public void prePersist() {
		if (activo == null) activo = 1;
	}
	
}
