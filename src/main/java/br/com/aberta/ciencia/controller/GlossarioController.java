package br.com.aberta.ciencia.controller;


import br.com.aberta.ciencia.exception.ResourceNotFoundException;
import br.com.aberta.ciencia.model.CategoriaPergunta;
import br.com.aberta.ciencia.model.Glossario;
import br.com.aberta.ciencia.service.CategoriaPerguntaService;
import br.com.aberta.ciencia.service.GlossarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/")
public class GlossarioController {

    @Autowired
    private GlossarioService glossarioService;


//Insere categoria
    @PostMapping("/glossario")
    public ResponseEntity<Glossario> createGlossario(@RequestBody Glossario glossario){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/glossario").toUriString());
        return ResponseEntity.created(uri).body(glossarioService.save(glossario));
    }

    //lista categorias
    @GetMapping("/glossario_list")
    public List<Glossario> getAllGlossario(){
        return glossarioService.findAll();
    }

    //altera Categoria
    @PutMapping("glossario_update/{id}")
    public ResponseEntity<Glossario> UpdateGlossario(@PathVariable(value = "id") Long glossarioId,
                                                            @RequestBody Glossario glossarioDetails) throws ResourceNotFoundException{
        final Glossario updateGlossario = glossarioService.update(glossarioId,glossarioDetails);
        return ResponseEntity.ok(updateGlossario);
    }

    //Deleta Categoria
    @DeleteMapping("glossario_delete/{id}")
    public ResponseEntity <?> DeleteGlossario(@PathVariable (value = "id") Long glossarioId) throws ResourceNotFoundException{
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/glossario_delete").toUriString());
       return ResponseEntity.created(uri).body(glossarioService.delete(glossarioId));
    }

    /** Método para localizar um usuário - módulo disponivel para o administrador*/
    @GetMapping("/glossario_busca/{id}")
    public ResponseEntity<Glossario> getGlossarioById(@PathVariable(value = "id") Long glossarioId)
            throws ResourceNotFoundException{
        Glossario glossario = glossarioService.findById(glossarioId);
        return ResponseEntity.ok().body(glossario);
    }



}
