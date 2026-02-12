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
//import lombok.experimental.SuperBuilder;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
//@SuperBuilder
@ToString
@Entity 
@Table(name="provincia")
public class Provincia {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)//Identity es un incremental para MySQL
	@Column
	private Long id;
	
	@Column
	private String nombre;//BIZKAIA, BARCELONA, MADRID...
	
	@Column
	private Integer activo;
	
	@JsonIgnore
	@OneToMany(mappedBy="provincia")//Una provincia puede tener muchas poblaciones
	private List<Poblacion> poblaciones;
	
	@PrePersist	
	public void prePersist() {
		if (activo == null) activo = 1;
	}
	
	

}
