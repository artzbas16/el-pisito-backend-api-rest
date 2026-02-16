package com.ipartek.springboot.backend.apirest.elpisito.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name="inmobiliarias")
public class Inmobiliaria {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)//Identity es un incremental para MySQL
	@Column
	private Long id;
	
	@Column(unique = true)
	private String nombre;//ARBETETA, GALEA, AZKORRI
	
	@Column
	private String telefono;
	
	@Column
	private String representante;
	
	@JsonIgnore
	@OneToMany(mappedBy = "inmobiliaria")
	private List<Inmueble> inmuebles;
	
	@Column
	private Integer activo;
	
	@PrePersist	
	public void prePersist() {
		if (activo == null) activo = 1;
	}
}
