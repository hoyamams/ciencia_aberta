package br.com.aberta.ciencia.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import br.com.aberta.ciencia.exception.ResourceNotFoundException;
import br.com.aberta.ciencia.model.TipoUsuario;
import br.com.aberta.ciencia.model.Usuario;
import br.com.aberta.ciencia.repository.UsuarioRepository;

@Service
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
public class UsuarioService{
	
	@Autowired
	private SequenceGeneratorService sequenceGeneratorService;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}
	
	/* Busca por todos os usuario */
	public List<Usuario> findAll() {
		return usuarioRepository.findAll();
	}

	/* Busca por todos um usuario especifico */
	public Usuario findById(Long usuarioId) throws ResourceNotFoundException {
		Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(() -> new ResourceNotFoundException("Usuário não localizado."));
		return usuario;
	}

	/* Salva cadastro usuario */
	public Usuario save(Usuario usuario) {

		usuario.setId(sequenceGeneratorService.generateSequence(Usuario.SEQUENCE_NAME));
		usuario.setDataCadastroUsuario(new Date());
		usuario.setTipoUsuario(TipoUsuario.COMUM);
		usuario.setSenhaUsuario(passwordEncoder.encode(usuario.getSenhaUsuario()));
		//usuario.setLogadoUsuario(false);
		Usuario newUsuario = usuarioRepository.save(usuario);
		return newUsuario;
	}


	/* Atualiza usuario */
	public Usuario update(Long usuarioId, Usuario usuarioDetails) throws ResourceNotFoundException {
		
		Usuario usuarioUpdate = findById(usuarioId);
		usuarioUpdate.setEmailUsuario(usuarioDetails.getEmailUsuario());
		usuarioUpdate.setInstituicaoUsuario(usuarioDetails.getInstituicaoUsuario());
		usuarioUpdate.setNomeUsuario(usuarioDetails.getNomeUsuario());
		usuarioUpdate.setOcupacaoUsuario(usuarioDetails.getOcupacaoUsuario());
		usuarioUpdate.setPermissaoDivulgacaoDadosUsuario(usuarioDetails.getPermissaoDivulgacaoDadosUsuario());
		usuarioUpdate.setTipoUsuario(usuarioDetails.getTipoUsuario());
		usuarioUpdate.setSenhaUsuario(usuarioDetails.getSenhaUsuario());
		usuarioUpdate.setDataAlteracaoUsuario(new Date());
		return usuarioUpdate;
	}

	/* Busca por email e senha para validação de ususario */
	/*public boolean loginUsuario(Usuario usuario)  {
		List<Usuario> usuarios = usuarioRepository.findAll();
		for (Usuario todos: usuarios) {
			if (todos.equals(usuario)) {
				usuario.setLogadoUsuario(true);
				usuarioRepository.save(usuario);
				return true;
			}
		}
		return false;
	}*/

	

}
