package br.com.rhscdeveloper.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.hibernate.annotations.DynamicUpdate;

import br.com.rhscdeveloper.dto.VeiculoDTO;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
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
public class VeiculoVO extends PanacheEntityBase implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "vei_id")
	private Integer id;
	
	@Column(name = "vei_modelo", nullable = false)
	private String modelo;
	
	@Column(name = "vei_montadora", nullable = false)
	private String montadora;
	
	@Column(name = "ve_dt_registro", nullable = false)
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

	public VeiculoVO(String modelo, String montadora, Date dtRegistro, String placa, String cor, Date versao) {
		this.modelo = modelo;
		this.montadora = montadora;
		this.dtRegistro = dtRegistro;
		this.placa = placa;
		this.cor = cor;
		this.versao = versao;
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

	@Override
	public String toString() {
		return "VeiculoVO [id=" + id + ", modelo=" + modelo + ", montadora=" + montadora + ", dtRegistro=" + dtRegistro
				+ ", placa=" + placa + ", cor=" + cor + ", versao=" + versao + "]";
	}

	public static VeiculoVO dtoToVo(VeiculoVO voPersistente, VeiculoDTO dto) {
		voPersistente.id = dto.getId();
		voPersistente.modelo = dto.getModelo();
		voPersistente.montadora = dto.getMontadora();
		voPersistente.dtRegistro = dto.getDtRegistro();
		voPersistente.placa = dto.getPlaca();
		voPersistente.cor = dto.getCor();
		voPersistente.versao = dto.getVersao();
		
		return voPersistente;
	}
	
	public static VeiculoVO findByPlaca(String placa) {
		return find("placa", placa).firstResult();
	}
	
	public static List<VeiculoVO> findByMontadora(String montadora) {
		return find("montadora like ?1", "".concat("%").concat(montadora).concat("%")).list();
	}
	
	// TODO Paginar	
	public static List<VeiculoVO> findAll(VeiculoDTO filtro){
		Map<String, Object> parametros = new HashMap<String, Object>();
		StringBuilder sb = new StringBuilder("1 = 1");
		
		if(filtro.getId() != null && filtro.getId() != 0) {
			sb.append(" and id =: id");
			parametros.put("id", filtro.getId());
		}
		
		if(filtro.getModelo() != null && !filtro.getModelo().isBlank()) {
			sb.append(" and modelo =: modelo");
			parametros.put("modelo", filtro.getModelo());
		}
		
		if(filtro.getMontadora() != null && !filtro.getMontadora().isBlank()) {
			sb.append(" and montadora =: montadora");
			parametros.put("montadora", filtro.getMontadora());
		}
		
		if(filtro.getPlaca() != null && !filtro.getPlaca().isBlank()) {
			sb.append(" and placa =: placa");
			parametros.put("placa", filtro.getPlaca());
		}
		
		if(filtro.getCor() != null && !filtro.getCor().isBlank()) {
			sb.append(" and cor =: cor");
			parametros.put("cor", filtro.getCor());
		}
		
		PanacheQuery<VeiculoVO> query = find(sb.toString(), parametros);
		
		return query.list();
	}
}
