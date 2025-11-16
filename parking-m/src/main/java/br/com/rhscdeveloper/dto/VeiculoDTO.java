package br.com.rhscdeveloper.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

public class VeiculoDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String modelo;
	private String montadora;
	private Date dtRegistro;
	private String placa;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Date versao;
	@JsonProperty(access = Access.WRITE_ONLY)
	private Integer pagina;
	
	public VeiculoDTO() {
		
	}

	public VeiculoDTO(Integer id, String modelo, String montadora, Date dtRegistro, String placa, Date versao) {
		this.id = id;
		this.modelo = modelo;
		this.montadora = montadora;
		this.dtRegistro = dtRegistro;
		this.placa = placa;
		this.versao = versao;
	}
	
	public VeiculoDTO(Builder build) {
		this.id = build.id;
		this.modelo = build.modelo;
		this.montadora = build.montadora;
		this.dtRegistro = build.dtRegistro;
		this.placa = build.placa;
		this.versao = build.versao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getMontadora() {
		return montadora;
	}

	public void setMontadora(String montadora) {
		this.montadora = montadora;
	}

	public Date getDtRegistro() {
		return dtRegistro;
	}

	public void setDtRegistro(Date dtRegistro) {
		this.dtRegistro = dtRegistro;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public Date getVersao() {
		return versao;
	}

	public void setVersao(Date versao) {
		this.versao = versao;
	}

	public Integer getPagina() {
		return pagina;
	}

	public void setPagina(Integer pagina) {
		this.pagina = pagina;
	}
	
	@Override
	public String toString() {
		return "VeiculoDTO [id=" + id + ", modelo=" + modelo + ", montadora=" + montadora + ", dtRegistro=" + dtRegistro
				+ ", placa=" + placa + ", versao=" + versao + ", pagina=" + pagina + "]";
	}

	public static class Builder {
		
		private Integer id;
		private String modelo;
		private String montadora;
		private Date dtRegistro;
		private String placa;
		private Date versao;
		
		public VeiculoDTO build() {
			return new VeiculoDTO(this);
		}

		public Builder setId(Integer id) {
			this.id = id;
			return this;
		}

		public Builder setModelo(String modelo) {
			this.modelo = modelo;
			return this;
		}

		public Builder setMontadora(String montadora) {
			this.montadora = montadora;
			return this;
		}

		public Builder setDtRegistro(Date dtRegistro) {
			this.dtRegistro = dtRegistro;
			return this;
		}

		public Builder setPlaca(String placa) {
			this.placa = placa;
			return this;
		}

		public Builder setVersao(Date versao) {
			this.versao = versao;
			return this;
		}
	}
}
