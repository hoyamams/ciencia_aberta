package br.com.aberta.ciencia.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "GrauMaturidade")
public class GrauMaturidade {

    @Transient
    public static final String SEQUENCE_NAME = "grau_maturidade_sequence";

    @Id
    private Long id;

    private String nivelGrauMaturidade;

    private String descricaoGrauMaturidade;

    private int pontuacaoMinimaGrauMaturidade;

    private int pontuacaoMaximaGrauMaturidade;

    private String porcentagemGrauMaturidade;

    public GrauMaturidade() {}

    public GrauMaturidade(Long id, String nivelGrauMaturidade, String descricaoGrauMaturidade, int pontuacaoMinimaGrauMaturidade, int pontuacaoMaximaGrauMaturidade, String porcentagemGrauMaturidade) {
        this.id = id;
        this.nivelGrauMaturidade = nivelGrauMaturidade;
        this.descricaoGrauMaturidade = descricaoGrauMaturidade;
        this.pontuacaoMinimaGrauMaturidade = pontuacaoMinimaGrauMaturidade;
        this.pontuacaoMaximaGrauMaturidade = pontuacaoMaximaGrauMaturidade;
        this.porcentagemGrauMaturidade = porcentagemGrauMaturidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNivelGrauMaturidade() {
        return nivelGrauMaturidade;
    }

    public void setNivelGrauMaturidade(String nivelGrauMaturidade) {
        this.nivelGrauMaturidade = nivelGrauMaturidade;
    }

    public String getDescricaoGrauMaturidade() {
        return descricaoGrauMaturidade;
    }

    public void setDescricaoGrauMaturidade(String descricaoGrauMaturidade) {
        this.descricaoGrauMaturidade = descricaoGrauMaturidade;
    }

    public int getPontuacaoMinimaGrauMaturidade() {
        return pontuacaoMinimaGrauMaturidade;
    }

    public void setPontuacaoMinimaGrauMaturidade(int pontuacaoMinimaGrauMaturidade) {
        this.pontuacaoMinimaGrauMaturidade = pontuacaoMinimaGrauMaturidade;
    }

    public int getPontuacaoMaximaGrauMaturidade() {
        return pontuacaoMaximaGrauMaturidade;
    }

    public void setPontuacaoMaximaGrauMaturidade(int pontuacaoMaximaGrauMaturidade) {
        this.pontuacaoMaximaGrauMaturidade = pontuacaoMaximaGrauMaturidade;
    }

    public String getPorcentagemGrauMaturidade() {
        return porcentagemGrauMaturidade;
    }

    public void setPorcentagemGrauMaturidade(String porcentagemGrauMaturidade) {
        this.porcentagemGrauMaturidade = porcentagemGrauMaturidade;
    }
}
