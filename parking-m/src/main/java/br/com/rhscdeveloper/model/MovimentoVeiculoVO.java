package br.com.rhscdeveloper.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import org.hibernate.annotations.DynamicUpdate;

import br.com.rhscdeveloper.enumerator.Enums.SituacaoMovimento;
import br.com.rhscdeveloper.enumerator.Enums.TipoMovimento;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

@Entity
@DynamicUpdate
@Table(name = "tb_movimento_veiculo")
public class MovimentoVeiculoVO extends PanacheEntityBase implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "mvv_id")
	private Integer id;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "mvv_veiculo", nullable = false, updatable = false, referencedColumnName = "vei_id", 
			foreignKey = @ForeignKey(name = "fk_movimentoveiculo_veiculo_01"))
	private VeiculoVO veiculoVO;
	
	@Column(name = "mvv_tipo_movimento", nullable = false)
	private Integer tipoMovimento;
	
	@Column(name = "mvv_dt_hr_entrada", nullable = false)
	private Date dtHrEntrada;

	@Column(name = "mvv_dt_hr_saida", nullable = false)
	private Date dtHrSaida;
	
	@Column(name = "mvv_situacao", nullable = false)
	private Integer situacao;

	@Version
	@Column(name = "mvv_versao", nullable = false)
	private Date versao;
	
	public MovimentoVeiculoVO() {
		
	}

	public MovimentoVeiculoVO(VeiculoVO veiculoVO, TipoMovimento tipoMovimento, Date dtHrEntrada, Date dtHrSaida, 
			SituacaoMovimento situacao, Date versao) {
		this.veiculoVO = veiculoVO;
		this.tipoMovimento = tipoMovimento.getId();
		this.dtHrEntrada = dtHrEntrada;
		this.dtHrSaida = dtHrSaida;
		this.situacao = situacao.getId();
		this.versao = versao;
	}

	public MovimentoVeiculoVO(Integer id, VeiculoVO veiculoVO, TipoMovimento tipoMovimento, Date dtHrEntrada, Date dtHrSaida, 
			SituacaoMovimento situacao, Date versao) {
		this.id = id;
		this.veiculoVO = veiculoVO;
		this.tipoMovimento = tipoMovimento.getId();
		this.dtHrEntrada = dtHrEntrada;
		this.dtHrSaida = dtHrSaida;
		this.situacao = situacao.getId();
		this.versao = versao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public VeiculoVO getVeiculo() {
		return veiculoVO;
	}

	public void setVeiculo(VeiculoVO veiculoVO) {
		this.veiculoVO = veiculoVO;
	}

	public Integer getTipoMovimento() {
		return tipoMovimento;
	}

	public void setTipoMovimento(TipoMovimento tipoMovimento) {
		this.tipoMovimento = tipoMovimento.getId();
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

	public void setSituacao(SituacaoMovimento situacao) {
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
		MovimentoVeiculoVO other = (MovimentoVeiculoVO) obj;
		return Objects.equals(id, other.id);
	}
}
