package br.com.equiplano.seguradora.application.buscadorapolice.controller.v1;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.equiplano.seguradora.application.buscadorapolice.dto.response.v1.BuscadorApoliceResponse;
import br.com.equiplano.seguradora.core.domain.entity.Apolice;
import br.com.equiplano.seguradora.core.service.ApoliceService;
import br.com.equiplano.seguradora.core.util.Paginador;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("v1/buscadorapolices")
@Api(tags = "/v1/buscadorapolices", value = "API's para busca especializada de  apolices")
@ApiResponses(value = {
		@ApiResponse(code = 201, message = "Retorno para a inclusão de apolice"),
		@ApiResponse(code = 401, message = "Erro de autenticação"),
		@ApiResponse(code = 403, message = "Erro de autorização"),
		@ApiResponse(code = 404, message = "Recurso não encontrado")}
)
@CrossOrigin(origins = "*", maxAge = 3600L)
public class BuscadorApoliceController  {

	@Autowired
	private ApoliceService service;
	
	@Autowired
	protected ModelMapper modelMapper;

	@ApiOperation(value = "API para listar apolices", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@GetMapping("/{numeroApolice}")
	@ResponseBody
	public List<BuscadorApoliceResponse> listar(@PathVariable String numeroApolice) {
		 List<Apolice> apolices = service.pesquisar(numeroApolice);
		return apolices.stream().map(this::buildApoliceResponse).collect(Collectors.toList());
	}

	private BuscadorApoliceResponse buildApoliceResponse(Apolice apolice) {
		return modelMapper.map(apolice, BuscadorApoliceResponse.class);	
	}
	

}
