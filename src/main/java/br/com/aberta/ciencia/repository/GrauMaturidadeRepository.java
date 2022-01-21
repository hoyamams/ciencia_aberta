package br.com.aberta.ciencia.repository;

import br.com.aberta.ciencia.model.CategoriaPergunta;
import br.com.aberta.ciencia.model.GrauMaturidade;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface GrauMaturidadeRepository extends MongoRepository<GrauMaturidade, Long> {
    List<GrauMaturidade> findAllByOrderById();

    GrauMaturidade findAllByPontuacaoMaximaGrauMaturidadeGreaterThanEqualAndPontuacaoMinimaGrauMaturidadeLessThanEqual(Integer pontuacaoMinima, Integer pontuacaoMaxima);
}
