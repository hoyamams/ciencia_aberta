package br.com.aberta.ciencia.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;

@Document(collection = "Perguntas")
public class Perguntas {

 @Transient
 public static final String SEQUENCE_NAME = "perguntas_sequence";

 @Id
 private Long id;

 @NotBlank
 private String descricaoPergunta;

 //relacionamento com categoria
 private CategoriaPergunta categoria;

 private TipoPergunta perguntaTipoPergunta;

 private ArrayList<RespostasPossiveis> respostasPossiveisPergunta;

 public Perguntas(){}

 public Perguntas(Long id, String descricaoPergunta, CategoriaPergunta categoria, TipoPergunta perguntaTipoPergunta, ArrayList<RespostasPossiveis> respostasPossiveisPergunta) {
  this.id = id;
  this.descricaoPergunta = descricaoPergunta;
  this.categoria = categoria;
  this.perguntaTipoPergunta = perguntaTipoPergunta;
  this.respostasPossiveisPergunta = respostasPossiveisPergunta;
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

 public String getDescricaoPergunta() {
  return descricaoPergunta;
 }

 public void setDescricaoPergunta(String descricaoPergunta) {
  this.descricaoPergunta = descricaoPergunta;
 }

 public CategoriaPergunta getCategoria() {
  return categoria;
 }

 public void setCategoria(CategoriaPergunta categoria) {
  this.categoria = categoria;
 }

 public String getPerguntaTipoPergunta() {

  return perguntaTipoPergunta.getDescricaoTipoPergunta();
 }

 public void setPerguntaTipoPergunta(TipoPergunta perguntaTipoPergunta) {
  this.perguntaTipoPergunta = perguntaTipoPergunta;
 }

 public ArrayList<RespostasPossiveis> getRespostasPossiveisPergunta() {
  return respostasPossiveisPergunta;
 }

 public void setRespostasPossiveisPergunta(ArrayList<RespostasPossiveis> respostasPossiveisPergunta) {
  this.respostasPossiveisPergunta = respostasPossiveisPergunta;
 }

 /*public CategoriaPergunta getPerguntasCategorias(Long idCategoria){
      CategoriaPergunta categoriaPergunta = new CategoriaPergunta().ge
 }*/

}
