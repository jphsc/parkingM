package br.com.rhscdeveloper.util;

import br.com.rhscdeveloper.dto.ErroDTO;
import jakarta.transaction.RollbackException;

public class GeracaoException {

	public static ErroDTO mensagemExceptionGenerica(Throwable e) {
		
		ErroDTO dto = new ErroDTO();
		
		if(e.getCause() instanceof RollbackException) {
			dto.setCodigo(ConstantesSistema.COD_ERRO_VALIDACAO);
			dto.setMensagem(ConstantesSistema.MSG_ERRO_CAMPOS);
			return dto;
		}
		dto.setCodigo(999);
		dto.setMensagem(ConstantesSistema.MSG_ERRO_GENERICO);
		return dto;
	}
}
