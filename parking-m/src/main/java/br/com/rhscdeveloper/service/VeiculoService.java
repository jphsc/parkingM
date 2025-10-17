package br.com.rhscdeveloper.service;

import static java.util.Objects.isNull;

import java.util.Arrays;
import java.util.Date;

import org.hibernate.PropertyValueException;
import org.jboss.logging.Logger;

import br.com.rhscdeveloper.dto.VeiculoDTO;
import br.com.rhscdeveloper.dto.VeiculoRespostaDTO;
import br.com.rhscdeveloper.enumerator.Enums.TipoRequest;
import br.com.rhscdeveloper.exception.MyConstraintViolationException;
import br.com.rhscdeveloper.exception.GlobalException;
import br.com.rhscdeveloper.model.VeiculoVO;
import br.com.rhscdeveloper.util.ConstantesSistema;
import io.quarkus.arc.ArcUndeclaredThrowableException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class VeiculoService {
	
	private static final Logger LOG = Logger.getLogger(GlobalException.class);
	
	public VeiculoRespostaDTO obterVeiculoById(Integer id) {
		
		try {
			return VeiculoRespostaDTO.newInstance(Arrays.asList(VeiculoVO.findById(id)), TipoRequest.CONSULTAR);
		} catch (NullPointerException e) {
			LOG.info(e.getMessage());
			throw new GlobalException(ConstantesSistema.COD_ERRO_INEXISTENTE, ConstantesSistema.MSG_ERRO_NAO_ENCONTRADO);
			
		}	catch (Exception e) {
			LOG.error(e.getStackTrace());
			throw new GlobalException( ConstantesSistema.MSG_ERRO_GENERICO);
		}
		
	}

	public VeiculoRespostaDTO obterVeiculos() {
		try {
			return VeiculoRespostaDTO.newInstance(VeiculoVO.findAll().list(), TipoRequest.CONSULTAR);
		} catch (Exception e) {
			LOG.error(e.getMessage());
			throw new GlobalException( ConstantesSistema.MSG_ERRO_GENERICO);
		}
	}

	public VeiculoRespostaDTO obterVeiculosFiltro(VeiculoDTO filtro) {
		try {
			return VeiculoRespostaDTO.newInstance(VeiculoVO.findAll(filtro), filtro, TipoRequest.CONSULTAR);
		} catch (NullPointerException e) {
			LOG.info(e.getMessage());
			throw new GlobalException(ConstantesSistema.COD_ERRO_INEXISTENTE, ConstantesSistema.MSG_ERRO_NAO_ENCONTRADO);
			
		}	catch (Exception e) {
			LOG.error(e.getStackTrace());
			throw new GlobalException( ConstantesSistema.MSG_ERRO_GENERICO);
		}
	}
	
	@Transactional
	public VeiculoRespostaDTO atualizarVeiculo(VeiculoDTO filtro) {
		
		VeiculoVO voPersistente = null;
		
		try {
			voPersistente = VeiculoVO.findById(filtro.getId());
			VeiculoVO newVo = VeiculoVO.dtoToVo(voPersistente, filtro);
			
			return VeiculoRespostaDTO.newInstance(Arrays.asList(newVo), filtro, TipoRequest.EDITAR);
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
	
	@Transactional
	public VeiculoVO criarVeiculoSomente(VeiculoVO vo) {
		
		if(isNull(VeiculoVO.findByPlaca(vo.getPlaca()))) {
			VeiculoVO.persist(vo);
		} else {
			throw new MyConstraintViolationException("A placa informada já existe no sistema. Verifique se o veículo está correto");
		}
		
		return vo;
	}
	
	public VeiculoRespostaDTO cadastrarVeiculo(VeiculoDTO filtro) {
		VeiculoVO vo = new VeiculoVO(filtro.getModelo(), filtro.getMontadora(), new Date(), filtro.getPlaca(), filtro.getCor(), new Date());
		
		try {
			criarVeiculoSomente(vo);
			return VeiculoRespostaDTO.newInstance(Arrays.asList(vo), TipoRequest.CADASTRAR);
			
		} catch(MyConstraintViolationException e) {
			LOG.info(e.getMessage());
			throw new GlobalException(ConstantesSistema.COD_ERRO_VALIDACAO, e.getMessage());
		} catch(PropertyValueException e) { 
			LOG.info(e.getMessage());
			throw new GlobalException(ConstantesSistema.COD_ERRO_VALIDACAO, ConstantesSistema.MSG_ERRO_NULL);
		} catch (Exception e) {
			LOG.error(e.getMessage());
			throw new GlobalException(ConstantesSistema.MSG_ERRO_GENERICO);
		}
	}
	
	@Transactional
	public Boolean deletarVeiculoSomente(Integer id) {
		return VeiculoVO.deleteById(id);
		
	}
	
	public VeiculoRespostaDTO deletarVeiculo(Integer id) {
		try {
			if(isNull(VeiculoVO.findById(id))) {
				throw new NullPointerException(ConstantesSistema.MSG_ERRO_NAO_ENCONTRADO); 
			}
			
			deletarVeiculoSomente(id);
			
			return VeiculoRespostaDTO.newInstance(Arrays.asList(), TipoRequest.EXCLUIR);
		} catch (NullPointerException e) {
			LOG.info(e.getMessage());
			throw new GlobalException(ConstantesSistema.COD_ERRO_INEXISTENTE, e.getMessage());
			
		} catch (ArcUndeclaredThrowableException e) {
			LOG.info(e.getCause());
			throw new GlobalException(ConstantesSistema.COD_ERRO_VALIDACAO, ConstantesSistema.MSG_ERRO_VINCULO);
			
		} catch (Exception e) {
			LOG.error(e.getMessage());
			throw new GlobalException(ConstantesSistema.MSG_ERRO_GENERICO);
		}
	}
}
