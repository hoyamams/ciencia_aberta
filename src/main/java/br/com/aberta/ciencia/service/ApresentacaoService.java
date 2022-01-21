package br.com.aberta.ciencia.service;

import br.com.aberta.ciencia.exception.ResourceNotFoundException;
import br.com.aberta.ciencia.model.Apresentacao;
import br.com.aberta.ciencia.repository.ApresentacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class ApresentacaoService {

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    private ApresentacaoRepository apresentacaoRepository;

    public ApresentacaoService(ApresentacaoRepository apresentacaoRepository){
        this.apresentacaoRepository = apresentacaoRepository;
    }

    //Salva apresentacao
    public Apresentacao save(Apresentacao apresentacao){
        apresentacao.setId(sequenceGeneratorService.generateSequence(Apresentacao.SEQUENCE_NAME));
        return apresentacaoRepository.save(apresentacao);
    }

    //lista todas categorias
    public List<Apresentacao> findAll(){
        return apresentacaoRepository.findAllByOrderById();
    }

//busca id categoria
    public Apresentacao findById(Long apresentacaoId) throws ResourceNotFoundException{
        Apresentacao apresentacao = apresentacaoRepository.findById(apresentacaoId).orElseThrow(()->
                new ResourceNotFoundException("apresentacao não encontrada"));
        return apresentacao;
    }

    //Atualiza categoria
    public Apresentacao update(Long apresentacaoId, Apresentacao apresentacaoDetais) throws ResourceNotFoundException {
        Apresentacao apresentacaoUpdate = findById(apresentacaoId);
        apresentacaoUpdate.setDescricaoApresentacao(apresentacaoDetais.getDescricaoApresentacao());
        return apresentacaoRepository.save(apresentacaoUpdate);
    }

    //Deleta categoria
    public ResponseEntity <?> delete(Long apresentacaoId) throws ResourceNotFoundException {
        return apresentacaoRepository.findById(apresentacaoId)
                .map(record -> {
                    apresentacaoRepository.deleteById(apresentacaoId);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
