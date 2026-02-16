package com.ipartek.springboot.backend.apirest.elpisito.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
//import lombok.experimental.SuperBuilder;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity 
@Table(name="poblaciones")
public class Poblacion {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)//Identity es un incremental para MySQL
	@Column
	private Long id;
	
	@Column
	private String nombre;//ALGORTA, BARAKALDO, MOSTOLES,...
	
	@Column
	private Integer activo;
	
	@Column
	private String cp;
	
	//Generalmente es en el @ManyToOne donde se suele encontrarse el @JoinColumn
	@ManyToOne //Muchas poblaciones pueden pertenecer a una provincia
	@JoinColumn(name="provincias") //Este es el nombre de la columna que aparecerá en la base de datos
	private Provincia provincia;//este es el MappedBy de Provincia
	
	@JsonIgnore
	@OneToMany(mappedBy = "poblacion")
	private List<Inmueble> inmuebles;
	
	@PrePersist	
	public void prePersist() {
		if (activo == null) activo = 1;
	}

}
