package br.com.aberta.ciencia.repository;

import br.com.aberta.ciencia.model.CategoriaPergunta;
import br.com.aberta.ciencia.model.Perguntas;
import br.com.aberta.ciencia.model.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CategoriaPerguntaRepository extends MongoRepository<CategoriaPergunta, Long> {


    List<CategoriaPergunta> findAllByOrderById();

}
