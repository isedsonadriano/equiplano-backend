package br.com.equiplano.seguradora.core.domain.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.equiplano.seguradora.core.domain.enums.Status;
import br.com.equiplano.seguradora.core.domain.vo.Cpf;
import br.com.equiplano.seguradora.core.domain.vo.UF;
import lombok.Data;


@Entity
@Table(name = "EP_SEG_CLIENTE")
@Data
public class Cliente {

	@Id 
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String nome;
	
	@NotBlank
	private String cidade;
	
	@NotNull
	@Embedded
	private UF uf;

	@NotNull
	@Embedded
	@Column(unique = true)
	private Cpf cpf;
	
	
	@NotNull
	private Status status;
	
	@NotNull
	private LocalDateTime dataCadastro;
	
	private LocalDateTime dataAlteracao;
	
	private LocalDateTime dataExclusao;

}
