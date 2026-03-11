package com.ipartek.springboot.backend.apirest.elpisito.security;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.ipartek.springboot.backend.apirest.elpisito.entities.Usuario;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {
	
	public static final String SECRET = "LKFSLSLSFJ998383LLSLSLSLSLLSSLKGSL111000"; //minimo 32 caracteres
	
	public String generateToken(Usuario usuario) {
		Key key = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
		
		return Jwts.builder()
				.setSubject(usuario.getNombre())
				.claim("id", usuario.getId())
				.claim("rol", usuario.getRol())
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + 15 * 60 * 1000))
				.signWith(key, SignatureAlgorithm.HS256) //Por defecto lo hace con HS256
				.compact();
	}
	
	public Claims getClaims(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(SECRET.getBytes())
				.build()
				.parseClaimsJws(token)
				.getBody();
	}

}
