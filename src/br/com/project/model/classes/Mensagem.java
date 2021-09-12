package br.com.project.model.classes;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.envers.Audited;
import org.primefaces.json.JSONObject;

import br.com.project.annotation.IdentificaCampoPesquisa;

@Audited
@Entity
@Table(name = "mensagem")
@SequenceGenerator(name = "mensagem_seq", sequenceName = "mensagem_seq", allocationSize = 1, initialValue = 1)
public class Mensagem implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mensagem_seq")
	@Column(name = "men_codigo")
	@IdentificaCampoPesquisa(descricaoCampo = "Código", campoConsulta = "men_codigo")
	private Long menCodigo;

	@ManyToOne(fetch = FetchType.EAGER)
	@ForeignKey(name = "usu_origem_fk")
	@JoinColumn(name = "usu_origem")
	@IdentificaCampoPesquisa(descricaoCampo = "Origem", campoConsulta = "usu_origem.ent_nomefantasia", principal = 2)
	private Entidade usuOrigem = new Entidade();

	@ManyToOne(fetch = FetchType.EAGER)
	@ForeignKey(name = "usu_destino_fk")
	@JoinColumn(name = "usu_destino")
	@IdentificaCampoPesquisa(descricaoCampo = "Origem", campoConsulta = "usu_destino.ent_nomefantasia", principal = 3)
	private Entidade usuDestino = new Entidade();

	@Column(name = "men_lido", nullable = false)
	private Boolean menLido = Boolean.FALSE;

	@IdentificaCampoPesquisa(descricaoCampo = "Data Hora", campoConsulta = "men_datahora")
	@Column(name = "men_datahora", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date menDataHora = new Date();

	@IdentificaCampoPesquisa(descricaoCampo = "Assunto", campoConsulta = "men_assunto")
	@Column(name = "men_assunto", length = 80, nullable = false)
	private String menAssunto;

	@IdentificaCampoPesquisa(descricaoCampo = "Mensagem", campoConsulta = "men_mensagem", principal = 1)
	@Column(name = "men_mensagem", length = 1000, nullable = false)
	private String menMensagem;

	@Column(name = "men_exigirresposta")
	private Boolean menExigirResposta = Boolean.FALSE;

	@Version
	@Column(name = "versionNum")
	private int versionNum;

	public Long getMenCodigo() {
		return menCodigo;
	}

	public void setMenCodigo(Long menCodigo) {
		this.menCodigo = menCodigo;
	}

	public Entidade getUsuOrigem() {
		return usuOrigem;
	}

	public void setUsuOrigem(Entidade usuOrigem) {
		this.usuOrigem = usuOrigem;
	}

	public Entidade getUsuDestino() {
		return usuDestino;
	}

	public void setUsuDestino(Entidade usuDestino) {
		this.usuDestino = usuDestino;
	}

	public Boolean getMenLido() {
		return menLido;
	}

	public void setMenLido(Boolean menLido) {
		this.menLido = menLido;
	}

	public Date getMenDataHora() {
		return menDataHora;
	}

	public void setMenDataHora(Date menDataHora) {
		this.menDataHora = menDataHora;
	}

	public String getMenAssunto() {
		return menAssunto;
	}

	public void setMenAssunto(String menAssunto) {
		this.menAssunto = menAssunto;
	}

	public String getMenMensagem() {
		return menMensagem;
	}

	public void setMenMensagem(String menMensagem) {
		this.menMensagem = menMensagem;
	}

	public Boolean getMenExigirResposta() {
		return menExigirResposta;
	}

	public void setMenExigirResposta(Boolean menExigirResposta) {
		this.menExigirResposta = menExigirResposta;
	}

	public int getVersionNum() {
		return versionNum;
	}

	public void setVersionNum(int versionNum) {
		this.versionNum = versionNum;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((menCodigo == null) ? 0 : menCodigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mensagem other = (Mensagem) obj;
		if (menCodigo == null) {
			if (other.menCodigo != null)
				return false;
		} else if (!menCodigo.equals(other.menCodigo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Mensagem [menCodigo=" + menCodigo + ", menAssunto=" + menAssunto + ", menMensagem=" + menMensagem + "]";
	}

	public JSONObject getJson() {
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("men_codigo", getMenCodigo());
		map.put("men_lido", getMenLido());
		return new JSONObject(map);
	}
}
