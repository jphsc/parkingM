package br.com.rhscdeveloper.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import br.com.rhscdeveloper.model.MovimentoFinanceiroVO;
import br.com.rhscdeveloper.model.MovimentoVeiculoVO;
import br.com.rhscdeveloper.model.RegraFinanceiraVO;

public class MovimentoFinanceiroDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private RegraFinanceiraVO idRegra;
    private MovimentoVeiculoVO idMovimento;
	private Double valor;
	private Integer situacao;
	private LocalDateTime versao;
	
	public MovimentoFinanceiroDTO() {
		
	}

	public MovimentoFinanceiroDTO(RegraFinanceiraVO idRegra, MovimentoVeiculoVO idMovimento, Double valor,
			Integer situacao, LocalDateTime versao) {
		this.idRegra = idRegra;
		this.idMovimento = idMovimento;
		this.valor = valor;
		this.situacao = situacao;
		this.versao = versao;
	}

	public RegraFinanceiraVO getIdRegra() {
		return idRegra;
	}

	public void setIdRegra(RegraFinanceiraVO idRegra) {
		this.idRegra = idRegra;
	}

	public MovimentoVeiculoVO getIdMovimento() {
		return idMovimento;
	}

	public void setIdMovimento(MovimentoVeiculoVO idMovimento) {
		this.idMovimento = idMovimento;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Integer getSituacao() {
		return situacao;
	}

	public void setSituacao(Integer situacao) {
		this.situacao = situacao;
	}

	public LocalDateTime getVersao() {
		return versao;
	}

	public void setVersao(LocalDateTime versao) {
		this.versao = versao;
	}
	
	public static MovimentoFinanceiroDTO newInstance(MovimentoFinanceiroVO vo) {
		return new MovimentoFinanceiroDTO(vo.getRegra(), vo.getMovimento(), vo.getValor(), vo.getSituacao(), vo.getVersao());
	}
}
