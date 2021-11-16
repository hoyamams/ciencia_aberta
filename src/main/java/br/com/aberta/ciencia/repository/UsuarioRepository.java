package br.com.aberta.ciencia.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.aberta.ciencia.model.Usuario;

@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, Long> {

	Usuario findByEmailUsuario(String emailUsuario);

}
