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
		
		InmuebleImagenDTO elInmueble = inmuebleMapper.toDto(inmueble); //elInmueble esta sin imagenes
		
		List<ImagenDTO> imagenesInmueble = imagenService.getImagenes(EntidadImagen.INMUEBLE, inmuebleId);
		List<ImagenDTO> imagenesInmobiliaria = imagenService.getImagenes(EntidadImagen.INMOBILIARIA, elInmueble.getInmobiliaria().getId());
		
		elInmueble.setImagenes(imagenesInmueble);
		elInmueble.getInmobiliaria().setImagenes(imagenesInmobiliaria);
		
		return elInmueble;
		
	}
	
	public InmuebleImagenDTO deleteFavorito(Long usuarioId, Long inmuebleId) {
		Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(() -> new EntityNotFoundException("El usuario con id " + usuarioId + " no existe"));
		Inmueble inmueble = inmuebleRepository.findById(inmuebleId).orElseThrow(() -> new EntityNotFoundException("El inmueble con id " + inmuebleId + " no existe"));
		
		usuario.getInmueblesFavoritos().remove(inmueble);
		usuarioRepository.save(usuario); //Es aquí donde hibernate hace el apunte en la BBDD en la persistencia
		InmuebleImagenDTO elInmueble =  inmuebleMapper.toDto(inmueble);
		
		List<ImagenDTO> imagenesInmueble = imagenService.getImagenes(EntidadImagen.INMUEBLE, inmuebleId);
		List<ImagenDTO> imagenesInmobiliaria = imagenService.getImagenes(EntidadImagen.INMOBILIARIA, elInmueble.getInmobiliaria().getId());
		
		elInmueble.setImagenes(imagenesInmueble);
		elInmueble.getInmobiliaria().setImagenes(imagenesInmobiliaria);
		
		return elInmueble;
	}
	
	//Este metodo a partir de un id de Usuario devuelve todos sus inmuebles favoritos incluyendo imagenes
	public List<InmuebleImagenDTO> findFavoritos(Long usuarioId){
		Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(() -> new EntityNotFoundException("El usuario con id " + usuarioId + " no existe"));
		List<Inmueble> inmuebles = new ArrayList<>(usuario.getInmueblesFavoritos());
		
		List<InmuebleImagenDTO> dtos = inmuebleMapper.toDtoList(inmuebles); //Un list de inmuebles sin imagenes
		
		//Para crear un bulk necesitamos los id de los inmuebles que esten en dtos
		
		List<Long> inmuebleIds = dtos.stream()
			.map(InmuebleImagenDTO::getId)
			.toList();
		
		List<Long> inmobiliariaIds = dtos.stream()
										.map(dto -> dto.getInmobiliaria().getId())
										.distinct() //porque varios inmuebles puedes pertenecer a la misma inmobiliaria
										.toList();
		
		Map<Long, List<ImagenDTO>> imagenesInmuebleMap = imagenService.getImagenesPorEntidadBulk(EntidadImagen.INMUEBLE, inmuebleIds);
		
		Map<Long, List<ImagenDTO>> imagenesInmobiliariaMap = imagenService.getImagenesPorEntidadBulk(EntidadImagen.INMOBILIARIA, inmobiliariaIds);
		
		for(InmuebleImagenDTO dto: dtos) {
			dto.setImagenes(imagenesInmuebleMap.getOrDefault(dto.getId(), List.of()));
			dto.getInmobiliaria().setImagenes(imagenesInmobiliariaMap.getOrDefault(dto.getInmobiliaria().getId(), List.of()));
		}
		
		return dtos;
	}
	
	//Este metodo a partir de un id de Usuario devuelve todos los ids de sus inmuebles favoritos
		public List<InmuebleIdDTO> findFavoritosId(Long usuarioId){
			Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(() -> new EntityNotFoundException("El usuario con id " + usuarioId + " no existe"));
			List<Inmueble> inmuebles = new ArrayList<>(usuario.getInmueblesFavoritos());
			
			return inmuebleMapper.toDtoIdList(inmuebles);
		}
}
