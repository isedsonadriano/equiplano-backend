package br.com.equiplano.seguradora.core.util;

import java.util.Objects;

public class UtilString {

	public static String retirarCaracteresNaoNumericos(String str) {
		if(Objects.isNull(str)) {
			return "";
		}
		return str.replaceAll("[^0-9]", "");
	}
}
