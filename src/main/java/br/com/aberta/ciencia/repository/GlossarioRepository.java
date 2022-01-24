package br.com.aberta.ciencia.repository;

import br.com.aberta.ciencia.model.CategoriaPergunta;
import br.com.aberta.ciencia.model.Glossario;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface GlossarioRepository extends MongoRepository<Glossario, Long> {


    List<Glossario> findAllByOrderByDescricaoGlossario();

}
