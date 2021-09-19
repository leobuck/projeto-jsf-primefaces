package br.com.project.model.classes;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.envers.Audited;
import org.primefaces.json.JSONObject;

import br.com.project.annotation.IdentificaCampoPesquisa;

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

	@IdentificaCampoPesquisa(campoConsulta = "entNomeFantasia", descricaoCampo = "Nome", principal = 1)
	@Column(name = "ent_nomefantasia")
	private String entNomeFantasia;

	private String tipoEntidade = "";

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

	public String getEntNomeFantasia() {
		return entNomeFantasia;
	}

	public void setEntNomeFantasia(String entNomeFantasia) {
		this.entNomeFantasia = entNomeFantasia;
	}

	public String getTipoEntidade() {
		return tipoEntidade;
	}

	public void setTipoEntidade(String tipoEntidade) {
		this.tipoEntidade = tipoEntidade;
	}

	public JSONObject getJson() {
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("ent_codigo", getEntCodigo());
		map.put("ent_login", getEntLogin());
		map.put("ent_nomefantasia", getEntNomeFantasia());
		return new JSONObject(map);
	}
}
