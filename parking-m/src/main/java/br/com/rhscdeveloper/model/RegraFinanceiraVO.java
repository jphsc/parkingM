package br.com.rhscdeveloper.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import org.hibernate.annotations.DynamicUpdate;

import br.com.rhscdeveloper.enumerator.Enums.Situacao;
import br.com.rhscdeveloper.enumerator.Enums.TipoCobranca;
import br.com.rhscdeveloper.enumerator.Enums.TipoMovimento;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

@Entity
@DynamicUpdate
@Table(name = "tb_regra_financeira")
public class RegraFinanceiraVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ref_id")
	private Integer id;
	
	@Column(name = "ref_description", nullable = false)
	private String descricao;
	
	@Column(name = "ref_valor", nullable = false, precision = 2)
	private Double valor;
	
	@Column(name = "ref_metodo_pag", nullable = false)
	private Integer tipoCobranca;
	
	@Column(name = "ref_tipo_movimento", nullable = false)
	private Integer tipoMovimento;
	
	@Column(name = "ref_ini_validade", nullable = false)
	private Date dtHrInicioValidade;
	
	@Column(name = "ref_fim_validade", nullable = true)
	private Date dtHrFimValidade;
	
	@Column(name = "ref_situacao", nullable = false)
	private Integer situacao;
	
	@Version
	@Column(name = "ref_versao", nullable = false)
	private Date versao;
	
	public RegraFinanceiraVO() {
		
	}

	public RegraFinanceiraVO(Integer id, String descricao, Double valor, TipoCobranca tipoCobranca, TipoMovimento tipoMovimento,
			Date dtHrInicioValidade, Date dtHrFimValidade, Situacao situacao, Date versao) {
		this.id = id;
		this.descricao = descricao;
		this.valor = valor;
		this.tipoCobranca = tipoCobranca.getId();
		this.tipoMovimento = tipoMovimento.getId();
		this.dtHrInicioValidade = dtHrInicioValidade;
		this.dtHrFimValidade = dtHrFimValidade;
		this.situacao = situacao.getId();
		this.versao = versao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Integer getTipoCobranca() {
		return tipoCobranca;
	}

	public void setTipoCobranca(TipoCobranca tipoCobranca) {
		this.tipoCobranca = tipoCobranca.getId();
	}

	public Integer getTipoMovimento() {
		return tipoMovimento;
	}

	public void setTipoMovimento(TipoMovimento tipoMovimento) {
		this.tipoMovimento = tipoMovimento.getId();
	}

	public Date getDtHrInicioValidade() {
		return dtHrInicioValidade;
	}

	public void setDtHrInicioValidade(Date dtHrInicioValidade) {
		this.dtHrInicioValidade = dtHrInicioValidade;
	}

	public Date getDtHrFimValidade() {
		return dtHrFimValidade;
	}

	public void setDtHrFimValidade(Date dtHrFimValidade) {
		this.dtHrFimValidade = dtHrFimValidade;
	}

	public Integer getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao.getId();
	}

	public Date getVersao() {
		return versao;
	}

	public void setVersao(Date versao) {
		this.versao = versao;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RegraFinanceiraVO other = (RegraFinanceiraVO) obj;
		return Objects.equals(id, other.id);
	}

}
