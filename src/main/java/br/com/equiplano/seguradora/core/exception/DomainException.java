package br.com.equiplano.seguradora.core.exception;

public class DomainException extends RuntimeException {

	private static final long serialVersionUID = 7340259249862978112L;

	public DomainException() {
		super();
	}

	public DomainException(String msg) {
		super(msg);
	}

	public DomainException(Exception e) {
		super(e);
	}
	

	public static void throwIf(boolean condicao, String mensagem) {
		if (condicao) {
			throw new DomainException(mensagem);
		}
	}
	
}
