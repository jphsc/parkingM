package br.com.rhscdeveloper.exception;

import br.com.rhscdeveloper.dto.ErroDTO;
import br.com.rhscdeveloper.util.ConstantesSistema;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class GlobalExceptionMapper implements ExceptionMapper<GlobalException> {
	
	@Override
	public Response toResponse(GlobalException e) {

        ErroDTO erroResposta = new ErroDTO(e.getCodigoErro(), e.getMensagem());
        
        Status statusCode = Status.INTERNAL_SERVER_ERROR;
        
        if(e.getCodigoErro() == ConstantesSistema.COD_ERRO_INEXISTENTE) {
        	statusCode = Status.NOT_FOUND;
        } else if(e.getCodigoErro() == ConstantesSistema.COD_ERRO_INTERNO) {
        	statusCode = Status.FORBIDDEN;
        } else if(e.getCodigoErro() == ConstantesSistema.COD_ERRO_VALIDACAO) {
        	statusCode = Status.BAD_REQUEST;
        }
        
        return Response
        		.status(statusCode)
        		.entity(erroResposta)
                .build();
	}
}
