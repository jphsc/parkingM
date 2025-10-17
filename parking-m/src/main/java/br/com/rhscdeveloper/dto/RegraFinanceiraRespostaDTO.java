package br.com.rhscdeveloper.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.rhscdeveloper.enumerator.Enums.TipoRequest;
import br.com.rhscdeveloper.model.RegraFinanceiraVO;
import br.com.rhscdeveloper.util.MensagemResposta;

public class RegraFinanceiraRespostaDTO {

	private List<RegraFinanceiraDTO> registros;
	private String mensagem;
	private Long quantidade;
	private Integer pagina;
	
	public static RegraFinanceiraRespostaDTO newInstance(List<RegraFinanceiraVO> registrosPersistentes, TipoRequest tipoRequest) {
		
		List<RegraFinanceiraDTO> registros = new ArrayList<RegraFinanceiraDTO>();
		RegraFinanceiraRespostaDTO resposta = new RegraFinanceiraRespostaDTO();
		String mensagem = registrosPersistentes.isEmpty() && !tipoRequest.equals(TipoRequest.EXCLUIR)
				? "Não foram encontrados registros" 
				: new MensagemResposta<RegraFinanceiraVO>().gerarMensagem(tipoRequest, RegraFinanceiraVO.class);
		
		registrosPersistentes.stream().map(
			vo -> new RegraFinanceiraDTO.Builder()
			.setDtHrFimValidade(vo.getDtHrFimValidade())
			.setDescricao(vo.getDescricao())
			.setDtHrInicioValidade(vo.getDtHrInicioValidade())
			.setId(vo.getId()).setSituacao(vo.getSituacao())
			.setTipoCobranca(vo.getTipoCobranca())
			.setTipoMovimento(vo.getTipoMovimento())
			.setValor(vo.getValor())
			.setVersao(vo.getVersao()).build()
		).forEach(registros::add);
		
		resposta.setRegistros(registros);
		resposta.setQuantidade(Long.valueOf(registros.size()));
		resposta.setPagina(1); // TODO Paginacao
		resposta.setMensagem(mensagem);
		
		return resposta;
	}

	public List<RegraFinanceiraDTO> getRegistros() {
		return registros;
	}

	public void setRegistros(List<RegraFinanceiraDTO> registros) {
		this.registros = registros;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Long getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}

	public Integer getPagina() {
		return pagina;
	}

	public void setPagina(Integer pagina) {
		this.pagina = pagina;
	}
}
