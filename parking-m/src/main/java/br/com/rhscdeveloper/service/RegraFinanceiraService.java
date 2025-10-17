package br.com.rhscdeveloper.service;

import static java.util.Objects.isNull;

import java.util.Arrays;

import org.hibernate.PropertyValueException;
import org.jboss.logging.Logger;
import org.jboss.resteasy.spi.UnhandledException;

import br.com.rhscdeveloper.dto.RegraFinanceiraDTO;
import br.com.rhscdeveloper.dto.RegraFinanceiraRespostaDTO;
import br.com.rhscdeveloper.enumerator.Enums.TipoRequest;
import br.com.rhscdeveloper.exception.GlobalException;
import br.com.rhscdeveloper.model.RegraFinanceiraVO;
import br.com.rhscdeveloper.util.ConstantesSistema;
import io.quarkus.arc.ArcUndeclaredThrowableException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class RegraFinanceiraService {
	
	private static final Logger LOG = Logger.getLogger(GlobalException.class);
	
	public RegraFinanceiraRespostaDTO obterRegraFinanceiraById(Integer id) {
		
		try {
			RegraFinanceiraVO vo = RegraFinanceiraVO.findById(id);
			if(vo == null) {
				throw new NullPointerException(ConstantesSistema.MSG_ERRO_NAO_ENCONTRADO);
			}
			
			return RegraFinanceiraRespostaDTO.newInstance(Arrays.asList(vo), TipoRequest.CONSULTAR);
		} catch (UnhandledException | NullPointerException e) {
			LOG.info(e.getMessage());
			System.out.println("TESTE CAIU AQUI");
			throw new GlobalException(ConstantesSistema.COD_ERRO_INEXISTENTE, e.getMessage());
			
		}	catch (Exception e) {
			LOG.error(e.getStackTrace());
			throw new GlobalException(ConstantesSistema.MSG_ERRO_GENERICO);
		}
	}

	public RegraFinanceiraRespostaDTO obterRegrasFinanceiras() {
		try {
			return RegraFinanceiraRespostaDTO.newInstance(RegraFinanceiraVO.findAll().list(), TipoRequest.CONSULTAR);
		} catch (Exception e) {
			LOG.error(e.getMessage());
			throw new GlobalException(ConstantesSistema.MSG_ERRO_GENERICO);
		}
	}

	public RegraFinanceiraRespostaDTO obterRegraFinanceiraFiltro(RegraFinanceiraDTO filtro) {
		try {
			return RegraFinanceiraRespostaDTO.newInstance(RegraFinanceiraVO.findAll(filtro), TipoRequest.CONSULTAR);
		} catch (NullPointerException e) {
			LOG.info(e.getMessage());
			throw new GlobalException(ConstantesSistema.COD_ERRO_INEXISTENTE, ConstantesSistema.MSG_ERRO_NAO_ENCONTRADO);
			
		}	catch (Exception e) {
			LOG.error(e.getStackTrace());
			throw new GlobalException(ConstantesSistema.MSG_ERRO_GENERICO);
		}
	}
	
	@Transactional
	public RegraFinanceiraRespostaDTO atualizarRegraFinanceira(RegraFinanceiraDTO filtro) {
		
		RegraFinanceiraVO voPersistente = null;
		
		try {
			
			if(filtro.getId() == 0 || filtro.getId().equals(null) || isNull(filtro.getId())) {
				throw new NoSuchFieldError(ConstantesSistema.MSG_ERRO_ID);
			}
			voPersistente = RegraFinanceiraVO.findById(filtro.getId());
			RegraFinanceiraVO newVo = RegraFinanceiraVO.dtoToVo(voPersistente, filtro);
			
			return RegraFinanceiraRespostaDTO.newInstance(Arrays.asList(newVo), TipoRequest.EDITAR);
		} catch (NullPointerException e) {
			LOG.info(e.getMessage());
			throw new GlobalException(ConstantesSistema.COD_ERRO_INEXISTENTE, ConstantesSistema.MSG_ERRO_NAO_ENCONTRADO);
		} catch(PropertyValueException e) { 
			LOG.info(e.getMessage());
			throw new GlobalException(ConstantesSistema.COD_ERRO_VALIDACAO, ConstantesSistema.MSG_ERRO_NULL);
		} catch (Exception e) {
			LOG.error(e.getMessage());
			throw new GlobalException(ConstantesSistema.MSG_ERRO_GENERICO);
		}
	}
	
	public RegraFinanceiraRespostaDTO cadastrarRegraFinanceira(RegraFinanceiraDTO filtro) {
		RegraFinanceiraVO vo = new RegraFinanceiraVO();
		
		try {
			vo = RegraFinanceiraVO.dtoToVo(vo, filtro);
			criarRegraFinanceiraSomente(vo);
			return RegraFinanceiraRespostaDTO.newInstance(Arrays.asList(vo), TipoRequest.CADASTRAR);
		} catch(ArcUndeclaredThrowableException e) {
			LOG.info(e.getMessage());
			throw new GlobalException(ConstantesSistema.COD_ERRO_VALIDACAO, "A placa informada já existe no sistema. Verifique se o veículo está correto");
		} catch(PropertyValueException e) { 
			LOG.info(e.getMessage());
			throw new GlobalException(ConstantesSistema.COD_ERRO_VALIDACAO, ConstantesSistema.MSG_ERRO_NULL);
		} catch (Exception e) {
			LOG.error(e.getMessage());
			throw new GlobalException(ConstantesSistema.MSG_ERRO_GENERICO);
		}
	}
	
	@Transactional
	protected RegraFinanceiraVO criarRegraFinanceiraSomente(RegraFinanceiraVO vo) {
		RegraFinanceiraVO.persist(vo);
		return vo;
		
	}
	
	public RegraFinanceiraRespostaDTO deletarRegraFinanceira(Integer id) {
		try {
			if(isNull(RegraFinanceiraVO.findById(id))) {
				throw new NullPointerException(ConstantesSistema.MSG_ERRO_NAO_ENCONTRADO); 
			}
			
			deletarRegraFinanceiraSomente(id);
			
			return RegraFinanceiraRespostaDTO.newInstance(Arrays.asList(), TipoRequest.EXCLUIR);
		} catch (NullPointerException e) {
			LOG.info(e.getMessage());
			throw new GlobalException(ConstantesSistema.COD_ERRO_INEXISTENTE, ConstantesSistema.MSG_ERRO_NAO_ENCONTRADO);
		} catch (ArcUndeclaredThrowableException e) {
			LOG.info(e.getCause());
			throw new GlobalException(ConstantesSistema.COD_ERRO_VALIDACAO, ConstantesSistema.MSG_ERRO_VINCULO);
		} catch (Exception e) {
			LOG.error(e.getMessage());
			throw new GlobalException(ConstantesSistema.MSG_ERRO_GENERICO);
		}
	}
	
	@Transactional
	protected Boolean deletarRegraFinanceiraSomente(Integer id) {
		return RegraFinanceiraVO.deleteById(id);
		
	}
}
