package br.com.aberta.ciencia.repository;

import br.com.aberta.ciencia.model.CategoriaPergunta;
import br.com.aberta.ciencia.model.Perguntas;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PerguntasRepository extends MongoRepository<Perguntas,Long> {

   List<Perguntas> findByCategoria_IdOrderById(Long idCategoria);




}
