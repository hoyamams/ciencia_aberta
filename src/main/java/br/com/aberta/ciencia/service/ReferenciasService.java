package br.com.aberta.ciencia.service;

import br.com.aberta.ciencia.exception.ResourceNotFoundException;
import br.com.aberta.ciencia.model.Referencias;
import br.com.aberta.ciencia.repository.ReferenciasRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class ReferenciasService {

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    private ReferenciasRepository referenciasRepository;

    public ReferenciasService(ReferenciasRepository referenciasRepository){
        this.referenciasRepository = referenciasRepository;
    }

    //Salva referencias
    public Referencias save(Referencias referencias){
        referencias.setId(sequenceGeneratorService.generateSequence(Referencias.SEQUENCE_NAME));
        return referenciasRepository.save(referencias);
    }

    //lista todas categorias
    public List<Referencias> findAll(){
        return referenciasRepository.findAllByOrderByDescricaoReferencias();
    }

//busca id categoria
    public Referencias findById(Long referenciasId) throws ResourceNotFoundException{
        Referencias referencias = referenciasRepository.findById(referenciasId).orElseThrow(()->
                new ResourceNotFoundException("referencias não encontrada"));
        return referencias;
    }

    //Atualiza categoria
    public Referencias update(Long referenciasId, Referencias referenciasDetais) throws ResourceNotFoundException {
        Referencias referenciasUpdate = findById(referenciasId);
        referenciasUpdate.setDescricaoReferencias(referenciasDetais.getDescricaoReferencias());
        return referenciasRepository.save(referenciasUpdate);
    }

    //Deleta categoria
    public ResponseEntity <?> delete(Long referenciasId) throws ResourceNotFoundException {
        return referenciasRepository.findById(referenciasId)
                .map(record -> {
                    referenciasRepository.deleteById(referenciasId);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
