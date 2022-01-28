package br.com.equiplano.seguradora.core.domain.vo;

import java.util.Random;

public enum TipoPlano {

	INDIVIDUAL("I"),
    EMPRESARIAL("E"),
    NAO_INFORMADO("NI");

    private String value;

	TipoPlano(String value) {
		this.value = value;
	}

	@Deprecated
		TipoPlano() {
	}

    @Override
    public String toString() {
        return this.value;
    }

    public static TipoPlano fromValue(String text){
         for(TipoPlano tipo: values()){
             if(String.valueOf(tipo.value).equalsIgnoreCase(text)){
                 return tipo;
             }
         }
         return NAO_INFORMADO;
    }
    
    public static TipoPlano getRandomTipoAssociado() {
    	return values()[new Random().nextInt(2)];
    }
}

