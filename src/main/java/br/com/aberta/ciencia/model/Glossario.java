package br.com.aberta.ciencia.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Glossario")
public class Glossario {

    @Transient
    public static final String SEQUENCE_NAME = "glossario_sequence";

    @Id
    private Long id;

    private String descricaoGlossario;


    public Glossario() {}

    public Glossario(Long id, String descricaoGlossario ) {
        this.id = id;
        this.descricaoGlossario = descricaoGlossario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricaoGlossario() {
        return descricaoGlossario;
    }

    public void setDescricaoGlossario(String descricaoGlossario) {
        this.descricaoGlossario = descricaoGlossario;
    }
}
