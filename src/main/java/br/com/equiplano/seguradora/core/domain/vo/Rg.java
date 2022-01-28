package br.com.equiplano.seguradora.core.domain.vo;

import javax.persistence.Embeddable;

@Embeddable
public class Rg {

	private String numeroRg;

	public Rg(String rg) {
		this.numeroRg = rg;
	}
	
	@Deprecated
	public Rg() {
	}

	public String getNumeroRg() {
		return numeroRg;
	}

	public void setNumeroRg(String numero) {
		this.numeroRg = numero;
	}
}
