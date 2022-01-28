package br.com.equiplano.seguradora.core.domain.vo;

import javax.persistence.Embeddable;

@Embeddable
public class Telefone {

	private String numeroCompleto;

	public Telefone(String telefone) {
		this.numeroCompleto = telefone;
	}
	
	@Deprecated
	public Telefone() {
	}

	public String getNumeroCompleto() {
		return numeroCompleto;
	}

	public void setNumeroCompleto(String numeroCompleto) {
		this.numeroCompleto = numeroCompleto;
	}
}
