package br.com.rhscdeveloper.model;

import java.io.Serializable;
import java.util.Objects;

public class MovimentoFinanceiroId implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer idRegra;
	private Integer idMovimento;

	public MovimentoFinanceiroId() {
		
	}
	
	public MovimentoFinanceiroId(Integer idRegra, Integer idMovimento) {
		this.idRegra = idRegra;
		this.idMovimento = idMovimento;
	}

	public Integer getIdRegra() {
		return idRegra;
	}

	public void setIdRegra(Integer idRegra) {
		this.idRegra = idRegra;
	}

	public Integer getIdMovimento() {
		return idMovimento;
	}

	public void setIdMovimento(Integer idMovimento) {
		this.idMovimento = idMovimento;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idMovimento, idRegra);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MovimentoFinanceiroId other = (MovimentoFinanceiroId) obj;
		return Objects.equals(idMovimento, other.idMovimento) && Objects.equals(idRegra, other.idRegra);
	}
	
}
