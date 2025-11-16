package br.com.rhscdeveloper.service;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.jboss.logging.Logger;

import br.com.rhscdeveloper.dto.ErroDTO;
import br.com.rhscdeveloper.dto.MovimentoVeiculoDTO;
import br.com.rhscdeveloper.dto.MovimentoVeiculoRespostaDTO;
import br.com.rhscdeveloper.enumerator.Enums;
import br.com.rhscdeveloper.enumerator.Enums.SituacaoMovimento;
import br.com.rhscdeveloper.enumerator.Enums.TipoMovimento;
import br.com.rhscdeveloper.enumerator.Enums.TipoOperacao;
import br.com.rhscdeveloper.exception.GlobalException;
import br.com.rhscdeveloper.model.MovimentoFinanceiroVO;
import br.com.rhscdeveloper.model.MovimentoVeiculoVO;
import br.com.rhscdeveloper.model.RegraFinanceiraVO;
import br.com.rhscdeveloper.model.VeiculoVO;
import br.com.rhscdeveloper.util.ConstantesSistema;
import br.com.rhscdeveloper.util.GeracaoException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class MovimentoVeiculoService {
	private static final Logger LOG = Logger.getLogger(GlobalException.class);
	
	public MovimentoVeiculoRespostaDTO criarMovimentoVeiculo(MovimentoVeiculoDTO dto) {
		
		try {
			VeiculoVO veiculo = (VeiculoVO) VeiculoVO.findByIdOptional(dto.getIdVeiculo()).orElseThrow(() -> new NullPointerException("Veículo de id "+dto.getIdVeiculo()+" não encontrado"));
			RegraFinanceiraVO regraFinanceira = (RegraFinanceiraVO) RegraFinanceiraVO.findByIdOptional(dto.getIdRegra()).orElseThrow(() -> new NullPointerException("Regra de id "+dto.getIdRegra()+" não encontrada"));;
			Double valor = 0.0;
			
			TipoMovimento tipoMovimento = (TipoMovimento) (Enums.getEnum(dto.getTipoMovimento()));
			SituacaoMovimento sitMovFinanceiro = (tipoMovimento == TipoMovimento.FINAL_SEMANA || tipoMovimento == TipoMovimento.MENSALISTA) 
					? SituacaoMovimento.ENCERRADO 
					: SituacaoMovimento.ABERTO ;
		
			MovimentoVeiculoVO movVeiculo = new MovimentoVeiculoVO.Builder()
					.setVeiculoVO(veiculo)
					.setTipoMovimento(tipoMovimento.getId())
					.setDtHrEntrada(dto.getDtHrEntrada())
					.setSituacao(SituacaoMovimento.ABERTO.getId()) // mv sempre nasce aberto | financeiro depende do tpMov
					.setVersao(new Date()).build();
			movVeiculo = (MovimentoVeiculoVO) persistirObjetoTransacional(movVeiculo);
			
			if(tipoMovimento == TipoMovimento.FINAL_SEMANA || tipoMovimento == TipoMovimento.MENSALISTA) {
				valor = this.gerarValorMovimento(movVeiculo, regraFinanceira, dto.getDtHrEntrada());
				
				MovimentoFinanceiroVO movFinanceiro = new MovimentoFinanceiroVO(regraFinanceira, movVeiculo, valor, sitMovFinanceiro , new Date());
				movFinanceiro = (MovimentoFinanceiroVO) persistirObjetoTransacional(movFinanceiro);
			}
			return MovimentoVeiculoRespostaDTO.newInstance(Arrays.asList(movVeiculo), TipoOperacao.CADASTRAR);
			
		} catch (NullPointerException e) {
			LOG.warn(e.getMessage());
			throw new GlobalException(ConstantesSistema.COD_ERRO_INEXISTENTE, e.getMessage());
			
		} catch (Exception e) {
			LOG.error(e.getMessage());
			e.printStackTrace();
			ErroDTO erro = GeracaoException.mensagemExceptionGenerica(e);
			throw new GlobalException(erro.getCodigo(), erro.getMensagem());
		}
	}
	
	@Transactional
	public Object persistirObjetoTransacional(Object objeto) {
		
		if(objeto instanceof MovimentoVeiculoVO) {
			MovimentoVeiculoVO.persist(objeto);
		} else if(objeto instanceof MovimentoFinanceiroVO) {
			MovimentoFinanceiroVO.persist(objeto);
		}
		return objeto;
	}

	public MovimentoVeiculoRespostaDTO obterMovVeiculoById(Integer id) {
		
		try {
			MovimentoVeiculoVO vo = MovimentoVeiculoVO.findById(id);
			MovimentoVeiculoRespostaDTO resposta = MovimentoVeiculoRespostaDTO.newInstance(Arrays.asList(vo), TipoOperacao.CONSULTAR);
			obterSetarRegraFinanceiraMovVeiculo(Arrays.asList(vo), resposta);
			
			return resposta;
		} catch (NullPointerException e) {
			LOG.warn(e.getMessage());
			throw new GlobalException(ConstantesSistema.COD_ERRO_INEXISTENTE, ConstantesSistema.MSG_ERRO_NAO_ENCONTRADO);
			
		} catch (Exception e) {
			LOG.error(e.getStackTrace());
			ErroDTO erro = GeracaoException.mensagemExceptionGenerica(e);
			throw new GlobalException(erro.getCodigo(), erro.getMensagem());
		}
	}
	
	public MovimentoVeiculoRespostaDTO obterMovsVeiculo() {
		try {
			List<MovimentoVeiculoVO> movsVeiculo = MovimentoVeiculoVO.findAll().list();
			MovimentoVeiculoRespostaDTO resposta = MovimentoVeiculoRespostaDTO.newInstance(movsVeiculo, TipoOperacao.CONSULTAR);
			
			obterSetarRegraFinanceiraMovVeiculo(movsVeiculo, resposta);
			
			return resposta;
		} catch (NullPointerException e) {
			LOG.warn(e.getMessage());
			throw new GlobalException(ConstantesSistema.COD_ERRO_INEXISTENTE, ConstantesSistema.MSG_ERRO_NAO_ENCONTRADO);
			
		} catch (Exception e) {
			LOG.error(e.getStackTrace());
			ErroDTO erro = GeracaoException.mensagemExceptionGenerica(e);
			throw new GlobalException(erro.getCodigo(), erro.getMensagem());
		}
	}
	
	public MovimentoVeiculoRespostaDTO encerrarMovVeiculo(MovimentoVeiculoDTO dto) {
		
		try {
			MovimentoVeiculoVO movVeiculo = (MovimentoVeiculoVO) MovimentoVeiculoVO.findByIdOptional(dto.getId()).orElseThrow(() -> new NullPointerException("Movimento de id "+dto.getId()+" não encontrado"));
			RegraFinanceiraVO regra = (RegraFinanceiraVO) RegraFinanceiraVO.findByIdOptional(dto.getIdRegra()).orElseThrow(() -> new NullPointerException("Regra de id não "+dto.getIdRegra()+" encontrada"));;
			Double valor = gerarValorMovimento(movVeiculo, regra, dto.getDtHrEntrada());
			
			MovimentoFinanceiroVO movFinanceiro = new MovimentoFinanceiroVO(regra, movVeiculo, valor, SituacaoMovimento.ENCERRADO, new Date());
			movFinanceiro = (MovimentoFinanceiroVO) persistirObjetoTransacional(movFinanceiro);
//			MovimentoFinanceiroVO.persist(movFinanceiro);
			
			MovimentoVeiculoRespostaDTO resposta = MovimentoVeiculoRespostaDTO.newInstance(Arrays.asList(movVeiculo), TipoOperacao.EDITAR);
			return resposta;
		} catch(NullPointerException e) {
			LOG.warn(e.getMessage());
			throw new GlobalException(ConstantesSistema.COD_ERRO_VALIDACAO, e.getMessage());
			
		} catch (Exception e) {
			LOG.error(e.getStackTrace());
			ErroDTO erro = GeracaoException.mensagemExceptionGenerica(e);
			throw new GlobalException(erro.getCodigo(), erro.getMensagem());
		}
		
	}
	
	/**
	 * Calcula o valor do movimento financeiro
	 * 
	 * <p>Dependendo do tipo de movimento (mensalista, final de semana, etc.), aplica regras diferentes:
	 * - Para mensalistas ou finais de semana, retorna o valor fixo da regra.
	 * - Para demais casos, calcula o valor com base no número de horas e valor da regra.
	 * 
	 * @param movimento - movimento do veículo do tipo MovimentoVeiculoVO
	 * @param regraFinanceira - regra financeira aplicável do tipo RegraFinanceiraVO
	 * @param dataHoraEntrada - Data e hora de entrada do veículo
	 * @return valor calculado do movimento do veiculo, considerando sua regra e entrada
	 *  */
	private Double gerarValorMovimento(MovimentoVeiculoVO movimento, RegraFinanceiraVO regra, Date entrada) {
		Duration duracao = Duration.between(entrada.toInstant(), Instant.now());
		long dias = Duration.ofDays(duracao.toMinutes()).toDays();
		int horas = (int) Math.ceil(duracao.toMinutes()/60);
		Double valor = 0.0;
		
		try {
			TipoMovimento tipoMovimento = (TipoMovimento) (Enums.getEnum(movimento.getTipoMovimento()));
			
			switch(tipoMovimento) {
				case HORA:
					valor = regra.getValor() * horas;
					break;
				case DIA:
					valor = regra.getValor() * dias;
					break;
				default:
					valor = regra.getValor();
			}
			
			return valor;
		} catch (Exception e) {
			LOG.warn(e.getMessage());
			ErroDTO erro = GeracaoException.mensagemExceptionGenerica(e);
			throw new GlobalException(erro.getCodigo(), erro.getMensagem());
		}
	}
	
	private void obterSetarRegraFinanceiraMovVeiculo(List<MovimentoVeiculoVO> movsVeiculo, MovimentoVeiculoRespostaDTO resposta) {
		for(MovimentoVeiculoVO mvv : movsVeiculo) {
			
			try {
				MovimentoFinanceiroVO mvf = mvv.getMovFinanceiro();
				
				if(!Objects.isNull(mvf)) {
					resposta.getRegistros().stream().filter(mvvPers -> mvvPers.getId() == mvf.getMovimento().getId()).forEach(mvvP -> mvvP.setIdRegra(mvf.getRegra().getId()));
				}
				
			} catch (NullPointerException e) {
				LOG.info(e.getMessage());
				throw new GlobalException(ConstantesSistema.COD_ERRO_VALIDACAO, e.getMessage());
				
			} catch (Exception e) {
				LOG.error(e.getMessage());
				e.printStackTrace();
				ErroDTO erro = GeracaoException.mensagemExceptionGenerica(e);
				throw new GlobalException(erro.getCodigo(), erro.getMensagem());
			}
		}
	}
}
