package br.com.aberta.ciencia.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Apresentacao")
public class Apresentacao {

    @Transient
    public static final String SEQUENCE_NAME = "apresentacao_sequence";

    @Id
    private Long id;

    private String descricaoApresentacao;


    public Apresentacao() {}

    public Apresentacao(Long id, String descricaoApresentacao ) {
        this.id = id;
        this.descricaoApresentacao = descricaoApresentacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricaoApresentacao() {
        return descricaoApresentacao;
    }

    public void setDescricaoApresentacao(String descricaoApresentacao) {
        this.descricaoApresentacao = descricaoApresentacao;
    }
}
