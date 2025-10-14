package br.com.rhscdeveloper.service;

import static java.util.Objects.isNull;

import java.util.Arrays;
import java.util.Date;

import org.hibernate.exception.ConstraintViolationException;
import org.jboss.logging.Logger;

import br.com.rhscdeveloper.dto.VeiculoDTO;
import br.com.rhscdeveloper.dto.VeiculoRespostaDTO;
import br.com.rhscdeveloper.enumerator.Enums.TipoRequest;
import br.com.rhscdeveloper.exception.VeiculoException;
import br.com.rhscdeveloper.model.VeiculoVO;
import br.com.rhscdeveloper.util.ConstantesSistema;
import io.quarkus.arc.ArcUndeclaredThrowableException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class VeiculoService {
	
	private static final Logger LOG = Logger.getLogger(VeiculoException.class);

	@Transactional
	public VeiculoRespostaDTO cadastrarVeiculo(VeiculoDTO filtro) {
		VeiculoVO vo = new VeiculoVO(filtro.getModelo(), filtro.getMontadora(), new Date(), filtro.getPlaca(), filtro.getCor(), new Date());
		
		try {
			VeiculoVO.persist(vo);
			return VeiculoRespostaDTO.newInstance(Arrays.asList(vo), TipoRequest.CADASTRAR);
		} catch(ConstraintViolationException e) {
			LOG.info(e.getErrorMessage());
			throw new VeiculoException(ConstantesSistema.COD_ERRO_VALIDACAO, "A placa informada já existe no sistema. Verifique se o veículo está correto");
		} catch (Exception e) {
			LOG.error(e.getMessage());
			throw new VeiculoException(ConstantesSistema.MSG_ERRO_GENERICO);
		}
	}
	
	public VeiculoRespostaDTO obterVeiculoById(Integer id) {
		
		try {
			return VeiculoRespostaDTO.newInstance(Arrays.asList(VeiculoVO.findById(id)), TipoRequest.CONSULTAR);
		} catch (NullPointerException e) {
			LOG.info(e.getMessage());
			throw new VeiculoException(ConstantesSistema.COD_ERRO_INEXISTENTE,  ConstantesSistema.MSG_ERRO_NAO_ENCONTRADO);
			
		}	catch (Exception e) {
			LOG.error(e.getStackTrace());
			throw new VeiculoException(ConstantesSistema.MSG_ERRO_GENERICO);
		}
		
	}

	public VeiculoRespostaDTO obterVeiculos() {
		try {
			return VeiculoRespostaDTO.newInstance(VeiculoVO.findAll().list(), TipoRequest.CONSULTAR);
		} catch (Exception e) {
			LOG.error(e.getMessage());
			throw new VeiculoException(ConstantesSistema.MSG_ERRO_GENERICO);
		}
	}

	public VeiculoRespostaDTO obterVeiculosFiltro(VeiculoDTO filtro) {
		try {
			return VeiculoRespostaDTO.newInstance(VeiculoVO.findAll(filtro), filtro, TipoRequest.CONSULTAR);
		} catch (NullPointerException e) {
			LOG.info(e.getMessage());
			throw new VeiculoException(ConstantesSistema.COD_ERRO_INEXISTENTE,  ConstantesSistema.MSG_ERRO_NAO_ENCONTRADO);
			
		}	catch (Exception e) {
			LOG.error(e.getStackTrace());
			throw new VeiculoException(ConstantesSistema.MSG_ERRO_GENERICO);
		}
	}
	
	@Transactional
	public VeiculoRespostaDTO atualizarVeiculo(VeiculoDTO filtro) {
		
		VeiculoVO voPersistente = null;
		
		try {
			voPersistente = VeiculoVO.findById(filtro.getId());
			VeiculoVO newVo = VeiculoVO.veiculoToVeiculo(voPersistente, filtro);
			
			return VeiculoRespostaDTO.newInstance(Arrays.asList(newVo), filtro, TipoRequest.EDITAR);
		} catch (NullPointerException e) {
			LOG.info(e.getMessage());
			throw new VeiculoException(ConstantesSistema.COD_ERRO_INEXISTENTE,  ConstantesSistema.MSG_ERRO_NAO_ENCONTRADO);
			
		}	catch (Exception e) {
			LOG.error(e.getStackTrace());
			throw new VeiculoException(ConstantesSistema.MSG_ERRO_GENERICO);
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
			throw new VeiculoException(ConstantesSistema.COD_ERRO_INEXISTENTE,  ConstantesSistema.MSG_ERRO_NAO_ENCONTRADO);
		} catch (ArcUndeclaredThrowableException e) {
			LOG.info(e.getCause());
			throw new VeiculoException(ConstantesSistema.COD_ERRO_VALIDACAO,  ConstantesSistema.MSG_ERRO_VINCULO);
		} catch (Exception e) {
			LOG.error(e.getMessage());
			throw new VeiculoException(ConstantesSistema.MSG_ERRO_GENERICO);
		}
	}
}
