package br.com.equiplano.seguradora.application.apolice.dto.response.v1;

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
public class ApoliceResponse {

	@ApiModelProperty(notes = "Id da apolice", name = "id")
	private Long id;
	
	@ApiModelProperty(notes = "Numero da apolice",name="nome")
	@NotBlank
	private String numero;
	
	@ApiModelProperty(notes = "início vigencia da apolice",name="cpf")
	@NotBlank
	private String inicioVigencia;
	
	@ApiModelProperty(notes = "fim vigencia da apolice",name="cidade")
	@NotBlank
	private String fimVigencia;
	
	@ApiModelProperty(notes = "placa do veículo",name="uf",required=true)
	@NotBlank
	private String placa;
	
	@ApiModelProperty(notes = "placa do veículo",name="uf",required=true)
	@NotBlank
	private String valor;
	
}
