package com.ipartek.springboot.backend.apirest.elpisito.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class SecurityConfig {

	@Autowired JWTValidationFilter jwtFilter;
	@Autowired JWTAuthentificationEntryPoint entryPoint;//401
	@Autowired JWTAccesDeniedHandler deniedHandler;//403
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		
		//En una API Rest no existen las sesiones!!!
		http.sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		
		http.authorizeHttpRequests( auth -> {
									//Podemos hacer infinitas conbinaciones, utilizando el SELECTOR y el AUTORIZADOR
									//Podemos "jugar" con el selector: anyRequest(), requestMatchers()...
									//Podemos "jugar" con el autorizador: permitAll(), authenticated(), hasRole(), hasAnyRole()
									//Haciendo conbinaciones entre selector y autorizador crearemos autorizaciones "a la carta"
									//Es obligatorio para Spring Security que en la BBDD este anotado el Rol como "ROLE_LOQUESEA"
									//y en este metodo "LOQUESEA"
									//auth.requestMatchers("/api/provincias").hasRole("USUARIO");
									//auth.requestMatchers("/api/tipos").hasAnyRole("SUPERADMIN","ADMIN");
			
									auth.requestMatchers(HttpMethod.POST,"/api/inmueble").hasAnyRole("SUPERADMIN","ADMIN");
									auth.requestMatchers(HttpMethod.PUT,"/api/inmueble").hasAnyRole("SUPERADMIN","ADMIN");
									auth.requestMatchers(HttpMethod.GET,"/api/inmuebles").hasAnyRole("SUPERADMIN","ADMIN");
									auth.requestMatchers(HttpMethod.DELETE,"/api/inmueble/**").hasAnyRole("SUPERADMIN","ADMIN");
									//auth.requestMatchers(HttpMethod.GET,"/api/inmuebles-inmobiliaria/**").hasAnyRole("SUPER","ADMIN","USER");
									
									auth.requestMatchers(HttpMethod.POST,"/api/poblacion").hasAnyRole("SUPERADMIN","ADMIN");
									auth.requestMatchers(HttpMethod.PUT,"/api/poblacion").hasAnyRole("SUPERADMIN","ADMIN");
									auth.requestMatchers(HttpMethod.GET,"/api/poblaciones").hasAnyRole("SUPERADMIN","ADMIN");
									auth.requestMatchers(HttpMethod.DELETE,"/api/poblacion/**").hasAnyRole("SUPERADMIN","ADMIN");
									//auth.requestMatchers(HttpMethod.GET,"/api/poblaciones-activas").hasAnyRole("SUPER","ADMIN");//lo necesitamos para el finder
							
									
									auth.requestMatchers(HttpMethod.POST,"/api/provincia").hasAnyRole("SUPERADMIN","ADMIN");
									auth.requestMatchers(HttpMethod.PUT,"/api/provincia").hasAnyRole("SUPERADMIN","ADMIN");
									auth.requestMatchers(HttpMethod.GET,"/api/provincias").hasAnyRole("SUPERADMIN","ADMIN");
									auth.requestMatchers(HttpMethod.DELETE,"/api/provincia/**").hasAnyRole("SUPERADMIN","ADMIN");
									auth.requestMatchers(HttpMethod.GET,"/api/provincias-activas").hasAnyRole("SUPERADMIN","ADMIN");
						
									
									auth.requestMatchers(HttpMethod.POST,"/api/tipo").hasAnyRole("SUPERADMIN","ADMIN");
									auth.requestMatchers(HttpMethod.PUT,"/api/tipo").hasAnyRole("SUPERADMIN","ADMIN");
									auth.requestMatchers(HttpMethod.GET,"/api/tipos").hasAnyRole("SUPERADMIN","ADMIN");
									auth.requestMatchers(HttpMethod.DELETE,"/api/tipo/**").hasAnyRole("SUPERADMIN","ADMIN");
									//auth.requestMatchers(HttpMethod.GET,"/api/tipos-activos").hasAnyRole("SUPER","ADMIN");//lo necesitamos para el finder
						
									
									auth.requestMatchers(HttpMethod.POST,"/api/operacion").hasAnyRole("SUPERADMIN","ADMIN");
									auth.requestMatchers(HttpMethod.PUT,"/api/operacion").hasAnyRole("SUPERADMIN","ADMIN");
									auth.requestMatchers(HttpMethod.GET,"/api/operaciones").hasAnyRole("SUPERADMIN","ADMIN");
									auth.requestMatchers(HttpMethod.DELETE,"/api/operacion/**").hasAnyRole("SUPERADMIN","ADMIN");
									//auth.requestMatchers(HttpMethod.GET,"/api/operaciones-activas").hasAnyRole("SUPER","ADMIN");//lo necesitamos para el finder
									
									
									//el POST no existe porque no podemos restringir que una persona se de de alta...
									auth.requestMatchers(HttpMethod.PUT,"/api/usuario").hasRole("SUPERADMIN");
									//auth.requestMatchers(HttpMethod.GET,"/api/usuario/**").hasRole("SUPER");
									auth.requestMatchers(HttpMethod.GET,"/api/usuarios").hasRole("SUPERADMIN");
									auth.requestMatchers(HttpMethod.DELETE,"/api/usuario/**").hasAnyRole("SUPERADMIN","ADMIN");
									auth.requestMatchers(HttpMethod.GET,"/api/usuarios-activos").hasRole("SUPERADMIN");
									
									auth.requestMatchers(HttpMethod.POST,"/api/tematica").hasAnyRole("SUPERADMIN","ADMIN");
									auth.requestMatchers(HttpMethod.PUT,"/api/tematica").hasAnyRole("SUPERADMIN","ADMIN");
									auth.requestMatchers(HttpMethod.GET,"/api/tematicas").hasAnyRole("SUPERADMIN","ADMIN");
									auth.requestMatchers(HttpMethod.GET,"/api/tematicas-activas").hasAnyRole("SUPERADMIN","ADMIN");
									auth.requestMatchers(HttpMethod.GET,"/api/tematica/**").hasAnyRole("SUPERADMIN","ADMIN");
									auth.requestMatchers(HttpMethod.DELETE,"/api/tematica/**").hasAnyRole("SUPERADMIN","ADMIN");
									auth.requestMatchers(HttpMethod.GET,"/api/actualizar-tematica/**").hasRole("SUPERADMIN");
									
									auth.requestMatchers(HttpMethod.POST,"/api/banner").hasAnyRole("SUPERADMIN","ADMIN");
									auth.requestMatchers(HttpMethod.PUT,"/api/banner").hasAnyRole("SUPERADMIN","ADMIN");
									auth.requestMatchers(HttpMethod.GET,"/api/banners").hasAnyRole("SUPERADMIN","ADMIN");
									auth.requestMatchers(HttpMethod.DELETE,"/api/banner/**").hasAnyRole("SUPERADMIN","ADMIN");
									auth.requestMatchers(HttpMethod.GET,"/api/banner/**").hasAnyRole("SUPERADMIN","ADMIN");
									
									auth.requestMatchers(HttpMethod.POST,"/api/banner-carousel").hasAnyRole("SUPERADMIN","ADMIN");
									auth.requestMatchers(HttpMethod.PUT,"/api/banner-carousel").hasAnyRole("SUPERADMIN","ADMIN");
									auth.requestMatchers(HttpMethod.GET,"/api/banners-carousel").hasAnyRole("SUPERADMIN","ADMIN");
									auth.requestMatchers(HttpMethod.DELETE,"/api/banner-carousel/**").hasAnyRole("SUPERADMIN","ADMIN");
									auth.requestMatchers(HttpMethod.GET,"/api/banners-carousel/**").hasAnyRole("SUPERADMIN","ADMIN");
									
									auth.requestMatchers(HttpMethod.POST,"/api/pagina").hasAnyRole("SUPERADMIN","ADMIN");
									auth.requestMatchers(HttpMethod.PUT,"/api/pagina").hasAnyRole("SUPERADMIN","ADMIN");
									auth.requestMatchers(HttpMethod.GET,"/api/paginas").hasAnyRole("SUPERADMIN","ADMIN");
									auth.requestMatchers(HttpMethod.DELETE,"/api/pagina/**").hasAnyRole("SUPERADMIN","ADMIN");
									auth.requestMatchers(HttpMethod.GET,"/api/paginas/**").hasAnyRole("SUPERADMIN","ADMIN");
									
									auth.requestMatchers(HttpMethod.POST,"/api/pagina-banner").hasAnyRole("SUPERADMIN","ADMIN");
									auth.requestMatchers(HttpMethod.DELETE,"/api/pagina-banner").hasAnyRole("SUPERADMIN","ADMIN");
									//auth.requestMatchers(HttpMethod.GET,"/api/banners-pagina/**").hasAnyRole("SUPERADMIN","ADMIN");
									//auth.requestMatchers(HttpMethod.GET,"/api/bannersid-pagina/**").hasAnyRole("SUPERADMIN","ADMIN");
									
									auth.requestMatchers(HttpMethod.POST,"/api/tematica-bannercarousel").hasAnyRole("SUPERADMIN","ADMIN");
									auth.requestMatchers(HttpMethod.DELETE,"/api/tematica-bannercarousel").hasAnyRole("SUPERADMIN","ADMIN");
									//auth.requestMatchers(HttpMethod.GET,"/api/bannerscarousel-tematica/**").hasAnyRole("SUPERADMIN","ADMIN");
									//auth.requestMatchers(HttpMethod.GET,"/api/bannerscarouselid-tematica/**").hasAnyRole("SUPERADMIN","ADMIN");
									
									//Esta linea de codigo se suele poner siempre al final
									//porque significa que todo lo que no hayamos "restringido arriba" esta permitido
									auth.anyRequest().permitAll();
									
									});
		
		//Indicamos que objetos manejan las excepciones Spring Security
		http.exceptionHandling(ex -> 
							ex.authenticationEntryPoint(entryPoint)//401
							.accessDeniedHandler(deniedHandler));//403
		
		//Indicamos a Spring Security que en lugar del "filtro chain" debe ejecutar
		//tu filtro JWT Personalizado. En nuestro caso el JWTValidationFilter.
		//Spring Security funciona con una cadena de filtros (Security Filter Chain)
		//Cada uno de estos filtros tiene una responsabilidad:
		//1) autentificacion basica
		//2) login de formularios
		//3) manejo sesiones
		//Nuestro filtro "personalizado" es un filtro que valida el token JWT
		
		//Un ejemplo poco comun en una API Rest con JWT...
		//http.addFilterAfter(jwtFilter, BasicAuthenticationFilter.class);
		
		//Lo que estamos diciendo aquí es:
		//"ejecuta el jwtFilter ANTES de UsenamePasswordAuthentificationFilter.class"
		//El UsenamePasswordAuthentificationFilter es el filtro que maneja el login clasico
		//con usuario y contraseña
		//¿Por que before?
		//1) Queremos que el JWT sea validado antes de que Spring intente autentificar por usuario/password
		//2) Si el JWT es valido ya se establece la utentificacion en el Security Context
		//Request -> jwtFilter -> UsernamePasswordAuthenticationFilter -> resto de filtros
		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);//Esta linea es muy comun en API Rest con JWT
		
		//http.cors(Customizer.withDefaults());
		http.cors(cors -> cors.configurationSource(corsConfigurationSource()));
		
		//El CSRF (Cross Site Request Forgery) o falsificacion en peticiones de sitios cruzados,
		//es un tipo de EXPLOIT malicioso de un sitio Web en el que
		//comandos no autorizados son transmitidos por un usuario (sin que el lo sepa)
		//en el cual el sitio Web confia
		//Desactivamos el CSRF porque nuestra API usa Tokens JWT
		http.csrf(csrf -> csrf.disable());
		
		return http.build();
		
	}
	
	//Una aplicacionde Spring Security SOLO PUEDE TENER UN PASSWORD ENCODER!!
	//y ademas ES OBLIGATORIO QUE LO TENGA
	//Este password encoder debe estar anotado como @Bean
	//Al estar anotado como @Bean esta permanentemente activo en el contexto de Spring
	//y, de esta forma, puede ser utilizado sin necesidad de hacer una instancia de su clase
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration configuracion) throws Exception {
		return configuracion.getAuthenticationManager();
	}
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowedOrigins(List.of("http://localhost:4200"));//Dominio desde donde se sirve la Web y otros dominios que nos interesan
		config.setAllowedMethods(List.of("GET","POST","PUT","PATCH","DELETE")); // Solo podemos utilizar en la peticiones los "verbos" (metodos) especificados
		config.setAllowedHeaders(List.of("*")); //Permitimos todos los headers (es importante por que aqui llegan los Tokens)
		config.setAllowCredentials(true);//Es fundamental para que CORS deje que se inicie el proceso de logeo
		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);
		
		return source;
		
	}
	
}

/*Dejamos este ejemplo como configuracion Cors un poco mas detallada
 * @Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowedOrigins(List.of("http://localhost:4200"));//Dominio desde donde se sirve la Web y otros dominios que nos interesan
		config.setAllowedMethods(List.of("GET","POST","PUT","PATCH","DELETE")); // Solo podemos utilizar en la peticiones los "verbos" (metodos) especificados
		config.setAllowedHeaders(List.of("Authorization","Cache-Control","Content-Type"));
		config.setAllowCredentials(true);
		config.setExposedHeader(List.of("Authorization");//De esta manera, podemos acceder a la informacion de "Authorization"
		//en un interceptor de Angular
		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);
		
		return source;
		
	}
 */
