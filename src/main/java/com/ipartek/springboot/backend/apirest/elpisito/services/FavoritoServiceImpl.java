package com.ipartek.springboot.backend.apirest.elpisito.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipartek.springboot.backend.apirest.elpisito.dtos.ImagenDTO;
import com.ipartek.springboot.backend.apirest.elpisito.dtos.InmuebleIdDTO;
import com.ipartek.springboot.backend.apirest.elpisito.dtos.InmuebleImagenDTO;
import com.ipartek.springboot.backend.apirest.elpisito.entities.Inmueble;
import com.ipartek.springboot.backend.apirest.elpisito.entities.Usuario;
import com.ipartek.springboot.backend.apirest.elpisito.enumerators.EntidadImagen;
import com.ipartek.springboot.backend.apirest.elpisito.mappers.InmuebleMapper;
import com.ipartek.springboot.backend.apirest.elpisito.repositories.InmuebleRepository;
import com.ipartek.springboot.backend.apirest.elpisito.repositories.UsuarioRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class FavoritoServiceImpl {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private InmuebleRepository inmuebleRepository;
	
	@Autowired
	private ImagenServiceImpl imagenService;
	
	@Autowired
	private InmuebleMapper inmuebleMapper;
	
	public InmuebleImagenDTO addFavorito(Long usuarioId, Long inmuebleId) {
		Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(() -> new EntityNotFoundException("El usuario con id " + usuarioId + " no existe"));
		Inmueble inmueble = inmuebleRepository.findById(inmuebleId).orElseThrow(() -> new EntityNotFoundException("El inmueble con id " + inmuebleId + " no existe"));
		
		usuario.getInmueblesFavoritos().add(inmueble);
		usuarioRepository.save(usuario); //Es aquí donde hibernate hace el apunte en la BBDD en la persistencia
		
		return inmuebleMapper.toDto(inmueble, imagenService);
	}
	
	public InmuebleImagenDTO deleteFavorito(Long usuarioId, Long inmuebleId) {
		Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(() -> new EntityNotFoundException("El usuario con id " + usuarioId + " no existe"));
		Inmueble inmueble = inmuebleRepository.findById(inmuebleId).orElseThrow(() -> new EntityNotFoundException("El inmueble con id " + inmuebleId + " no existe"));
		
		usuario.getInmueblesFavoritos().remove(inmueble);
		usuarioRepository.save(usuario); //Es aquí donde hibernate hace el apunte en la BBDD en la persistencia
		return inmuebleMapper.toDto(inmueble, imagenService);
	}
	
	//Este metodo a partir de un id de Usuario devuelve todos sus inmuebles favoritos incluyendo imagenes
	public List<InmuebleImagenDTO> findFavoritos(Long usuarioId){
		Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(() -> new EntityNotFoundException("El usuario con id " + usuarioId + " no existe"));
		List<Inmueble> inmuebles = new ArrayList<>(usuario.getInmueblesFavoritos());
		
		List<Long> ids = inmuebles.stream()		
				.map(Inmueble::getId)
				.toList();											
			
		Map<Long, List<ImagenDTO>> mapaImagenes = imagenService.getImagenesPorEntidadBulk(EntidadImagen.INMUEBLE, ids); 
		
		return inmuebleMapper.toDtoBulk(inmuebles, mapaImagenes);
	}
	
	//Este metodo a partir de un id de Usuario devuelve todos los ids de sus inmuebles favoritos
		public List<InmuebleIdDTO> findFavoritosId(Long usuarioId){
			Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(() -> new EntityNotFoundException("El usuario con id " + usuarioId + " no existe"));
			List<Inmueble> inmuebles = new ArrayList<>(usuario.getInmueblesFavoritos());
			
			return inmuebleMapper.toDtoIdList(inmuebles);
		}
}
