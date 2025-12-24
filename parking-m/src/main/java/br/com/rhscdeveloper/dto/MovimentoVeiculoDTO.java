package br.com.rhscdeveloper.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MovimentoVeiculoDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer idVeiculo;
	private Integer idRegra;
	private String placa;
	private Integer tipoMovimento;
	private Date dtHrEntrada;
	private Date dtHrSaida;
	private Integer situacao;
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Date versao;
    
    public MovimentoVeiculoDTO() {
    	
    }

	public MovimentoVeiculoDTO(Integer id, Integer idVeiculo, Integer idRegra, String placa, Integer tipoMovimento, Date dtHrEntrada, 
			Date dtHrSaida, Integer situacao, Date versao) {
		this.id = id;
		this.idVeiculo = idVeiculo;
		this.idRegra = idRegra;
		this.placa = placa;
		this.tipoMovimento = tipoMovimento;
		this.dtHrEntrada = dtHrEntrada;
		this.dtHrSaida = dtHrSaida;
		this.situacao = situacao;
		this.versao = versao;
	}
	
	public MovimentoVeiculoDTO(Builder b) {
		this.id = b.id;
		this.idVeiculo = b.idVeiculo;
		this.idRegra = b.idRegra;
		this.placa = b.placa;
		this.tipoMovimento = b.tipoMovimento;
		this.dtHrEntrada = b.dtHrEntrada;
		this.dtHrSaida = b.dtHrSaida;
		this.situacao = b.situacao;
		this.versao = b.versao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdVeiculo() {
		return idVeiculo;
	}

	public void setIdVeiculo(Integer idVeiculo) {
		this.idVeiculo = idVeiculo;
	}

	public Integer getIdRegra() {
		return idRegra;
	}

	public void setIdRegra(Integer idRegra) {
		this.idRegra = idRegra;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public Integer getTipoMovimento() {
		return tipoMovimento;
	}

	public void setTipoMovimento(Integer tipoMovimento) {
		this.tipoMovimento = tipoMovimento;
	}

	public Date getDtHrEntrada() {
		return dtHrEntrada;
	}

	public void setDtHrEntrada(Date dtHrEntrada) {
		this.dtHrEntrada = dtHrEntrada;
	}

	public Date getDtHrSaida() {
		return dtHrSaida;
	}

	public void setDtHrSaida(Date dtHrSaida) {
		this.dtHrSaida = dtHrSaida;
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
		private Integer idVeiculo;
		private Integer idRegra;
		private String placa;
		private Integer tipoMovimento;
		private Date dtHrEntrada;
		private Date dtHrSaida;
	    private Integer situacao;
	    private Date versao;
		
		public MovimentoVeiculoDTO build() {
			return new MovimentoVeiculoDTO(this);
		}

		public Builder setId(Integer id) {
			this.id = id;
			return this;
		}

		public Builder setIdVeiculo(Integer idVeiculo) {
			this.idVeiculo = idVeiculo;
			return this;
		}

		public Builder setIdRegra(Integer idRegra) {
			this.idRegra = idRegra;
			return this;
		}

		public Builder setPlaca(String placa) {
			this.placa = placa;
			return this;
		}

		public Builder setTipoMovimento(Integer tipoMovimento) {
			this.tipoMovimento = tipoMovimento;
			return this;
		}

		public Builder setDtHrEntrada(Date dtHrEntrada) {
			this.dtHrEntrada = dtHrEntrada;
			return this;
		}

		public Builder setDtHrSaida(Date dtHrSaida) {
			this.dtHrSaida = dtHrSaida;
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
