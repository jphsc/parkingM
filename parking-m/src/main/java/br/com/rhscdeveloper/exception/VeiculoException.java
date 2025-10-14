package br.com.rhscdeveloper.exception;

public class VeiculoException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private final Integer codErro;
	private final String mensagem;
	
	public VeiculoException(String mensagem) {
		super(mensagem);
		this.codErro = 999;
		this.mensagem = mensagem;
	}

	public VeiculoException(Integer codErro, String mensagem) {
		super(mensagem);
		this.codErro = codErro;
		this.mensagem = mensagem;
	}

    public Integer getCodigoErro() {
        return codErro;
    }

    public String getMensagem() {
        return mensagem;
    }
}