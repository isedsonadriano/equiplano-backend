package br.com.equiplano.seguradora.application.cliente.dto.request.v1;


import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClienteRequest {

	@ApiModelProperty(notes = "Nome do cliente",name="nome")
	@NotBlank
	private String nome;
	
	@ApiModelProperty(notes = "Cpf do cliente",name="cpf")
	@NotBlank
	private String cpf;
	
	@ApiModelProperty(notes = "Cpf do cliente",name="cidade")
	@NotBlank
	private String cidade;
	
	@ApiModelProperty(notes = "uf do cliente",name="uf")
	@NotBlank
	private String uf;
	

}
