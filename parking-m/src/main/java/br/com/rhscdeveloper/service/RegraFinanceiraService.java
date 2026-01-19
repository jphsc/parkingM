package br.com.rhscdeveloper.service;

import static java.util.Objects.isNull;

import java.util.Arrays;
import java.util.Date;

import org.hibernate.PropertyValueException;
import org.jboss.logging.Logger;
import org.jboss.resteasy.spi.UnhandledException;

import com.google.gson.Gson;

import br.com.rhscdeveloper.dto.ErroDTO;
import br.com.rhscdeveloper.dto.RegraFinanceiraDTO;
import br.com.rhscdeveloper.dto.RegraFinanceiraRespostaDTO;
import br.com.rhscdeveloper.enumerator.Enums.TipoOperacao;
import br.com.rhscdeveloper.exception.GlobalException;
import br.com.rhscdeveloper.model.RegraFinanceiraVO;
import br.com.rhscdeveloper.util.ConstantesSistema;
import br.com.rhscdeveloper.util.GeracaoException;
import br.com.rhscdeveloper.util.MensagemResposta;
import io.quarkus.arc.ArcUndeclaredThrowableException;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class RegraFinanceiraService {
	
	private static final Logger LOG = Logger.getLogger(GlobalException.class);
	
	public RegraFinanceiraRespostaDTO obterRegraFinanceiraById(Integer id) {
		
		try {
			Thread.sleep(4000);	
			RegraFinanceiraVO vo = RegraFinanceiraVO.findById(id);
			if(isNull(vo)) {
				throw new NullPointerException(ConstantesSistema.MSG_ERRO_NAO_ENCONTRADO);
			}
			
			return RegraFinanceiraRespostaDTO.newInstance(Arrays.asList(vo), TipoOperacao.CONSULTAR);
		} catch (UnhandledException | NullPointerException e) {
			LOG.info(e.getMessage());
			throw new GlobalException(ConstantesSistema.COD_ERRO_INEXISTENTE, e.getMessage());
			
		} catch (Exception e) {
			LOG.error(e.getMessage());
			e.printStackTrace();
			ErroDTO erro = GeracaoException.mensagemExceptionGenerica(e);
			throw new GlobalException(erro.getCodigo(), erro.getMensagem());
		}
	}

	public RegraFinanceiraRespostaDTO obterRegrasFinanceiras() {
		try {
			Thread.sleep(4000);	
			return RegraFinanceiraRespostaDTO.newInstance(RegraFinanceiraVO.findAll(Sort.by("id")).list(), TipoOperacao.CONSULTAR);
		} catch (Exception e) {
			LOG.error(e.getMessage());
			e.printStackTrace();
			ErroDTO erro = GeracaoException.mensagemExceptionGenerica(e);
			throw new GlobalException(erro.getCodigo(), erro.getMensagem());
		}
	}

	public RegraFinanceiraRespostaDTO obterRegraFinanceiraFiltro(RegraFinanceiraDTO filtro) {
		try {
			Thread.sleep(4000);	
			return RegraFinanceiraRespostaDTO.newInstance(RegraFinanceiraVO.findAll(filtro), TipoOperacao.CONSULTAR);
		} catch (NullPointerException e) {
			LOG.info(e.getMessage());
			throw new GlobalException(ConstantesSistema.COD_ERRO_INEXISTENTE, ConstantesSistema.MSG_ERRO_NAO_ENCONTRADO);	
		
		} catch (Exception e) {
			LOG.error(e.getMessage());
			e.printStackTrace();
			ErroDTO erro = GeracaoException.mensagemExceptionGenerica(e);
			throw new GlobalException(erro.getCodigo(), erro.getMensagem());
		}
	}

	// TODO implementar validacao dos enumeradores
	public RegraFinanceiraRespostaDTO atualizarRegraFinanceira(RegraFinanceiraDTO filtro) {
		
		try {
			Thread.sleep(4000);	
			if(filtro.getId() == 0 || filtro.getId().equals(null) || isNull(filtro.getId())) {
				throw new NoSuchFieldError(ConstantesSistema.MSG_ERRO_ID);
			}
			
			RegraFinanceiraVO newVo = atualizarRegraFinanceiraTransacional(filtro);
			
			return RegraFinanceiraRespostaDTO.newInstance(Arrays.asList(newVo), TipoOperacao.EDITAR);
		} catch (NullPointerException e) {
			LOG.info(e.getMessage());
			throw new GlobalException(ConstantesSistema.COD_ERRO_INEXISTENTE, ConstantesSistema.MSG_ERRO_NAO_ENCONTRADO);
			
		} catch(PropertyValueException e) { 
			LOG.info(e.getMessage());
			throw new GlobalException(ConstantesSistema.COD_ERRO_VALIDACAO, ConstantesSistema.MSG_ERRO_NULL);
		
		} catch (Exception e) {
			LOG.error(e.getMessage());
			e.printStackTrace();
			ErroDTO erro = GeracaoException.mensagemExceptionGenerica(e);
			throw new GlobalException(erro.getCodigo(), erro.getMensagem());
		}
	}
	
	@Transactional
	public RegraFinanceiraVO atualizarRegraFinanceiraTransacional(RegraFinanceiraDTO filtro) {
		RegraFinanceiraVO voPersistente = RegraFinanceiraVO.findById(filtro.getId());
		return RegraFinanceiraVO.dtoToVo(voPersistente, filtro);
	} 
	
	// TODO implementar validacao dos enumeradores
	public RegraFinanceiraRespostaDTO cadastrarRegraFinanceira(RegraFinanceiraDTO filtro) {
		
		try {
			Thread.sleep(4000);	
			RegraFinanceiraVO vo = new RegraFinanceiraVO.Builder().setDescricao(filtro.getDescricao())
					.setDtHrInicioValidade(filtro.getDtHrInicioValidade()).setDtHrFimValidade(filtro.getDtHrFimValidade())
					.setSituacao(filtro.getSituacao()).setTipoCobranca(filtro.getTipoCobranca())
					.setTipoMovimento(filtro.getTipoMovimento()).setValor(filtro.getValor()).setVersao(new Date())
					.build();
			
			criarRegraFinanceiraTransacional(vo);
			return RegraFinanceiraRespostaDTO.newInstance(Arrays.asList(vo), TipoOperacao.CADASTRAR);
			
		} catch(PropertyValueException e) { 
			LOG.info(e.getMessage());
			throw new GlobalException(ConstantesSistema.COD_ERRO_VALIDACAO, ConstantesSistema.MSG_ERRO_NULL);
			
		} catch (Exception e) {
			LOG.error(e.getMessage());
			e.printStackTrace();
			ErroDTO erro = GeracaoException.mensagemExceptionGenerica(e);
			throw new GlobalException(erro.getCodigo(), erro.getMensagem());
		}
	}
	
	@Transactional
	protected RegraFinanceiraVO criarRegraFinanceiraTransacional(RegraFinanceiraVO vo) {
		RegraFinanceiraVO.persist(vo);
		return vo;
	}
	
	/**
	 * <p>Remove uma regra financeira pelo identificador da regra.</p>
	 * @param id - Integer - Identificador da regra
	 * */
	public String deletarRegraFinanceira(Integer id) {
		try {
			Thread.sleep(4000);	
			RegraFinanceiraVO regra = RegraFinanceiraVO.findById(id);
			
			if(isNull(regra)) {
				throw new NullPointerException(ConstantesSistema.MSG_ERRO_NAO_ENCONTRADO); 
			}
			
			deletarRegraFinanceiraTransacional(id);
			return new Gson().toJson(new MensagemResposta().gerarMensagem(TipoOperacao.EXCLUIR, RegraFinanceiraVO.class));
			
		} catch (NullPointerException e) {
			LOG.info(e.getMessage());
			throw new GlobalException(ConstantesSistema.COD_ERRO_INEXISTENTE, ConstantesSistema.MSG_ERRO_NAO_ENCONTRADO);
			
		} catch (ArcUndeclaredThrowableException e) {
			LOG.info(e.getCause());
			throw new GlobalException(ConstantesSistema.COD_ERRO_VALIDACAO, ConstantesSistema.MSG_ERRO_VINCULO);
			
		} catch (Exception e) {
			LOG.error(e.getMessage());
			e.printStackTrace();
			ErroDTO erro = GeracaoException.mensagemExceptionGenerica(e);
			throw new GlobalException(erro.getCodigo(), erro.getMensagem());
		}
	}
	
	@Transactional
	protected Boolean deletarRegraFinanceiraTransacional(Integer id) {
		return RegraFinanceiraVO.deleteById(id);
	}
}
