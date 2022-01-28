package br.com.equiplano.seguradora.infra.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.equiplano.seguradora.application.exception.CampoExceptionDTO;
import br.com.equiplano.seguradora.application.exception.ExceptionDTO;
import br.com.equiplano.seguradora.core.exception.DomainException;

@RestControllerAdvice
public class GlobalErrorHandler{

	@Autowired
	private MessageSource source;

	@ResponseStatus(code = HttpStatus.BAD_GATEWAY)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<CampoExceptionDTO> handleMethodArguementException(MethodArgumentNotValidException exception) {
		List<CampoExceptionDTO> retorno = new ArrayList<CampoExceptionDTO>();
		List<FieldError> allErrors = exception.getBindingResult().getFieldErrors();
		allErrors.forEach(e -> {
			String mensagem = source.getMessage(e, LocaleContextHolder.getLocale());
			retorno.add(new CampoExceptionDTO(e.getField(), mensagem));
		});
		return retorno;
	}
	
	/*
	 * @ExceptionHandler({ TransactionSystemException.class }) public
	 * List<CampoExceptionDTO> handleConstraintViolation(Exception ex, WebRequest
	 * request) { List<CampoExceptionDTO> retorno = new
	 * ArrayList<CampoExceptionDTO>(); Throwable cause =
	 * ((TransactionSystemException) ex).getRootCause(); if (cause instanceof
	 * ConstraintViolationException) { Set<ConstraintViolation<?>>
	 * constraintViolations = ((ConstraintViolationException)
	 * cause).getConstraintViolations(); constraintViolations.forEach(e -> {
	 * retorno.add(new CampoExceptionDTO(e.getMessage(), e.getMessage())); }); }
	 * return retorno; }
	 */
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(DomainException.class)
	public ExceptionDTO handleDomainException(DomainException exception) {
		return new ExceptionDTO(exception.getMessage());

	}
}
