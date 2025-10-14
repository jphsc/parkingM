package br.com.rhscdeveloper.dto;

public class ErroDTO {

	private Integer codigoErro;
	private String mensagem;
	
	public ErroDTO() {
		
	}

	public ErroDTO(Integer codigoErrro, String mensagem) {
		this.codigoErro = codigoErrro;
		this.mensagem = mensagem;
	}

	public Integer getCodigoErro() {
		return codigoErro;
	}

	public void setCodigoErro(Integer codigoErrro) {
		this.codigoErro = codigoErrro;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
}
