package br.com.aberta.ciencia.service;

import br.com.aberta.ciencia.exception.ResourceNotFoundException;
import br.com.aberta.ciencia.model.CategoriaPergunta;
import br.com.aberta.ciencia.model.Usuario;
import br.com.aberta.ciencia.repository.CategoriaPerguntaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class CategoriaPerguntaService {

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    private CategoriaPerguntaRepository categoriaPerguntaRepository;

    public CategoriaPerguntaService (CategoriaPerguntaRepository categoriaPerguntaRepository){
        this.categoriaPerguntaRepository = categoriaPerguntaRepository;
    }

    //Salva CAtegoria
    public CategoriaPergunta save(CategoriaPergunta categoriaPergunta){
        categoriaPergunta.setId(sequenceGeneratorService.generateSequence(CategoriaPergunta.SEQUENCE_NAME));
        return categoriaPerguntaRepository.save(categoriaPergunta);
    }

    //lista todas categorias
    public List<CategoriaPergunta> findAll(){
        return categoriaPerguntaRepository.findAllByOrderById();
    }

//busca id categoria
    public CategoriaPergunta findById(Long categoriaId) throws ResourceNotFoundException{
        CategoriaPergunta categoriaPergunta = categoriaPerguntaRepository.findById(categoriaId).orElseThrow(()->
                new ResourceNotFoundException("Categoria não encontrada"));
        return categoriaPergunta;
    }

    //Atualiza categoria
    public CategoriaPergunta update(Long categoriaId, CategoriaPergunta categoriaDetais) throws ResourceNotFoundException {
        CategoriaPergunta categoriaUpdate = findById(categoriaId);
        categoriaUpdate.setDescricaoCategoriaPergunta(categoriaDetais.getDescricaoCategoriaPergunta());
        categoriaUpdate.setPontosPossiveisCategoriaPergunta(categoriaDetais.getPontosPossiveisCategoriaPergunta());
        return categoriaPerguntaRepository.save(categoriaUpdate);
    }

    //Deleta categoria
    public ResponseEntity <?> delete(Long categoriaId) throws ResourceNotFoundException {
        return categoriaPerguntaRepository.findById(categoriaId)
                .map(record -> {
                    categoriaPerguntaRepository.deleteById(categoriaId);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
