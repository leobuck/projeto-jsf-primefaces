package br.com.project.model.classes;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.CollectionOfElements;
import org.hibernate.envers.Audited;
import org.primefaces.json.JSONObject;

import br.com.project.annotation.IdentificaCampoPesquisa;

@SuppressWarnings("deprecation")
@Audited
@Entity
@Table(name = "entidade")
@SequenceGenerator(name = "entidade_seq", sequenceName = "entidade_seq", initialValue = 1, allocationSize = 1)
public class Entidade implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "entidade_seq")
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

	@CollectionOfElements
	@ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
	@JoinTable(name = "entidade_acesso", uniqueConstraints = {
			@UniqueConstraint(name = "unique_acesso_entidade_key", columnNames = { "ent_codigo",
					"esa_codigo" }) }, joinColumns = { @JoinColumn(name = "ent_codigo") })
	@Column(name = "esa_codigo", length = 20)
	private Set<String> acessos = new HashSet<String>();
	
	private String email;

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

	public Set<String> getAcessos() {
		return acessos;
	}

	public void setAcessos(Set<String> acessos) {
		this.acessos = acessos;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public JSONObject getJson() {
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("ent_codigo", getEntCodigo());
		map.put("ent_login", getEntLogin());
		map.put("ent_nomefantasia", getEntNomeFantasia());
		return new JSONObject(map);
	}
}
