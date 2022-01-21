package br.com.aberta.ciencia.controller;


import br.com.aberta.ciencia.exception.ResourceNotFoundException;
import br.com.aberta.ciencia.model.Glossario;
import br.com.aberta.ciencia.model.Referencias;
import br.com.aberta.ciencia.service.ReferenciasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/")
public class ReferenciasController {

    @Autowired
    private ReferenciasService referenciasService;

//Insere categoria
    @PostMapping("/referencias")
    public ResponseEntity<Referencias> createReferencias(@RequestBody Referencias referencias){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/referencias").toUriString());
        return ResponseEntity.created(uri).body(referenciasService.save(referencias));
    }

    //lista categorias
    @GetMapping("/referencias_list")
    public List<Referencias> getAllReferencias(){
        return referenciasService.findAll();
    }

    //altera Categoria
    @PutMapping("referencias_update/{id}")
    public ResponseEntity<Referencias> UpdateReferencias(@PathVariable(value = "id") Long referenciasId,
                                                            @RequestBody Referencias referenciasDetails) throws ResourceNotFoundException{
        final Referencias updateReferencias = referenciasService.update(referenciasId,referenciasDetails);
        return ResponseEntity.ok(updateReferencias);
    }

    //Deleta Categoria
    @DeleteMapping("referencias_delete/{id}")
    public ResponseEntity <?> DeleteReferencias(@PathVariable (value = "id") Long referenciasId) throws ResourceNotFoundException{
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/referencias_delete").toUriString());
       return ResponseEntity.created(uri).body(referenciasService.delete(referenciasId));
    }

    /** Método para localizar um usuário - módulo disponivel para o administrador*/
    @GetMapping("/referencias_busca/{id}")
    public ResponseEntity<Referencias> getReferenciasById(@PathVariable(value = "id") Long referenciasId)
            throws ResourceNotFoundException{
        Referencias referencias = referenciasService.findById(referenciasId);
        return ResponseEntity.ok().body(referencias);
    }

}
