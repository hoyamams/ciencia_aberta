package br.com.aberta.ciencia.model;


public enum TipoUsuario {
	
		ADMIN(1,"ADMIN") ,
		COMUM(2, "COMUM");

	private int codigoTipoUsuario;
	private String descricaoTipoUsuario;
	
	
	private TipoUsuario(int cod, String descricao) {
		this.codigoTipoUsuario = cod;
		this.descricaoTipoUsuario = descricao;
	}


	public int getCodigoTipoUsuario() {
		return codigoTipoUsuario;
	}


	public String getDescricaoTipoUsuario() {
		return descricaoTipoUsuario;
	}

	
}
