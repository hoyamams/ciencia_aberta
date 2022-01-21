package br.com.aberta.ciencia.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Document(collection = "CategoriaPergunta")
public class CategoriaPergunta {


    @Transient
    public static final String SEQUENCE_NAME = "categoria_pergunta_sequence";

    @Id
    private Long id;

    @NotBlank
    @Indexed(unique = true)
    @Size(max = 255)
    private String descricaoCategoriaPergunta;

    private int pontosPossiveisCategoriaPergunta;

    public CategoriaPergunta(){}

    public CategoriaPergunta(Long id, String descricaoCategoriaPergunta, int pontosPossiveisCategoriaPergunta) {
        this.id = id;
        this.descricaoCategoriaPergunta = descricaoCategoriaPergunta;
        this.pontosPossiveisCategoriaPergunta = pontosPossiveisCategoriaPergunta;
    }


    public static String getSequenceName() {
        return SEQUENCE_NAME;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public int getPontosPossiveisCategoriaPergunta() {
        return pontosPossiveisCategoriaPergunta;
    }

    public void setPontosPossiveisCategoriaPergunta(int pontosPossiveisCategoriaPergunta) {
        this.pontosPossiveisCategoriaPergunta = pontosPossiveisCategoriaPergunta;
    }


    public String getDescricaoCategoriaPergunta() {
        return descricaoCategoriaPergunta;
    }

    public void setDescricaoCategoriaPergunta(String descricaoCategoriaPergunta) {
        this.descricaoCategoriaPergunta = descricaoCategoriaPergunta;
    }

}
