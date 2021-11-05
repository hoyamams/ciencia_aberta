package br.com.aberta.ciencia.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import br.com.aberta.ciencia.exception.ResourceNotFoundException;
import br.com.aberta.ciencia.model.Usuario;
import br.com.aberta.ciencia.service.UsuarioService;

@RestController
@RequestMapping("/")
public class UsuarioController {
	
	private UsuarioService usuarioService;
	
	@Autowired
	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
	/** Método para listar todos os usuários - módulo disponivel para o administrador*/
	@GetMapping("/usuario_list")
	public List<Usuario> getAllUsuario(){
		return usuarioService.findAll();
	}
	
	/** Método para localizar um usuário - módulo disponivel para o administrador*/
	@GetMapping("/usuario_search/{id}")
	public ResponseEntity<Usuario> getUsuarioById(@PathVariable(value = "id") Long usuarioId) 
		throws ResourceNotFoundException{
		 Usuario usuario = usuarioService.findById(usuarioId);
		 return ResponseEntity.ok().body(usuario);
	}
	
	
	/** Método para realizar Login no sistema  */
	/*@GetMapping("/login")
	 public String loginUsuario(@RequestBody Usuario usuario){
		String logado;
		if (usuarioService.loginUsuario(usuario)) {
			logado = "deeeu";
		}else {
			logado = "ixiii";
		}
		
		return logado;
    }*/
	
	/** Método para alterar os dados de um usuário - módulo disponivel para o administrador*/
	@PutMapping("usuario_update/{id}")
	public ResponseEntity<Usuario> UpdateUsuario(@PathVariable(value = "id") Long usuarioId,
	  @Valid @RequestBody Usuario usuarioDetails) throws ResourceNotFoundException {
	 
	    final Usuario updateUsuario = usuarioService.update(usuarioId, usuarioDetails);
	    return ResponseEntity.ok(updateUsuario);
	    
	  }
		
	/** Método cadastra usuário - acessivel para todos os usuários*/
	@PostMapping("/usuario")
	public Usuario createUsuario(@Valid @RequestBody Usuario usuario) {
		return usuarioService.save(usuario);
	}

	
}
