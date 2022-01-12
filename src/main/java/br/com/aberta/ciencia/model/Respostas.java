package br.com.aberta.ciencia.model;

import br.com.aberta.ciencia.repository.GrauMaturidadeRepository;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@Document(collection = "Respostas")
public class Respostas {

    @Transient
    public static final String SEQUENCE_NAME = "respostas_sequence";

    @Id
    private Long id;

    private Long idUsuario;

    private String instituicaoUsuario;

    private ArrayList respostasUsuario;

    private Double pontuacaoUsuario;

    private GrauMaturidade grauMaturidadeUsuario;

    private Boolean divulgaUsuario;

    public Respostas(){}

    public Respostas(Long id, Long idUsuario, ArrayList respostasUsuario, Double pontuacaoUsuario, GrauMaturidade grauMaturidadeUsuario, String instituicaoUsuario, Boolean divulgaUsuario) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.respostasUsuario = respostasUsuario;
        this.pontuacaoUsuario = pontuacaoUsuario;
        this.grauMaturidadeUsuario = grauMaturidadeUsuario;
        this.instituicaoUsuario = instituicaoUsuario;
        this.divulgaUsuario = divulgaUsuario;
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

    public Double getPontuacaoUsuario() {
        return pontuacaoUsuario;
    }

    public void setPontuacaoUsuario(Double pontuacaoUsuario) {
        this.pontuacaoUsuario = pontuacaoUsuario;
    }

    public GrauMaturidade getGrauMaturidadeUsuario() {
        return grauMaturidadeUsuario;
    }

    public void setGrauMaturidadeUsuario(GrauMaturidade grauMaturidadeUsuario) {
        this.grauMaturidadeUsuario = grauMaturidadeUsuario;
    }

    public String getInstituicaoUsuario() {
        return instituicaoUsuario;
    }

    public void setInstituicaoUsuario(String instituicaoUsuario) {
        this.instituicaoUsuario = instituicaoUsuario;
    }

    public Boolean getDivulgaUsuario() {
        return divulgaUsuario;
    }

    public void setDivulgaUsuario(Boolean divulgaUsuario) {
        this.divulgaUsuario = divulgaUsuario;
    }
}

