package br.com.rhscdeveloper.dto;

import java.io.Serializable;
import java.util.Date;

public class RegraFinanceiraDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String descricao;
	private Double valor;
	private Integer tipoCobranca;
	private Integer tipoMovimento;
	private Date dtHrInicioValidade;
	private Date dtHrFimValidade;
	private Integer situacao;
	private Date versao;	
	
	public RegraFinanceiraDTO() {
		
	}

	public RegraFinanceiraDTO(Integer id, String descricao, Double valor, Integer tipoCobranca, Integer tipoMovimento,
			Date dtHrInicioValidade, Date dtHrFimValidade, Integer situacao, Date versao) {
		this.id = id;
		this.descricao = descricao;
		this.valor = valor;
		this.tipoCobranca = tipoCobranca;
		this.tipoMovimento = tipoMovimento;
		this.dtHrInicioValidade = dtHrInicioValidade;
		this.dtHrFimValidade = dtHrFimValidade;
		this.situacao = situacao;
		this.versao = versao;
	}
	
	public RegraFinanceiraDTO(Builder build) {
		this.id = build.id;
		this.descricao = build.descricao;
		this.valor = build.valor;
		this.tipoCobranca = build.tipoCobranca;
		this.tipoMovimento = build.tipoMovimento;
		this.dtHrInicioValidade = build.dtHrInicioValidade;
		this.dtHrFimValidade = build.dtHrFimValidade;
		this.situacao = build.situacao;
		this.versao = build.versao;
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

	public void setTipoCobranca(Integer tipoCobranca) {
		this.tipoCobranca = tipoCobranca;
	}

	public Integer getTipoMovimento() {
		return tipoMovimento;
	}

	public void setTipoMovimento(Integer tipoMovimento) {
		this.tipoMovimento = tipoMovimento;
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

	public void setSituacao(Integer situacao) {
		this.situacao = situacao;
	}

	public Date getVersao() {
		return versao;
	}

	public void setVersao(Date versao) {
		this.versao = versao;
	}
	
	public static class Builder {

		private Integer id;
		private String descricao;
		private Double valor;
		private Integer tipoCobranca;
		private Integer tipoMovimento;
		private Date dtHrInicioValidade;
		private Date dtHrFimValidade;
		private Integer situacao;
		private Date versao;
		
		public RegraFinanceiraDTO build() {
			return new RegraFinanceiraDTO(this);
		}
		
		public Builder setId(Integer id) {
			this.id = id;
			return this;
		}
		
		public Builder setDescricao(String descricao) {
			this.descricao = descricao;
			return this;
		}
		
		public Builder setValor(Double valor) {
			this.valor = valor;
			return this;
		}
		
		public Builder setTipoCobranca(Integer tipoCobranca) {
			this.tipoCobranca = tipoCobranca;
			return this;
		}
		
		public Builder setTipoMovimento(Integer tipoMovimento) {
			this.tipoMovimento = tipoMovimento;
			return this;
		}
		
		public Builder setDtHrInicioValidade(Date dtHrInicioValidade) {
			this.dtHrInicioValidade = dtHrInicioValidade;
			return this;
		}
		
		public Builder setDtHrFimValidade(Date dtHrFimValidade) {
			this.dtHrFimValidade = dtHrFimValidade;
			return this;
		}
		
		public Builder setSituacao(Integer situacao) {
			this.situacao = situacao;
			return this;
		}
		
		public Builder setVersao(Date versao) {
			this.versao = versao;
			return this;
		}
	}
}
