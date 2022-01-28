package br.com.equiplano.seguradora.application.cliente.dto.response.v1;

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
public class ClienteResponse {

	@ApiModelProperty(notes = "Id do cliente", name = "id")
	private Long id;
	
	@ApiModelProperty(notes = "Nome do cliente",name="nome")
	private String nome;
	
	@ApiModelProperty(notes = "Cpf do cliente",name="cpf")
	private String cpf;
	
	@ApiModelProperty(notes = "Cpf do cliente",name="cpf",required=true)
	private String cidade;
	
	@ApiModelProperty(notes = "uf do cliente",name="cpf",required=true)
	private String uf;
	
}
