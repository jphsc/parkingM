package br.com.rhscdeveloper.service;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.hibernate.PropertyValueException;
import org.hibernate.exception.ConstraintViolationException;
import org.jboss.logging.Logger;

import com.google.gson.Gson;

import br.com.rhscdeveloper.dto.ErroDTO;
import br.com.rhscdeveloper.dto.VeiculoDTO;
import br.com.rhscdeveloper.dto.VeiculoRespostaDTO;
import br.com.rhscdeveloper.enumerator.Enums.TipoOperacao;
import br.com.rhscdeveloper.exception.GlobalException;
import br.com.rhscdeveloper.exception.MyConstraintViolationException;
import br.com.rhscdeveloper.model.VeiculoVO;
import br.com.rhscdeveloper.util.ConstantesSistema;
import br.com.rhscdeveloper.util.GeracaoException;
import br.com.rhscdeveloper.util.MensagemResposta;
import io.quarkus.arc.ArcUndeclaredThrowableException;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class VeiculoService {
	
	private static final Logger LOG = Logger.getLogger(GlobalException.class);
	
	public VeiculoRespostaDTO obterVeiculoById(Integer id) {
		
		try {
			return VeiculoRespostaDTO.newInstance(Arrays.asList(VeiculoVO.findById(id)), TipoOperacao.CONSULTAR);
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

	public VeiculoRespostaDTO obterVeiculos() {
		try {
			List<VeiculoVO> veiculos = VeiculoVO.findAll(Sort.by("id")).list();
			
			if(veiculos.isEmpty()) {
				throw new NullPointerException(ConstantesSistema.MSG_ERRO_NAO_ENCONTRADO);
			}
			
			return VeiculoRespostaDTO.newInstance(veiculos, TipoOperacao.CONSULTAR);
		} catch (NullPointerException e) {
			LOG.info(ConstantesSistema.MSG_ERRO_NAO_ENCONTRADO);
			throw new GlobalException(ConstantesSistema.COD_ERRO_INEXISTENTE, ConstantesSistema.MSG_ERRO_NAO_ENCONTRADO);
			
		} catch (Exception e) {
			LOG.error(e.getMessage());
			e.printStackTrace();
			ErroDTO erro = GeracaoException.mensagemExceptionGenerica(e);
			throw new GlobalException(erro.getCodigo(), erro.getMensagem());
		}
	}

	public VeiculoRespostaDTO obterVeiculosFiltro(VeiculoDTO filtro) {
		try {
			Thread.sleep(4000);
			List<VeiculoVO> veiculos = VeiculoVO.findAll(filtro);
			
			if(veiculos.isEmpty()) {
				throw new NullPointerException(ConstantesSistema.MSG_ERRO_NAO_ENCONTRADO);
			}
			
			return VeiculoRespostaDTO.newInstance(veiculos, filtro, TipoOperacao.CONSULTAR);
		} catch (NullPointerException e) {
			LOG.info(e.getMessage());
			throw new GlobalException(ConstantesSistema.COD_ERRO_INEXISTENTE, e.getMessage());
			
		}	catch (Exception e) {
			LOG.error(e.getMessage());
			e.printStackTrace();
			ErroDTO erro = GeracaoException.mensagemExceptionGenerica(e);
			throw new GlobalException(erro.getCodigo(), erro.getMensagem());
		}
	}

	// TODO implementar excecao de veiculo nao encontrado
	// TODO implementar validacao dos enumeradores
	public VeiculoRespostaDTO atualizarVeiculo(VeiculoDTO filtro) {
		
		
		try {
			VeiculoVO voPersistente = atualizarVeiculoBase(filtro);
			VeiculoVO newVo = VeiculoVO.dtoToVo(voPersistente, filtro);
			
			return VeiculoRespostaDTO.newInstance(Arrays.asList(newVo), filtro, TipoOperacao.EDITAR);
		} catch (NullPointerException e) {
			LOG.info(e.getMessage());
			throw new GlobalException(ConstantesSistema.COD_ERRO_INEXISTENTE, ConstantesSistema.MSG_ERRO_NAO_ENCONTRADO);
			
		} catch(PropertyValueException e) { 
			LOG.info(e.getMessage());
			throw new GlobalException(ConstantesSistema.COD_ERRO_VALIDACAO, ConstantesSistema.MSG_ERRO_NULL);
			
		} catch(IllegalArgumentException e) { 
			LOG.info(e.getMessage());
			throw new GlobalException(ConstantesSistema.COD_ERRO_VALIDACAO, ConstantesSistema.MSG_ERRO_ID_REGISTRO);
			
		} catch(MyConstraintViolationException e) { 
			LOG.info(e.getMessage());
			throw new GlobalException(ConstantesSistema.COD_ERRO_VALIDACAO, e.getMessage());
			
		} catch (Exception e) {
			LOG.error(e.getMessage());
			e.printStackTrace();
			ErroDTO erro = GeracaoException.mensagemExceptionGenerica(e);
			throw new GlobalException(erro.getCodigo(), erro.getMensagem());
		}
	}
	
	@Transactional
	protected VeiculoVO atualizarVeiculoBase(VeiculoDTO filtro) {

		VeiculoVO voPersistenteId = VeiculoVO.findById(filtro.getId());
		VeiculoVO voPersistentePlaca = VeiculoVO.findByPlaca(filtro.getPlaca());
		
		if(nonNull(voPersistentePlaca) && voPersistenteId.getId() != voPersistentePlaca.getId()) {
			throw new MyConstraintViolationException("A placa informada já existe no sistema. Verifique se o veículo está correto");
		}
		
		return voPersistenteId;
	}

	// TODO implementar validacao dos enumeradores
	public VeiculoRespostaDTO cadastrarVeiculo(VeiculoDTO filtro) {
		
		try {
			VeiculoVO vo = new VeiculoVO(filtro.getModelo(), filtro.getMontadora(), new Date(), filtro.getPlaca(), new Date());
			
			cadastrarVeiculoBase(vo);
			return VeiculoRespostaDTO.newInstance(Arrays.asList(vo), TipoOperacao.CADASTRAR);
			
		} catch(NullPointerException e) {
			LOG.info(e.getMessage());
			throw new GlobalException(ConstantesSistema.COD_ERRO_VALIDACAO, e.getMessage());
			
		}  catch (ConstraintViolationException e) {
			LOG.warn(e.getMessage());
			throw new GlobalException(ConstantesSistema.COD_ERRO_VALIDACAO, "A placa informada já existe no sistema. Verifique se o veículo está correto");
			
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
	public VeiculoVO cadastrarVeiculoBase(VeiculoVO vo) {
		VeiculoVO voPersistente = VeiculoVO.findByPlaca(vo.getPlaca());
		
		if(isNull(voPersistente)) {
			VeiculoVO.persist(vo);
		} else {
			throw new MyConstraintViolationException("A placa informada já existe no sistema. Verifique se o veículo está correto");
		}
		
		return vo;
	}
	
	public String deletarVeiculo(Integer id) {
		try {
					
			deletarVeiculoBase(id);
			return new Gson().toJson(new MensagemResposta().gerarMensagem(TipoOperacao.EXCLUIR, VeiculoVO.class));
		} catch (NullPointerException e) {
			LOG.info(e.getMessage());
			throw new GlobalException(ConstantesSistema.COD_ERRO_INEXISTENTE, e.getMessage());
			
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
	public Boolean deletarVeiculoBase(Integer id) {
		VeiculoVO.findByIdOptional(id).orElseThrow(() -> new NullPointerException(ConstantesSistema.MSG_ERRO_NAO_ENCONTRADO));
		return VeiculoVO.deleteById(id);
	}
}

