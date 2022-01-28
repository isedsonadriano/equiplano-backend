package br.com.equiplano.seguradora.core.util;

import static java.util.Objects.isNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class UtilData {

	private static final String DATE_FORMAT = "dd/MM/yyyy";

	public static LocalDateTime converterStringParaLocalDateTime(String str) {
		try {
			if (isNull(str)) {
				return null;
			}
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
			LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
			return dateTime;
		} catch (Exception e) {
			return null;
		}
	}
	
	public static LocalDate converterStringParaLocalDate(String str) {
		if (Objects.isNull(str)) {
			return null;
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
		return LocalDate.parse(str, formatter);
	}
	
	public static String converterLocalDateParaString(LocalDate localDate) {
		if (Objects.isNull(localDate)) {
			return null;
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
		return localDate.format(formatter);
	}
}
