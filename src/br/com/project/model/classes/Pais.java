package br.com.project.model.classes;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.envers.Audited;

import br.com.project.annotation.IdentificaCampoPesquisa;

@Audited
@Entity
@Table(name = "pais")
@SequenceGenerator(name = "pais_seq", sequenceName = "pais_seq", initialValue = 1, allocationSize = 1)
public class Pais implements Serializable {

	private static final long serialVersionUID = 1L;

	@IdentificaCampoPesquisa(descricaoCampo = "Código", campoConsulta = "pai_id")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pais_seq")
	@Column(name = "pai_id")
	private Long paiId;

	@IdentificaCampoPesquisa(descricaoCampo = "Nome", campoConsulta = "pai_nome", principal = 1)
	@Column(name = "pai_nome", nullable = false, length = 80)
	private String paiNome;

	@Column(name = "pai_sigla", nullable = true, length = 15)
	private String paiSigla;

	@Version
	@Column(name = "versionNum")
	private int versionNum;

	public Long getPaiId() {
		return paiId;
	}

	public void setPaiId(Long paiId) {
		this.paiId = paiId;
	}

	public String getPaiNome() {
		return paiNome;
	}

	public void setPaiNome(String paiNome) {
		this.paiNome = paiNome;
	}

	public String getPaiSigla() {
		return paiSigla;
	}

	public void setPaiSigla(String paiSigla) {
		this.paiSigla = paiSigla;
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
		result = prime * result + ((paiId == null) ? 0 : paiId.hashCode());
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
		Pais other = (Pais) obj;
		if (paiId == null) {
			if (other.paiId != null)
				return false;
		} else if (!paiId.equals(other.paiId))
			return false;
		return true;
	}

}
