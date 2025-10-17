package br.com.rhscdeveloper.util;

import br.com.rhscdeveloper.enumerator.Enums.TipoRequest;

public class MensagemResposta<T> {
	
	public String gerarMensagem(TipoRequest tipoRequest, Class<?> classe) {

		String mensagem = "";
		String objeto = classe.getCanonicalName();
		String nHierarquiaObj[] = objeto.split("\\.");
		String nomeClasseCompleto = nHierarquiaObj[nHierarquiaObj.length - 1];
		String nomeClasse = nomeClasseCompleto.substring(0, nomeClasseCompleto.length()-2);
		
		switch(tipoRequest){
		case CONSULTAR:
			mensagem = "Registro(s) de "+nomeClasse+" obtido(s) com sucesso";
			break;
		case CADASTRAR:
			mensagem = "Registro de "+nomeClasse+" realizado com sucesso";
			break;
		case EDITAR:
			mensagem = "Registro de "+nomeClasse+" alterado com sucesso";
			break;
		case EXCLUIR:
			mensagem = "Registro de "+nomeClasse+" removido com sucesso";
			break;
		default:
			mensagem = "Não foi possível realizar a operação solicitada";
			
		}
		return mensagem;
	}
}
