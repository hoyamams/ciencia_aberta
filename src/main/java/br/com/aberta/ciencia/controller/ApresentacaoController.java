package br.com.aberta.ciencia.controller;


import br.com.aberta.ciencia.exception.ResourceNotFoundException;
import br.com.aberta.ciencia.model.Apresentacao;
import br.com.aberta.ciencia.model.Glossario;
import br.com.aberta.ciencia.model.Referencias;
import br.com.aberta.ciencia.service.ApresentacaoService;
import br.com.aberta.ciencia.service.GlossarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/")
public class ApresentacaoController {

    @Autowired
    private ApresentacaoService apresentacaoService;

//Insere categoria
    @PostMapping("/apresentacao")
    public ResponseEntity<Apresentacao> createApresentacao(@RequestBody Apresentacao apresentacao){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/apresentacao").toUriString());
        return ResponseEntity.created(uri).body(apresentacaoService.save(apresentacao));
    }

    //lista categorias
    @GetMapping("/apresentacao_list")
    public List<Apresentacao> getAllApresentacao(){
        return apresentacaoService.findAll();
    }

    //altera Categoria
    @PutMapping("apresentacao_update/{id}")
    public ResponseEntity<Apresentacao> UpdateApresentacao(@PathVariable(value = "id") Long apresentacaoId,
                                                            @RequestBody Apresentacao apresentacaoDetails) throws ResourceNotFoundException{
        final Apresentacao updateApresentacao = apresentacaoService.update(apresentacaoId,apresentacaoDetails);
        return ResponseEntity.ok(updateApresentacao);
    }

    //Deleta Categoria
    @DeleteMapping("apresentacao_delete/{id}")
    public ResponseEntity <?> DeleteApresentacao(@PathVariable (value = "id") Long apresentacaoId) throws ResourceNotFoundException{
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/apresentacao_delete").toUriString());
       return ResponseEntity.created(uri).body(apresentacaoService.delete(apresentacaoId));
    }

    /** Método para localizar um usuário - módulo disponivel para o administrador*/
    @GetMapping("/apresentacao_busca/{id}")
    public ResponseEntity<Apresentacao> getReferenciasById(@PathVariable(value = "id") Long apresentacaoId)
            throws ResourceNotFoundException{
        Apresentacao apresentacao = apresentacaoService.findById(apresentacaoId);
        return ResponseEntity.ok().body(apresentacao);
    }

}
