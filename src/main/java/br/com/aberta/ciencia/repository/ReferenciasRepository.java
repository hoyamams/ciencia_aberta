package br.com.aberta.ciencia.repository;

import br.com.aberta.ciencia.model.Glossario;
import br.com.aberta.ciencia.model.Referencias;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ReferenciasRepository extends MongoRepository<Referencias, Long> {


    List<Referencias> findAllByOrderByDescricaoReferencias();

}
