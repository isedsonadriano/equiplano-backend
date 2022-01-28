package br.com.equiplano.seguradora.application.buscadorapolice.dto.response.v1;

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
public class BuscadorApoliceResponse {

	@ApiModelProperty(notes = "Id da apolice", name = "id")
	private Long id;
	
	@ApiModelProperty(notes = "Numero da apolice",name="nome")
	@NotBlank
	private Boolean vencida;
	
	@ApiModelProperty(notes = "início vigencia da apolice",name="cpf")
	@NotBlank
	private String vencimento;
	
	@ApiModelProperty(notes = "fim vigencia da apolice",name="cidade")
	@NotBlank
	private String placa;
	
	@ApiModelProperty(notes = "placa do veículo",name="uf",required=true)
	@NotBlank
	private String valor;
	
}
