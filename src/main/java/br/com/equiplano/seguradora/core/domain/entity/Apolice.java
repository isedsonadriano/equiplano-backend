package br.com.equiplano.seguradora.core.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.equiplano.seguradora.core.domain.enums.Status;
import lombok.Data;


@Entity
@Table(name = "EP_SEG_APOLICE")
@Data
public class Apolice {

	@Id 
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Column(unique = true)
	private String numero;
	
	@NotNull
	private LocalDate inicioVigencia;
	
	@NotNull
	private LocalDate fimVigencia;
	
	@NotBlank
	private String placa;
	
	@NotNull
	private BigDecimal valorApolice;
	
	@NotNull
	private Status status;
	
	@NotNull
	private LocalDateTime dataCadastro;
	
	private LocalDateTime dataAlteracao;
	
	private LocalDateTime dataExclusao;
	
	public boolean getVencida() {
		return this.fimVigencia.isBefore(LocalDate.now());
	}
	
	public String getVencimento() {
		return String.valueOf(Math.abs(ChronoUnit.DAYS.between(LocalDate.now(), getFimVigencia()))); 
	}

}
