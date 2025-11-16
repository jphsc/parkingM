package br.com.rhscdeveloper.util;

public class ConstantesSistema {

	// Codigos erro do sistema
	public static final Integer COD_ERRO_INEXISTENTE = 100;
	public static final Integer COD_ERRO_VALIDACAO = 200;
	public static final Integer COD_ERRO_INTERNO = 300;
	public static final Integer COD_ERRO_SERVIDOR_INTERNO = 999;
	
	// Mensagens de erro
	public static final String MSG_ERRO_GENERICO = "Sistema temporariamente indisponível, tente novamente mais tarde";
	public static final String MSG_ERRO_NAO_ENCONTRADO = "O registro solicitado não foi encontrado, verifique a solicitação";
	public static final String MSG_ERRO_VINCULO = "O registro solicitado possui vínculos que impedem a exclusão, verifique os vínculos.";
	public static final String MSG_ERRO_ID = "O identificador do registro não foi informado, informe o identificador para realizar a operção desejada.";
	public static final String MSG_ERRO_NULL = "Há campos obrigatórios que não foram informados no registro.";
	public static final String MSG_ERRO_CAMPOS = "Há campos obrigatórios que não foram informados no registro.";
	public static final String MSG_ERRO_ID_REGISTRO = "É necessário informar o id do registro";
}
