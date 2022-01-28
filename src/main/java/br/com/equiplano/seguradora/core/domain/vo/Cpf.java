package br.com.equiplano.seguradora.core.domain.vo;

import javax.persistence.Embeddable;

@Embeddable
public class Cpf {

	private String numeroCpf;

	public Cpf(String cpf) {
		this.numeroCpf = cpf;
	}
	
	@Deprecated
	public Cpf() {
	}

	public String getNumeroCpf() {
		return numeroCpf;
	}

	public void setNumeroCpf(String numero) {
		this.numeroCpf = numero;
	}
}
