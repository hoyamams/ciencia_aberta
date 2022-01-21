package br.com.aberta.ciencia.repository;

import br.com.aberta.ciencia.model.GrauMaturidade;
import br.com.aberta.ciencia.model.Perguntas;
import br.com.aberta.ciencia.model.Respostas;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RespostasRepository extends MongoRepository<Respostas,Long> {


    List<Respostas> findAllByOrderByGrauMaturidadeUsuario();

    Respostas findByIdUsuario(Long idUsuario);


}
