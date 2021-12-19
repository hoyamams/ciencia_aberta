package br.com.aberta.ciencia.controller;

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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")
public class RespostasController {

    @Autowired
    private RespostasService respostasService;

    @PostMapping("/respostas")
    public ResponseEntity<Respostas> createResposta(@RequestBody Map<String,Object> respostas) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/respostas").toUriString());
        Respostas montaResposta = new Respostas();
        ArrayList minhasrespostas = new ArrayList(respostas.values());
        montaResposta.setIdUsuario( Long.valueOf((String)minhasrespostas.get(0)));
        montaResposta.setRespostasUsuario((ArrayList) minhasrespostas.get(1));
        return ResponseEntity.created(uri).body(respostasService.save(montaResposta));
    }

}
