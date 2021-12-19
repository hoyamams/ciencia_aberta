package br.com.aberta.ciencia.controller;


import br.com.aberta.ciencia.exception.ResourceNotFoundException;
import br.com.aberta.ciencia.model.CategoriaPergunta;
import br.com.aberta.ciencia.model.GrauMaturidade;
import br.com.aberta.ciencia.service.CategoriaPerguntaService;
import br.com.aberta.ciencia.service.GrauMaturidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/")
public class GrauMaturidadeController {

    @Autowired
    private GrauMaturidadeService grauMaturidadeService;


    //Insere grau
    @PostMapping("/grau_maturidade")
    public ResponseEntity<GrauMaturidade> createGrauMaturidade(@RequestBody GrauMaturidade grauMaturidade){
        URI uri = java.net.URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/grau_maturidade").toUriString());
        return ResponseEntity.created(uri).body(grauMaturidadeService.save(grauMaturidade));
    }

    //lista grau
    @GetMapping("/grau_maturidade_list")
    public List<GrauMaturidade> getAllGrauMaturidade(){
        return grauMaturidadeService.findAll();
    }

    //altera grau maturidade
    @PutMapping("grau_maturidade_update/{id}")
    public ResponseEntity<GrauMaturidade> UpdateGrauMaturidade(@PathVariable(value = "id") Long grauMaturidadeId,
                                                             @RequestBody GrauMaturidade grauMaturidadeDetails) throws ResourceNotFoundException {
        final GrauMaturidade updateGrauMaturidade = grauMaturidadeService.update(grauMaturidadeId,grauMaturidadeDetails);
        return ResponseEntity.ok(updateGrauMaturidade);
    }

    //Deleta grau
    @DeleteMapping("grau_maturidade_delete/{id}")
    public ResponseEntity <?> DeleteGrauMaturidade(@PathVariable (value = "id") Long grauMaturidadeId) throws ResourceNotFoundException{
        URI uri = java.net.URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/grau_maturidade_delete").toUriString());
        return ResponseEntity.created(uri).body(grauMaturidadeService.delete(grauMaturidadeId));
    }


    /** Método para localizar grau maturidade - módulo disponivel para o administrador*/
    @GetMapping("/grau_maturidade_busca/{id}")
    public ResponseEntity<GrauMaturidade> getGrauMaturidadeById(@PathVariable(value = "id") Long grauMaturidadeId)
            throws ResourceNotFoundException{
        GrauMaturidade grauMaturidade = grauMaturidadeService.findById(grauMaturidadeId);
        return ResponseEntity.ok().body(grauMaturidade);
    }


}
