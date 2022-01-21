package br.com.aberta.ciencia.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Referencias")
public class Referencias {

    @Transient
    public static final String SEQUENCE_NAME = "referencias_sequence";

    @Id
    private Long id;

    private String descricaoReferencias;


    public Referencias() {}

    public Referencias(Long id, String descricaReferencias ) {
        this.id = id;
        this.descricaoReferencias = descricaoReferencias;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricaoReferencias() {
        return descricaoReferencias;
    }

    public void setDescricaoReferencias(String descricaoReferencias) {
        this.descricaoReferencias = descricaoReferencias;
    }
}
