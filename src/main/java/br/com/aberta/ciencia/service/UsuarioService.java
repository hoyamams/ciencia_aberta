package br.com.aberta.ciencia.service;

import java.util.*;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.message.StringFormattedMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import br.com.aberta.ciencia.exception.ResourceNotFoundException;
import br.com.aberta.ciencia.model.TipoUsuario;
import br.com.aberta.ciencia.model.Usuario;
import br.com.aberta.ciencia.repository.UsuarioRepository;




@RequiredArgsConstructor
@Transactional
@Slf4j
@Service
public class UsuarioService implements UserDetailsService {
	@Autowired
	private SequenceGeneratorService sequenceGeneratorService;

	@Autowired
	private UsuarioRepository usuarioRepository;


	private TipoUsuario tipoUsuario;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String emailUsuario) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByEmailUsuario(emailUsuario);
		if (usuario==null){
			log.error("User not found in the database");
			throw new UsernameNotFoundException("User not found in the database");
		}else{
			log.info("User found in the database:{}",emailUsuario);
		}
		//int permissao= usuario.getTipoUsuario();
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
		usuario.getTipoUsuario().forEach(tipo -> {
			authorities.add(new SimpleGrantedAuthority(tipo.getDescricaoTipoUsuario()));
		});
		//authorities.add(new SimpleGrantedAuthority(descricaoTipo));
		return new User(usuario.getEmailUsuario(),usuario.getSenhaUsuario(),authorities);
	}


	/* Salva cadastro usuario */
	public Usuario save(Usuario usuario) {

		usuario.setId(sequenceGeneratorService.generateSequence(Usuario.SEQUENCE_NAME));
		usuario.setDataCadastroUsuario(new Date());
		usuario.setTipoUsuario(Collections.singleton(TipoUsuario.COMUM));
		usuario.setSenhaUsuario(passwordEncoder.encode(usuario.getSenhaUsuario()));
		System.out.println(usuario);
		return usuarioRepository.save(usuario);
	}

	public Usuario getUsuario(String emailUsuario) {
		return usuarioRepository.findByEmailUsuario(emailUsuario);
	}


	/* Busca por todos os usuario */
	public List<Usuario> findAll() {
		return usuarioRepository.findAll();
	}

	/* Busca por um usuario especifico */
	public Usuario findById(Long usuarioId) throws ResourceNotFoundException {
		Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(() -> new ResourceNotFoundException("Usuário não localizado."));
		return usuario;
	}



	/* Atualiza usuario */
	public Usuario update(Long usuarioId, Usuario usuarioDetails) throws ResourceNotFoundException {
		
		Usuario usuarioUpdate = findById(usuarioId);
		usuarioUpdate.setEmailUsuario(usuarioDetails.getEmailUsuario());
		usuarioUpdate.setInstituicaoUsuario(usuarioDetails.getInstituicaoUsuario());
		usuarioUpdate.setNomeUsuario(usuarioDetails.getNomeUsuario());
		usuarioUpdate.setOcupacaoUsuario(usuarioDetails.getOcupacaoUsuario());
		usuarioUpdate.setPermissaoDivulgacaoDadosUsuario(usuarioDetails.getPermissaoDivulgacaoDadosUsuario());
		//usuarioUpdate.setTipoUsuario(usuarioDetails.getTipoUsuario());
		usuarioUpdate.setSenhaUsuario(usuarioDetails.getSenhaUsuario());
		usuarioUpdate.setDataAlteracaoUsuario(new Date());
		return usuarioRepository.save(usuarioUpdate);
		//return usuarioUpdate;
	}




	

}
