package br.com.rhscdeveloper.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.hibernate.annotations.DynamicUpdate;

import br.com.rhscdeveloper.dto.RegraFinanceiraDTO;
import br.com.rhscdeveloper.enumerator.Enums.Situacao;
import br.com.rhscdeveloper.enumerator.Enums.TipoCobranca;
import br.com.rhscdeveloper.enumerator.Enums.TipoMovimento;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
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
public class RegraFinanceiraVO extends PanacheEntityBase implements Serializable, Comparable<RegraFinanceiraVO> {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ref_id")
	private Integer id;
	
	@Column(name = "ref_descricao", nullable = false)
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

	public RegraFinanceiraVO(String descricao, Double valor, TipoCobranca tipoCobranca, TipoMovimento tipoMovimento,
			Date dtHrInicioValidade, Date dtHrFimValidade, Situacao situacao, Date versao) {
		this.descricao = descricao;
		this.valor = valor;
		this.tipoCobranca = tipoCobranca.getId();
		this.tipoMovimento = tipoMovimento.getId();
		this.dtHrInicioValidade = dtHrInicioValidade;
		this.dtHrFimValidade = dtHrFimValidade;
		this.situacao = situacao.getId();
		this.versao = versao;
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

	public RegraFinanceiraVO(Builder b) {
		this.id = b.id;
		this.descricao = b.descricao;
		this.valor = b.valor;
		this.tipoCobranca = b.tipoCobranca;
		this.tipoMovimento = b.tipoMovimento;
		this.dtHrInicioValidade = b.dtHrInicioValidade;
		this.dtHrFimValidade = b.dtHrFimValidade;
		this.situacao = b.situacao;
		this.versao = b.versao;
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

	@Override
	public String toString() {
		return "RegraFinanceiraVO [id=" + id + ", descricao=" + descricao + ", valor=" + valor + ", tipoCobranca="
				+ tipoCobranca + ", tipoMovimento=" + tipoMovimento + ", dtHrInicioValidade=" + dtHrInicioValidade
				+ ", dtHrFimValidade=" + dtHrFimValidade + ", situacao=" + situacao + ", versao=" + versao + "]";
	}

	@Override
	public int compareTo(RegraFinanceiraVO o) {
		return this.getId().compareTo(o.getId());
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
		
		public RegraFinanceiraVO build() {
			return new RegraFinanceiraVO(this);
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

	public static RegraFinanceiraVO dtoToVo(RegraFinanceiraVO voPersistente, RegraFinanceiraDTO dto) {
		voPersistente.id = dto.getId();
		voPersistente.descricao = dto.getDescricao();
		voPersistente.valor = dto.getValor();
		voPersistente.tipoCobranca = dto.getTipoCobranca();
		voPersistente.tipoMovimento = dto.getTipoMovimento();
		voPersistente.dtHrInicioValidade = dto.getDtHrInicioValidade();
		voPersistente.dtHrFimValidade = dto.getDtHrFimValidade();
		voPersistente.situacao = dto.getSituacao();
		voPersistente.versao = Objects.isNull(dto.getVersao()) ? new Date() : dto.getVersao();
		
		return voPersistente;
	}
	
	public static List<RegraFinanceiraVO> findByDescricao(String descricao) {
		return find("descricao like ?1", "".concat("%").concat(descricao).concat("%")).list();
	}
	
	// TODO Paginar	
	public static List<RegraFinanceiraVO> findAll(RegraFinanceiraDTO filtro){
		Map<String, Object> parametros = new HashMap<String, Object>();
		StringBuilder sb = new StringBuilder("1 = 1");
		
		if(filtro.getDescricao() != null && !filtro.getDescricao().isBlank()) {
			sb.append(" and descricao =: descricao");
			parametros.put("descricao", filtro.getDescricao());
		}
		
		if(filtro.getValor() != null && filtro.getValor() > 0) {
			sb.append(" and valor =: valor");
			parametros.put("valor", filtro.getValor());
		}
		
		if(filtro.getTipoCobranca() != null && filtro.getTipoCobranca() > 0) {
			sb.append(" and tipoCobranca =: tipoCobranca");
			parametros.put("tipoCobranca", filtro.getTipoCobranca());
		}
		
		if(filtro.getTipoMovimento() != null && filtro.getTipoMovimento() > 0) {
			sb.append(" and tipoMovimento =: tipoMovimento");
			parametros.put("tipoMovimento", filtro.getTipoMovimento());
		}
		
		if(filtro.getDtHrFimValidade() != null && filtro.getDtHrFimValidade() == null && Objects.isNull(filtro.getDtHrFimValidade())) {
			sb.append(" and dtHrFimValidade =: dtHrFimValidade");
			parametros.put("dtHrFimValidade", filtro.getDtHrFimValidade());
		}
		
		if(filtro.getSituacao() != null && filtro.getSituacao() != 0) {
			sb.append(" and situacao =: situacao");
			parametros.put("situacao", filtro.getSituacao());
		}
		
		PanacheQuery<RegraFinanceiraVO> query = find(sb.toString(), parametros);
		
		return query.list();
	}
}
