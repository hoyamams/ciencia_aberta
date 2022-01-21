package br.com.aberta.ciencia.service;

import br.com.aberta.ciencia.exception.ResourceNotFoundException;
import br.com.aberta.ciencia.model.CategoriaPergunta;
import br.com.aberta.ciencia.model.Glossario;
import br.com.aberta.ciencia.repository.CategoriaPerguntaRepository;
import br.com.aberta.ciencia.repository.GlossarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class GlossarioService {

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    private GlossarioRepository glossarioRepository;

    public GlossarioService(GlossarioRepository glossarioRepository){
        this.glossarioRepository = glossarioRepository;
    }

    //Salva glossario
    public Glossario save(Glossario glossario){
        glossario.setId(sequenceGeneratorService.generateSequence(Glossario.SEQUENCE_NAME));
        return glossarioRepository.save(glossario);
    }

    //lista todas categorias
    public List<Glossario> findAll(){
        return glossarioRepository.findAllByOrderById();
    }

//busca id categoria
    public Glossario findById(Long glossarioId) throws ResourceNotFoundException{
        Glossario glossario = glossarioRepository.findById(glossarioId).orElseThrow(()->
                new ResourceNotFoundException("glossario não encontrada"));
        return glossario;
    }

    //Atualiza categoria
    public Glossario update(Long glossarioId, Glossario glossarioDetais) throws ResourceNotFoundException {
        Glossario glossarioUpdate = findById(glossarioId);
        glossarioUpdate.setDescricaoGlossario(glossarioDetais.getDescricaoGlossario());
        return glossarioRepository.save(glossarioUpdate);
    }

    //Deleta categoria
    public ResponseEntity <?> delete(Long glossarioId) throws ResourceNotFoundException {
        return glossarioRepository.findById(glossarioId)
                .map(record -> {
                    glossarioRepository.deleteById(glossarioId);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
