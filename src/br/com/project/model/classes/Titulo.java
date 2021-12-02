package br.com.project.model.classes;

import java.math.BigDecimal;
import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.envers.Audited;

import br.com.project.annotation.IdentificaCampoPesquisa;

@Audited
@Entity
@Table(name = "titulo")
@SequenceGenerator(name = "titulo_seq", sequenceName = "titulo_seq", initialValue = 1, allocationSize = 1)
public class Titulo {

	@IdentificaCampoPesquisa(descricaoCampo = "Código", campoConsulta = "titCodigo")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "titulo_seq")
	@Column(name = "tit_codigo")
	private Long titCodigo;
	
	@IdentificaCampoPesquisa(descricaoCampo = "Origem", campoConsulta = "titOrigem")
	@Column(name = "tit_origem")
	private String titOrigem;
	
	@IdentificaCampoPesquisa(descricaoCampo = "Valor R$", campoConsulta = "titValor")
	@Column(name = "tit_valor", scale = 4, precision = 15)
	private BigDecimal titValor = BigDecimal.ZERO;
	
	@Column(name = "tit_datahora", updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date titDataHora = new Date();
	
	@Column(name = "tit_baixado")
	private Boolean titBaixado = Boolean.FALSE;
	
	@IdentificaCampoPesquisa(descricaoCampo = "Tipo", campoConsulta = "titTipo")
	@Column(name = "tit_tipo")
	private String titTipo;
	
	@IdentificaCampoPesquisa(descricaoCampo = "Valor Pago", campoConsulta = "titValorPago")
	@Column(name = "tit_valorpago", scale = 4, precision = 15)
	private BigDecimal titValorPago = BigDecimal.ZERO;
	
	@IdentificaCampoPesquisa(descricaoCampo = "Usuário Abertura", campoConsulta = "entCodigoAbertura.entNomeFantasia", principal = 1)
	@Basic
	@ManyToOne
	@JoinColumn(nullable = false, name = "ent_codigoabertura", updatable = false)
	@ForeignKey(name = "ent_codigoabertura_fk")
	private Entidade entCodigoAbertura = new Entidade();

	@IdentificaCampoPesquisa(descricaoCampo = "Entidade Responsável", campoConsulta = "entCodigo.entNomeFantasia")
	@Basic
	@ManyToOne
	@JoinColumn(nullable = false, name = "ent_codigo", updatable = false)
	@ForeignKey(name = "ent_codigo_fk")
	private Entidade entCodigo = new Entidade();
	
	@Version
	@Column(name = "versionNum")
	private int versionNum;

	public Long getTitCodigo() {
		return titCodigo;
	}

	public void setTitCodigo(Long titCodigo) {
		this.titCodigo = titCodigo;
	}

	public String getTitOrigem() {
		return titOrigem;
	}

	public void setTitOrigem(String titOrigem) {
		this.titOrigem = titOrigem;
	}

	public BigDecimal getTitValor() {
		return titValor;
	}

	public void setTitValor(BigDecimal titValor) {
		this.titValor = titValor;
	}

	public Date getTitDataHora() {
		return titDataHora;
	}

	public void setTitDataHora(Date titDataHora) {
		this.titDataHora = titDataHora;
	}

	public Boolean getTitBaixado() {
		return titBaixado;
	}

	public void setTitBaixado(Boolean titBaixado) {
		this.titBaixado = titBaixado;
	}

	public String getTitTipo() {
		return titTipo;
	}

	public void setTitTipo(String titTipo) {
		this.titTipo = titTipo;
	}

	public BigDecimal getTitValorPago() {
		return titValorPago;
	}

	public void setTitValorPago(BigDecimal titValorPago) {
		this.titValorPago = titValorPago;
	}

	public Entidade getEntCodigoAbertura() {
		return entCodigoAbertura;
	}

	public void setEntCodigoAbertura(Entidade entCodigoAbertura) {
		this.entCodigoAbertura = entCodigoAbertura;
	}

	public Entidade getEntCodigo() {
		return entCodigo;
	}

	public void setEntCodigo(Entidade entCodigo) {
		this.entCodigo = entCodigo;
	}

	public int getVersionNum() {
		return versionNum;
	}

	public void setVersionNum(int versionNum) {
		this.versionNum = versionNum;
	}
	
}
