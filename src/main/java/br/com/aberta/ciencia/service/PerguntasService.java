package br.com.aberta.ciencia.service;

import br.com.aberta.ciencia.exception.ResourceNotFoundException;
import br.com.aberta.ciencia.model.*;
import br.com.aberta.ciencia.repository.PerguntasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class PerguntasService {

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    private PerguntasRepository perguntasRepository;

    private TipoPergunta tipoPergunta;

    private RespostasPossiveis respostasPossiveis;


    public Perguntas save(Perguntas perguntas){
        perguntas.setId(sequenceGeneratorService.generateSequence(Perguntas.SEQUENCE_NAME));
       // perguntas.setPerguntaTipoPergunta(TipoPergunta.valueOf(perguntas.setPerguntaTipoPergunta(););
        return perguntasRepository.save(perguntas);
    }

    public List<Perguntas> findAll() {
        return perguntasRepository.findAll(Sort.by(Sort.Direction.ASC, "categoria","_id"));
    }

    public Perguntas findById(Long perguntaId) throws ResourceNotFoundException {
        Perguntas pergunta = perguntasRepository.findById(perguntaId).orElseThrow(() -> new ResourceNotFoundException("Pergunta não localizado."));
        return pergunta;
    }


    /* Atualiza pergunta */
    public Perguntas update(Long perguntaId, Perguntas perguntaDetails) throws ResourceNotFoundException {

        Perguntas perguntaUpdate = findById(perguntaId);
        perguntaUpdate.setDescricaoPergunta(perguntaDetails.getDescricaoPergunta());
        perguntaUpdate.setRespostasPossiveisPergunta(perguntaDetails.getRespostasPossiveisPergunta());
        perguntaUpdate.setPerguntaTipoPergunta(TipoPergunta.valueOf(perguntaDetails.getPerguntaTipoPergunta()));
        perguntaUpdate.setCategoria(perguntaDetails.getCategoria());

        return perguntasRepository.save(perguntaUpdate);
        //return usuarioUpdate;
    }


    //Deleta pergunta
    public ResponseEntity<?> delete(Long perguntaId) throws ResourceNotFoundException {
        return perguntasRepository.findById(perguntaId)
                .map(record -> {
                    perguntasRepository.deleteById(perguntaId);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

   public List<Perguntas> findByPerguntasCategoria(Long idCategoria) {
        List<Perguntas> perguntasCategoria = perguntasRepository.findByCategoria_IdOrderById(idCategoria);
        return perguntasCategoria;
    }
}
