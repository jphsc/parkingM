package br.com.rhscdeveloper.service;

import static java.util.Objects.isNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import org.hibernate.PropertyValueException;
import org.jboss.logging.Logger;
import org.jboss.resteasy.spi.UnhandledException;

import com.google.gson.Gson;

import br.com.rhscdeveloper.dto.ErroDTO;
import br.com.rhscdeveloper.dto.RegraFinanceiraDTO;
import br.com.rhscdeveloper.dto.RegraFinanceiraRespostaDTO;
import br.com.rhscdeveloper.enumerator.Enums.Situacao;
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
			RegraFinanceiraVO vo = (RegraFinanceiraVO) RegraFinanceiraVO
					.findByIdOptional(id).orElseThrow(() -> new NoSuchElementException(ConstantesSistema.MSG_ERRO_NAO_ENCONTRADO));
			
			return RegraFinanceiraRespostaDTO.newInstance(Arrays.asList(vo), TipoOperacao.CONSULTAR);
		} catch (UnhandledException | NoSuchElementException e) {
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
			
			List<RegraFinanceiraVO> regras = RegraFinanceiraVO.findAll(Sort.by("id")).list();
			
			if(regras.isEmpty()) {
				throw new NoSuchElementException(ConstantesSistema.MSG_ERRO_NAO_ENCONTRADO);
			}
			
			return RegraFinanceiraRespostaDTO.newInstance(regras, TipoOperacao.CONSULTAR);
			
		} catch (NoSuchElementException e) {
			LOG.warn(e.getMessage());
			ErroDTO erro = GeracaoException.mensagemExceptionGenerica(e);
			throw new GlobalException(erro.getCodigo(), erro.getMensagem());
			
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
			
			List<RegraFinanceiraVO> regras = RegraFinanceiraVO.findAll(filtro);
			
			if(regras.isEmpty()) {
				throw new NoSuchElementException(ConstantesSistema.MSG_ERRO_NAO_ENCONTRADO);
			}
			
			return RegraFinanceiraRespostaDTO.newInstance(regras, TipoOperacao.CONSULTAR);
		} catch (NoSuchElementException e) {
			LOG.info(e.getMessage());
			throw new GlobalException(ConstantesSistema.COD_ERRO_INEXISTENTE, e.getMessage());	
		
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
			
			RegraFinanceiraVO newVo = this.atualizarRegraFinanceiraTransacional(filtro);
			
			return RegraFinanceiraRespostaDTO.newInstance(Arrays.asList(newVo), TipoOperacao.EDITAR);
		} catch (NoSuchElementException e) {
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
		
		if(filtro.getDtFimValidade().isBefore(LocalDate.now())) {
			filtro.setSituacao(Situacao.INATIVO.getId());
		}
		
		return RegraFinanceiraVO.dtoToVo(voPersistente, filtro);
	} 
	
	// TODO implementar validacao dos enumeradores
	public RegraFinanceiraRespostaDTO cadastrarRegraFinanceira(RegraFinanceiraDTO filtro) {
		
		try {
			Thread.sleep(4000);	
			RegraFinanceiraVO vo = new RegraFinanceiraVO.Builder().setDescricao(filtro.getDescricao())
					.setDtInicioValidade(filtro.getDtInicioValidade()).setDtFimValidade(filtro.getDtFimValidade())
					.setSituacao(filtro.getSituacao()).setTipoCobranca(filtro.getTipoCobranca())
					.setTipoMovimento(filtro.getTipoMovimento()).setValor(filtro.getValor()).setVersao(LocalDateTime.now())
					.build();
			
			this.criarRegraFinanceiraTransacional(vo);
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
	 * @param id - Identificador da regra
	 * */
	public String deletarRegraFinanceira(Integer id) {
		try {
			Thread.sleep(4000);	
			RegraFinanceiraVO regra = RegraFinanceiraVO.findById(id);
			
			if(isNull(regra)) {
				throw new NoSuchElementException(ConstantesSistema.MSG_ERRO_NAO_ENCONTRADO); 
			}
			
			this.deletarRegraFinanceiraTransacional(id);
			return new Gson().toJson(new MensagemResposta().gerarMensagem(TipoOperacao.EXCLUIR, RegraFinanceiraVO.class));
			
		} catch (NoSuchElementException e) {
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
