package br.com.aberta.ciencia.controller;

import br.com.aberta.ciencia.exception.ResourceNotFoundException;
import br.com.aberta.ciencia.model.GrauMaturidade;
import br.com.aberta.ciencia.model.Perguntas;
import br.com.aberta.ciencia.model.Respostas;
import br.com.aberta.ciencia.service.RespostasService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.awt.image.ImageProducer;
import java.lang.reflect.Array;
import java.net.URI;
import java.util.*;

@RestController
@RequestMapping("/")
public class RespostasController {

    @Autowired
    private RespostasService respostasService;

    @PostMapping("/respostas")
    public ResponseEntity<Respostas> createResposta(@RequestBody HashMap<String,Object> respostas) throws ResourceNotFoundException {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/respostas").toUriString());
       return ResponseEntity.created(uri).body(respostasService.save(respostas));
    }


    //lista respostas
    @GetMapping("/respostas_list")
    public List<Respostas> getAllRespostas(){
        return respostasService.findAll();
    }

    //pesquisa usuario
    @GetMapping("/pesquisa_usuario/{id}")
    public ResponseEntity<Respostas> getRespostaUsuario(@PathVariable(value = "id") Long idUsuario)throws ResourceNotFoundException{
        Respostas respostas = respostasService.findRespostaUsuario(idUsuario);
        return ResponseEntity.ok().body(respostas);
    }

    @PutMapping("pergunta_update_divulga/{id}")
    public ResponseEntity<Respostas> UpdatePerguntaDivulga(@PathVariable(value = "id") Long usuarioId,
                                                    @RequestBody Boolean divulgaPesquisa)  throws ResourceNotFoundException {

        System.out.println("aqui "+divulgaPesquisa);
        final Respostas updateResposta = respostasService.updateDivulgaCadastro(usuarioId, divulgaPesquisa);
        return ResponseEntity.ok(updateResposta);

    }

}
