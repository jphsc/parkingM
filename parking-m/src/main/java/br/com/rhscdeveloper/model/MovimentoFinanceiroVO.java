package br.com.rhscdeveloper.model;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.annotations.DynamicUpdate;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@DynamicUpdate
@Table(name = "tb_movimento_financeiro")
@IdClass(MovimentoFinanceiroId.class)
public class MovimentoFinanceiroVO extends PanacheEntityBase implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
    @ManyToOne
    @JoinColumn(name = "mvf_regra", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "fk_movfinanceiro_regrafin_01"))
    private RegraFinanceiraVO idRegra;

	@Id
    @ManyToOne
    @JoinColumn(name = "mvf_movimento", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "fk_movfinanceiro_movveiculo_01"))
    private MovimentoVeiculoVO idMovimento;
	
	@Column(name = "mvf_valor", nullable = false, precision = 2)
	private Double valor;
	
	@Column(name = "mvf_versao", nullable = false)
	private Date versao;
	
	public MovimentoFinanceiroVO() {
		
	}

	public MovimentoFinanceiroVO(RegraFinanceiraVO regra, MovimentoVeiculoVO movimento, Double valor, Date versao) {
		this.idRegra = regra;
		this.idMovimento = movimento;
		this.valor = valor;
		this.versao = versao;
	}

	public RegraFinanceiraVO getRegra() {
		return idRegra;
	}

	public void setRegra(RegraFinanceiraVO idRegra) {
		this.idRegra = idRegra;
	}

	public MovimentoVeiculoVO getMovimento() {
		return idMovimento;
	}

	public void setMovimento(MovimentoVeiculoVO idMovimento) {
		this.idMovimento = idMovimento;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Date getVersao() {
		return versao;
	}

	public void setVersao(Date versao) {
		this.versao = versao;
	}
}
