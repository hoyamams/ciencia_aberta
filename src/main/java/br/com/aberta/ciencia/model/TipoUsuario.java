package br.com.aberta.ciencia.model;


public enum TipoUsuario {
	
		ADMINISTRADOR(1,"ADMIN") ,
		COMUM(2, "COMUM");

	private Integer codigoTipoUsuario;
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

	public String apresentaDescricao(int codigoTipoUsuario) {
		if (codigoTipoUsuario == 1) {
			return "ADMIN";
		} else if (codigoTipoUsuario == 2) {
			return "COMUM";
		}
		return null;
	}
	
}
