package br.com.aberta.ciencia.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Document(collection = "Respostas")
public class Respostas {

    @Transient
    public static final String SEQUENCE_NAME = "respostas_sequence";

    @Id
    private Long id;

    private Long idUsuario;

    private ArrayList respostasUsuario;

    public Respostas(){}

    public Respostas(Long id, Long idUsuario, ArrayList respostasUsuario) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.respostasUsuario = respostasUsuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public ArrayList getRespostasUsuario() {
        return respostasUsuario;
    }

    public void setRespostasUsuario(ArrayList respostasUsuario) {
        this.respostasUsuario = respostasUsuario;
    }
}

