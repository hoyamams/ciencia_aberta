package br.com.aberta.ciencia.controller;

import br.com.aberta.ciencia.exception.ResourceNotFoundException;
import br.com.aberta.ciencia.model.Perguntas;
import br.com.aberta.ciencia.model.Usuario;
import br.com.aberta.ciencia.service.PerguntasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/")
public class PerguntasController {

    @Autowired
    private PerguntasService perguntasService;

    @PostMapping("/perguntas")
    public ResponseEntity<Perguntas> createPergunta(@RequestBody Perguntas perguntas) {
        URI uri = java.net.URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/perguntas").toUriString());
        return ResponseEntity.created(uri).body(perguntasService.save(perguntas));
    }

    @GetMapping("/perguntas_list")
    public List<Perguntas> getAllPergunta() {
        return perguntasService.findAll();
    }

    @GetMapping("/perguntas_busca/{id}")
    public ResponseEntity<Perguntas> getPerguntaById(@PathVariable(value = "id") Long perguntaId)
            throws ResourceNotFoundException{
        Perguntas pergunta = perguntasService.findById(perguntaId);
        return ResponseEntity.ok().body(pergunta);
    }

    @PutMapping("perguntas_update/{id}")
    public ResponseEntity<Perguntas> UpdatePergunta(@PathVariable(value = "id") Long perguntaId,
                                                 @RequestBody Perguntas perguntaDetails) throws ResourceNotFoundException {

        final Perguntas updatePergunta = perguntasService.update(perguntaId, perguntaDetails);
        return ResponseEntity.ok(updatePergunta);

    }
    //Deleta Categoria
    @DeleteMapping("perguntas_delete/{id}")
    public ResponseEntity <?> DeletePergunta(@PathVariable (value = "id") Long perguntaId) throws ResourceNotFoundException{
        URI uri = java.net.URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/perguntas_delete").toUriString());
        return ResponseEntity.created(uri).body(perguntasService.delete(perguntaId));
    }


}
