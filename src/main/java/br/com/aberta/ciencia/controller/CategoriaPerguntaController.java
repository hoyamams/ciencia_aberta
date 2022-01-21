package br.com.aberta.ciencia.controller;


import br.com.aberta.ciencia.exception.ResourceNotFoundException;
import br.com.aberta.ciencia.model.CategoriaPergunta;
import br.com.aberta.ciencia.model.Usuario;
import br.com.aberta.ciencia.service.CategoriaPerguntaService;
import br.com.aberta.ciencia.service.SequenceGeneratorService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/")
public class CategoriaPerguntaController {

    @Autowired
    private CategoriaPerguntaService categoriaPerguntaService;


//Insere categoria
    @PostMapping("/categoria")
    public ResponseEntity<CategoriaPergunta> createCategoriaPergunta(@RequestBody CategoriaPergunta categoriaPergunta){
        URI uri = java.net.URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/categoria").toUriString());
        return ResponseEntity.created(uri).body(categoriaPerguntaService.save(categoriaPergunta));
    }

    //lista categorias
    @GetMapping("/categoria_list")
    public List<CategoriaPergunta> getAllCategoriaPergunta(){
        return categoriaPerguntaService.findAll();
    }

    //altera Categoria
    @PutMapping("categoria_update/{id}")
    public ResponseEntity<CategoriaPergunta> UpdateCategoria(@PathVariable(value = "id") Long categoriaId,
                                                            @RequestBody CategoriaPergunta categoriaDetails) throws ResourceNotFoundException{
        final CategoriaPergunta updateCategoria = categoriaPerguntaService.update(categoriaId,categoriaDetails);
        return ResponseEntity.ok(updateCategoria);
    }

    //Deleta Categoria
    @DeleteMapping("categoria_delete/{id}")
    public ResponseEntity <?> DeleteCategoria(@PathVariable (value = "id") Long categoriaId) throws ResourceNotFoundException{
        URI uri = java.net.URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/categoria_delete").toUriString());
       return ResponseEntity.created(uri).body(categoriaPerguntaService.delete(categoriaId));
    }


    /** Método para localizar um usuário - módulo disponivel para o administrador*/
    @GetMapping("/categoria_busca/{id}")
    public ResponseEntity<CategoriaPergunta> getCategoriaById(@PathVariable(value = "id") Long categoriaId)
            throws ResourceNotFoundException{
        CategoriaPergunta categoriaPergunta = categoriaPerguntaService.findById(categoriaId);
        return ResponseEntity.ok().body(categoriaPergunta);
    }

}
