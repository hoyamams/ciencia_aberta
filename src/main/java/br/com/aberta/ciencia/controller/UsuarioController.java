package br.com.aberta.ciencia.controller;

import java.io.IOException;
import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.aberta.ciencia.model.TipoUsuario;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;


import br.com.aberta.ciencia.exception.ResourceNotFoundException;
import br.com.aberta.ciencia.model.Usuario;
import br.com.aberta.ciencia.service.UsuarioService;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
	/** Método para listar todos os usuários - módulo disponivel para o administrador*/
	@GetMapping("/usuario_list")
	public List<Usuario> getAllUsuario(){
		return usuarioService.findAll();
	}
	
	/** Método para localizar um usuário - módulo disponivel para o administrador*/
	@GetMapping("/usuario_busca/{id}")
	public ResponseEntity<Usuario> getUsuarioById(@PathVariable(value = "id") Long usuarioId) 
		throws ResourceNotFoundException{
		 Usuario usuario = usuarioService.findById(usuarioId);
		 return ResponseEntity.ok().body(usuario);
	}
	

	
	/** Método para alterar os dados de um usuário - módulo disponivel para o administrador*/
	@PutMapping("usuario_update/{id}")
	public ResponseEntity<Usuario> UpdateUsuario(@PathVariable(value = "id") Long usuarioId,
												 @RequestBody Usuario usuarioDetails) throws ResourceNotFoundException {
	 
	    final Usuario updateUsuario = usuarioService.update(usuarioId, usuarioDetails);
	    return ResponseEntity.ok(updateUsuario);
	    
	  }
		
	/** Método cadastra usuário - acessivel para todos os usuários
	 * @return*/

	/** Método cadastra usuário - acessivel para todos os usuários*/
/*	@PostMapping("/usuario")
	public Usuario createUsuario(@RequestBody Usuario usuario) {
		return usuarioService.save(usuario);
	}*/


	@PostMapping("/usuario")
	public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario usuario) {
		URI uri = java.net.URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/usuario").toUriString());
		return ResponseEntity.created(uri).body(usuarioService.save(usuario));
	}


	@GetMapping("/token/refresh")
	public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String authorizationHeader = request.getHeader(AUTHORIZATION);
		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
			try {

				String refresh_token = authorizationHeader.substring("Bearer ".length());
				Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
				JWTVerifier verifier = JWT.require(algorithm).build();
				DecodedJWT decodedJWT = verifier.verify(refresh_token);
				String emailUsuario = decodedJWT.getSubject(); //email usuario
				Usuario user = usuarioService.getUsuario(emailUsuario);

				String access_token = JWT.create()
						.withSubject(user.getEmailUsuario())
						.withExpiresAt(new Date(System.currentTimeMillis()+ 10 * 60 * 1000))
						.withIssuer(request.getRequestURL().toString())
						.withClaim("tipoUsuario", user.getTipoUsuario().stream().map(TipoUsuario::getDescricaoTipoUsuario).collect(Collectors.toList()))
						.sign(algorithm);

				Map<String,String> tokens = new HashMap<>();
				tokens.put("access_token", access_token);
				tokens.put("refresh_token", refresh_token);
				response.setContentType(APPLICATION_JSON_VALUE);
				new ObjectMapper().writeValue(response.getOutputStream(),tokens);

			}catch (Exception exception) {

				response.setHeader("error",exception.getMessage());
				response.setStatus(FORBIDDEN.value());

				Map<String,String> error = new HashMap<>();
				error.put("error_message", exception.getMessage());
				response.setContentType(APPLICATION_JSON_VALUE);
				new ObjectMapper().writeValue(response.getOutputStream(),error);
			}
		}else {
			throw new RuntimeException("Refresh token is missing");
		}

	}
	
}
