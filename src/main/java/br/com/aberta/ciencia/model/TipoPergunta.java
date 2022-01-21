package br.com.aberta.ciencia.model;


public enum TipoPergunta {

		TRUE_FALSE(1,"TRUE_FALSE") ,
		ALTERNATIVE(2, "ALTERNATIVE"),
	    ABERTA(3, "ABERTA"),
	    SELECAO(4, "SELECAO");

	private int codigoTipoPergunta;
	private String descricaoTipoPergunta;

	TipoPergunta(int codigoTipoPergunta, String descricaoTipoPergunta) {
		this.codigoTipoPergunta = codigoTipoPergunta;
		this.descricaoTipoPergunta = descricaoTipoPergunta;
	}

	public int getCodigoTipoPergunta() {
		return codigoTipoPergunta;
	}

	public String getDescricaoTipoPergunta() {
		return descricaoTipoPergunta;
	}
}
