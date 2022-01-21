package br.com.aberta.ciencia.repository;

import br.com.aberta.ciencia.model.Apresentacao;
import br.com.aberta.ciencia.model.Glossario;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ApresentacaoRepository extends MongoRepository<Apresentacao, Long> {


    List<Apresentacao> findAllByOrderById();

}
