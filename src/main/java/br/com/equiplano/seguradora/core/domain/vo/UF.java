package br.com.equiplano.seguradora.core.domain.vo;

import javax.persistence.Embeddable;

@Embeddable
public class UF {

	private String descricao;
	
	public UF(String descricao) {
		this.descricao = descricao;
	}

	@Deprecated
	public UF() {
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
