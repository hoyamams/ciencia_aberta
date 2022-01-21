package br.com.aberta.ciencia.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collection;


public class RespostasPossiveis {

    private int id;
   private String descricaoRespostasPossiveis;

    public RespostasPossiveis() {
    }

    public RespostasPossiveis(int id, String descricaoRespostasPossiveis) {
        this.id = id;
        this.descricaoRespostasPossiveis = descricaoRespostasPossiveis;
    }

    public String getDescricaoRespostasPossiveis() {
        return descricaoRespostasPossiveis;
    }

    public void setDescricaoRespostasPossiveis(String descricaoRespostasPossiveis) {
        this.descricaoRespostasPossiveis = descricaoRespostasPossiveis;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
