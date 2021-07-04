package br.com.project.model.classes;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.envers.Audited;

@Audited
@Entity
@Table(name = "entidade")
public class Entidade implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ent_codigo")
	private Long entCodigo;
	
	@Column(name = "ent_login")
	private String entLogin;
	
	@Column(name = "ent_senha")
	private String entSenha;
	
	@Column(name = "ent_inativo")
	private boolean entInativo;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ent_ultimoacesso")
	private Date entUltimoAcesso;

	public Long getEntCodigo() {
		return entCodigo;
	}

	public void setEntCodigo(Long entCodigo) {
		this.entCodigo = entCodigo;
	}

	public String getEntLogin() {
		return entLogin;
	}

	public void setEntLogin(String entLogin) {
		this.entLogin = entLogin;
	}

	public String getEntSenha() {
		return entSenha;
	}

	public void setEntSenha(String entSenha) {
		this.entSenha = entSenha;
	}

	public boolean isEntInativo() {
		return entInativo;
	}

	public void setEntInativo(boolean entInativo) {
		this.entInativo = entInativo;
	}

	public Date getEntUltimoAcesso() {
		return entUltimoAcesso;
	}

	public void setEntUltimoAcesso(Date entUltimoAcesso) {
		this.entUltimoAcesso = entUltimoAcesso;
	}
	
}
