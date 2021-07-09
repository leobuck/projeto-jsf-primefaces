package br.com.project.model.classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import br.com.project.annotation.IdentificaCampoPesquisa;

@Audited
@Entity
@Table(name = "estado")
@SequenceGenerator(name = "estado_seq", sequenceName = "estado_seq", initialValue = 1, allocationSize = 1)
public class Estado implements Serializable {

	private static final long serialVersionUID = 1L;

	@IdentificaCampoPesquisa(descricaoCampo = "Código", campoConsulta = "est_id")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "estado_seq")
	@Column(name = "est_id")
	private Long estId;

	@Column(name = "est_uf", nullable = true, length = 10)
	private String estUF;

	@IdentificaCampoPesquisa(descricaoCampo = "Nome", campoConsulta = "est_nome", principal = 1)
	@Column(name = "est_nome", nullable = false, length = 100)
	private String estNome;

	@NotAudited
	@OneToMany(mappedBy = "estado", orphanRemoval = false)
	@Cascade(value = { CascadeType.SAVE_UPDATE, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
	private List<Cidade> cidades = new ArrayList<>();

	@Basic
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "pais", nullable = false)
	@ForeignKey(name = "pais_fk")
	private Pais pais = new Pais();

	@Version
	@Column(name = "versionNum")
	private int versionNum;

	public Long getEstId() {
		return estId;
	}

	public void setEstId(Long estId) {
		this.estId = estId;
	}

	public String getEstUF() {
		return estUF;
	}

	public void setEstUF(String estUF) {
		this.estUF = estUF;
	}

	public String getEstNome() {
		return estNome;
	}

	public void setEstNome(String estNome) {
		this.estNome = estNome;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
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
		result = prime * result + ((estId == null) ? 0 : estId.hashCode());
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
		Estado other = (Estado) obj;
		if (estId == null) {
			if (other.estId != null)
				return false;
		} else if (!estId.equals(other.estId))
			return false;
		return true;
	}

}
