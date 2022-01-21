package br.com.aberta.ciencia.service;

import br.com.aberta.ciencia.exception.ResourceNotFoundException;
import br.com.aberta.ciencia.model.CategoriaPergunta;
import br.com.aberta.ciencia.model.GrauMaturidade;
import br.com.aberta.ciencia.repository.CategoriaPerguntaRepository;
import br.com.aberta.ciencia.repository.GrauMaturidadeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class GrauMaturidadeService {

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    private GrauMaturidadeRepository grauMaturidadeRepository;

    public GrauMaturidadeService(GrauMaturidadeRepository grauMaturidadeRepository){
        this.grauMaturidadeRepository = grauMaturidadeRepository;
    }

    //Salva Grau Maturidade
    public GrauMaturidade save(GrauMaturidade grauMaturidade){
        grauMaturidade.setId(sequenceGeneratorService.generateSequence(GrauMaturidade.SEQUENCE_NAME));
        return grauMaturidadeRepository.save(grauMaturidade);
    }

    //lista todas categorias
    public List<GrauMaturidade> findAll(){
        return grauMaturidadeRepository.findAllByOrderById();
    }

//busca id categoria
    public GrauMaturidade findById(Long grauMaturidadeId) throws ResourceNotFoundException{
        GrauMaturidade grauMaturidade = grauMaturidadeRepository.findById(grauMaturidadeId).orElseThrow(()->
                new ResourceNotFoundException("Grau Maturidade não encontrado"));
        return grauMaturidade;
    }

    //Atualiza categoria
    public GrauMaturidade update(Long grauMaturidadeId, GrauMaturidade grauMaturidadeDetais) throws ResourceNotFoundException {
        GrauMaturidade grauMaturidadeUpdate = findById(grauMaturidadeId);
        grauMaturidadeUpdate.setDescricaoGrauMaturidade(grauMaturidadeDetais.getDescricaoGrauMaturidade());
        grauMaturidadeUpdate.setNivelGrauMaturidade(grauMaturidadeDetais.getNivelGrauMaturidade());
        grauMaturidadeUpdate.setPontuacaoMaximaGrauMaturidade(grauMaturidadeDetais.getPontuacaoMaximaGrauMaturidade());
        grauMaturidadeUpdate.setPontuacaoMinimaGrauMaturidade(grauMaturidadeDetais.getPontuacaoMinimaGrauMaturidade());
        grauMaturidadeUpdate.setPorcentagemGrauMaturidade(grauMaturidadeDetais.getPorcentagemGrauMaturidade());
        return grauMaturidadeRepository.save(grauMaturidadeUpdate);
    }

    //Deleta categoria
    public ResponseEntity <?> delete(Long grauMaturidadeId) throws ResourceNotFoundException {
        return grauMaturidadeRepository.findById(grauMaturidadeId)
                .map(record -> {
                    grauMaturidadeRepository.deleteById(grauMaturidadeId);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

    public GrauMaturidade findByNivelMaturidade(Integer pontuacaoMinima, Integer pontuacaoMaxima) {
        return grauMaturidadeRepository.findAllByPontuacaoMaximaGrauMaturidadeGreaterThanEqualAndPontuacaoMinimaGrauMaturidadeLessThanEqual(pontuacaoMinima,pontuacaoMaxima);
    }
}
