package br.com.equiplano.seguradora.application.apolice.controller.v1;

import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.equiplano.seguradora.application.apolice.dto.request.v1.ApoliceRequest;
import br.com.equiplano.seguradora.application.apolice.dto.response.v1.ApoliceResponse;
import br.com.equiplano.seguradora.core.domain.entity.Apolice;
import br.com.equiplano.seguradora.core.service.ApoliceService;
import br.com.equiplano.seguradora.core.util.Paginador;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;

@RestController
@RequestMapping("v1/apolices")
@Api(tags = "/v1/apolices", value = "API's para manipular apolices", authorizations = {@Authorization(value="jwtToken")})
@ApiResponses(value = {
		@ApiResponse(code = 201, message = "Retorno para a inclusão de apolice"),
		@ApiResponse(code = 401, message = "Erro de autenticação"),
		@ApiResponse(code = 403, message = "Erro de autorização"),
		@ApiResponse(code = 404, message = "Recurso não encontrado")}
)
@CrossOrigin(origins = "*", maxAge = 3600L)
public class ApoliceController  {

	@Autowired
	private ApoliceService service;
	
	@Autowired
	protected ModelMapper modelMapper;

	
	@ApiOperation(value = "API para salvar um apolice", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ApoliceResponse> salvar(@RequestBody @Valid ApoliceRequest apoliceRequest, UriComponentsBuilder uriBuilder) {
		Apolice apolice = buildApolice(apoliceRequest);
		this.service.salvar(apolice);
		URI uri = uriBuilder.path("{id}").buildAndExpand(apolice.getId()).toUri();
		return ResponseEntity.created(uri).body(buildApoliceResponse(apolice));
	}
	
	@ApiOperation(value = "API para listar apolices", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@GetMapping
	@ResponseBody
	public List<ApoliceResponse> listar(@PageableDefault(sort="id", direction = Direction.DESC) Pageable pageable) {
		 List<Apolice> apolices = service.capturarTodos(new Paginador(pageable.getOffset(), pageable.getPageNumber(), pageable.getPageSize(), false, false));
		return apolices.stream().map(this::buildApoliceResponse).collect(Collectors.toList());
	}

	@ApiOperation(value = "API para capturar apolice por id", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@GetMapping("/{id}")
	@ResponseBody
	public ResponseEntity<ApoliceResponse> catpurar(@PathVariable Long id) {
		Apolice apolice = service.capturarPorId(id);
		if (Objects.nonNull(apolice)) {
			ApoliceResponse response = buildApoliceResponse(apolice);
			return ResponseEntity.ok(response);
		}
		return ResponseEntity.notFound().build();
	}
	
	@ApiOperation(value = "API para atualizar o apolice", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PutMapping("/{id}")
	@ResponseBody
	public ResponseEntity<ApoliceResponse> atualizar(@PathVariable Long id, @RequestBody @Valid ApoliceRequest apoliceRequest, UriComponentsBuilder uriBuilder) {
		Apolice apolice = buildApolice(apoliceRequest);
		apolice.setId(id);
		this.service.atualizar(apolice);
		return ResponseEntity.ok(buildApoliceResponse(apolice));
	}

	@ApiOperation(value = "API para deletar um apolice", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@DeleteMapping("/{id}")
	@ResponseBody
	public ResponseEntity<ApoliceResponse> deletar(@PathVariable Long id) {
		this.service.deletar(id);
		return ResponseEntity.ok().build();
	}

	private ApoliceResponse buildApoliceResponse(Apolice apolice) {
		return modelMapper.map(apolice, ApoliceResponse.class);	
	}
	
	private Apolice buildApolice(ApoliceRequest apoliceRequest) {
		return modelMapper.map(apoliceRequest, Apolice.class);
	}

}
