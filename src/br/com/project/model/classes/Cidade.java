package br.com.project.model.classes;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.envers.Audited;

import br.com.project.annotation.IdentificaCampoPesquisa;

@Audited
@Entity
@Table(name = "cidade")
@SequenceGenerator(name = "cidade_seq", sequenceName = "cidade_seq", initialValue = 1, allocationSize = 1)
public class Cidade implements Serializable {

	private static final long serialVersionUID = 1L;

	@IdentificaCampoPesquisa(descricaoCampo = "Código", campoConsulta = "cid_codigo")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cidade_seq")
	@Column(name = "cid_codigo")
	private Long cidCodigo;

	@IdentificaCampoPesquisa(descricaoCampo = "Descrição", campoConsulta = "cid_descricao", principal = 1)
	@Column(name = "cid_descricao", nullable = false, length = 100)
	private String cidDescricao;

	@IdentificaCampoPesquisa(descricaoCampo = "Estado", campoConsulta = "estado.est_nome")
	@Basic
	@ManyToOne
	@JoinColumn(name = "estado", nullable = false)
	@ForeignKey(name = "estado_fk")
	private Estado estado = new Estado();

	@Version
	@Column(name = "versionNum")
	private int versionNum;

	public Long getCidCodigo() {
		return cidCodigo;
	}

	public void setCidCodigo(Long cidCodigo) {
		this.cidCodigo = cidCodigo;
	}

	public String getCidDescricao() {
		return cidDescricao;
	}

	public void setCidDescricao(String cidDescricao) {
		this.cidDescricao = cidDescricao;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
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
		result = prime * result + ((cidCodigo == null) ? 0 : cidCodigo.hashCode());
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
		Cidade other = (Cidade) obj;
		if (cidCodigo == null) {
			if (other.cidCodigo != null)
				return false;
		} else if (!cidCodigo.equals(other.cidCodigo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cidade [cidCodigo=" + cidCodigo + ", cidDescricao=" + cidDescricao + ", estado=" + estado
				+ ", versionNum=" + versionNum + "]";
	}

}
