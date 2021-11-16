package br.com.aberta.ciencia.model;

import java.util.*;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

@Document(collection = "Usuario")
public class Usuario {
	
	@Transient
	public static final String SEQUENCE_NAME = "usuario_sequence";
	
	@Id
	private long id;
	
	@NotBlank
	@Indexed(unique = true)
	@Size(max = 200)
	private String nomeUsuario;
	
	@NotBlank
	@Size(max = 100)
	private String emailUsuario;
	
	@NotBlank
	@Indexed(unique = true)
	@Size(max = 100)
	private String instituicaoUsuario;
	
	
	private String senhaUsuario;
	
	private String ocupacaoUsuario;
	
	private boolean permissaoDivulgacaoDadosUsuario;
	
	@DateTimeFormat(style = "M-") 
	@CreatedDate
	private Date dataCadastroUsuario;
	
	@DateTimeFormat(style = "M-") 
	@CreatedDate
	private Date dataAlteracaoUsuario;
	
	
	private int tipoUsuario ;

	public Usuario() {
	}



	public Usuario(String nomeUsuario, String emailUsuario, String instituicaoUsuario,String senhaUsuario, String ocupacaoUsuario, boolean permissaoDivulgacaoDadosUsuario,
			Date dataCadastroUsuario, Date dataAlteracaoUsuario, TipoUsuario tipoUsuario) {
        this.nomeUsuario = nomeUsuario;
        this.emailUsuario = emailUsuario;
        this.instituicaoUsuario = instituicaoUsuario;
        this.senhaUsuario = senhaUsuario;
        this.ocupacaoUsuario = ocupacaoUsuario;
        this.permissaoDivulgacaoDadosUsuario = permissaoDivulgacaoDadosUsuario;
        this.dataCadastroUsuario = dataCadastroUsuario;
        this.dataAlteracaoUsuario = dataAlteracaoUsuario;
        this.tipoUsuario = tipoUsuario.getCodigoTipoUsuario();
    }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getEmailUsuario() {
		return emailUsuario;
	}

	public void setEmailUsuario(String emailUsuario) {
		this.emailUsuario = emailUsuario;
	}
	
	

	public String getSenhaUsuario() {
		return senhaUsuario;
	}

	public void setSenhaUsuario(String senhaUsuario) {
		this.senhaUsuario = senhaUsuario;
	}

	public String getInstituicaoUsuario() {
		return instituicaoUsuario;
	}

	public void setInstituicaoUsuario(String instituicaoUsuario) {
		this.instituicaoUsuario = instituicaoUsuario;
	}

	public String getOcupacaoUsuario() {
		return ocupacaoUsuario;
	}

	public void setOcupacaoUsuario(String ocupacaoUsuario) {
		this.ocupacaoUsuario = ocupacaoUsuario;
	}

	public boolean getPermissaoDivulgacaoDadosUsuario() {
		return permissaoDivulgacaoDadosUsuario;
	}

	public void setPermissaoDivulgacaoDadosUsuario(boolean permissaoDivulgacaoDadosUsuario) {
		this.permissaoDivulgacaoDadosUsuario = permissaoDivulgacaoDadosUsuario;
	}

	public Date getDataCadastroUsuario() {
		return dataCadastroUsuario;
	}

	public void setDataCadastroUsuario(Date dataCadastroUsuario) {
		this.dataCadastroUsuario = dataCadastroUsuario;
	}
	
	public Date getDataAlteracaoUsuario() {
		return dataAlteracaoUsuario;
	}
	
	public void setDataAlteracaoUsuario(Date dataAlteracaoUsuario) {
		this.dataAlteracaoUsuario = dataAlteracaoUsuario;
	}
	
	public int getTipoUsuario() {
		return tipoUsuario;
	}
	
	
	public void setTipoUsuario(int tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nomeUsuario=" + nomeUsuario + ", emailUsuario=" + emailUsuario + ", instituicaoUsuario=" + instituicaoUsuario +
				", senhaUsuario=" + senhaUsuario + ", ocupacaoUsuario=" + ocupacaoUsuario + ", permissaoDivulgacaoDadosUsuario=" + permissaoDivulgacaoDadosUsuario + 
				", dataCadastroUsuario=" + dataCadastroUsuario + ", dataAlteracaoUsuario=" + dataAlteracaoUsuario +
				", tipoUsuario=" + tipoUsuario + "]";
		
	}

	

}
