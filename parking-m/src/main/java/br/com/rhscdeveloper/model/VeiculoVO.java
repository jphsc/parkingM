package br.com.rhscdeveloper.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.Version;

@Entity
@DynamicUpdate
@Table(name = "tb_veiculo", indexes = {@Index(name="ix_veiculo_01", columnList = "vei_placa")})
public class VeiculoVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "vei_id")
	private Integer id;
	
	@Column(name = "vei_modelo", nullable = false)
	private String modelo;
	
	@Column(name = "vei_montadora", nullable = false)
	private String montadora;
	
	@Column(name = "vei_dt_registro", nullable = false)
	private Date dtRegistro;
	
	@Column(name = "vei_placa", nullable = false, unique = true, length = 7)
	private String placa;
	
	@Column(name = "vei_cor", nullable = false)
	private String cor;
	
	@Version
	@Column(name = "vei_versao", nullable = false)
	private Date versao;
	
	@Transient
	private List<MovimentoVeiculoVO> movimentos = new ArrayList<>();
	
	public VeiculoVO() {
		
	}

	public VeiculoVO(Integer id, String modelo, String montadora, Date dtRegistro, String placa, String cor, Date versao) {
		this.id = id;
		this.modelo = modelo;
		this.montadora = montadora;
		this.dtRegistro = dtRegistro;
		this.placa = placa;
		this.cor = cor;
		this.versao = versao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getModel() {
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

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
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
		VeiculoVO other = (VeiculoVO) obj;
		return Objects.equals(id, other.id);
	}
	
	public List<MovimentoVeiculoVO> getMovimentos(){
		return this.movimentos;
	}
	
	public void setMovimentos(List<MovimentoVeiculoVO> movimentos){
		if(!movimentos.isEmpty()) {
			movimentos.forEach(this.movimentos::add);
		}
	}
}
