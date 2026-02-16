package com.ipartek.springboot.backend.apirest.elpisito.entities;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ipartek.springboot.backend.apirest.elpisito.enumerators.Rol;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
@Table(name="usuarios")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)//Identity es un incremental para MySQL
	@Column
	private Long id;
	
	@Column(unique=true, nullable=false)
	private String nombre;
	
	@Column
	private String password;
	
	@Column(name="password_open")
	private String passwordOpen;
	
	@Column(unique=true)
	private String email;
	
	@Enumerated(EnumType.STRING)
	@Column
	//@Builder.Default
	private Rol rol;
	
	@Column
	//@Builder.Default
	private Integer activo;
	
	//Elegimos Usuario como entidad dominante de la relacion con Inmueble: es el Usuario el que 
	//"toma la decision" de favoritar un inmueble y no al reves
	@JsonIgnore
	@ManyToMany
	@JoinTable(
			name = "usuario-inmueble",
			joinColumns = {@JoinColumn(name="usuario_id")},
			inverseJoinColumns = {@JoinColumn(name="inmueble_id")}
			)
	private Set<Inmueble> inmueblesFavoritos; //Este es el mappedBy de Inmueble
	
	
	//Esta funcion es llamada automaticamente cuando se crea un nuevo objeto
	//Lo que conseguimos es que Jackson (encargado de serializar) (y lo setea como null)
	//posteriormente al llamarse este metodo este valor sea corregido
	//constructor --> Jackson --> @PrePersist
	@PrePersist	
	public void prePersist() {
		if (activo == null) activo = 1;
		if (rol == null) rol = Rol.ROLE_USUARIO;
	}
	
	
	

}
