package br.com.equiplano.seguradora.application.cliente.controller.v1;

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

import br.com.equiplano.seguradora.application.cliente.dto.request.v1.ClienteRequest;
import br.com.equiplano.seguradora.application.cliente.dto.response.v1.ClienteResponse;
import br.com.equiplano.seguradora.core.domain.entity.Cliente;
import br.com.equiplano.seguradora.core.service.ClienteService;
import br.com.equiplano.seguradora.core.util.Paginador;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;

@RestController
@RequestMapping("v1/clientes")
@Api(tags = "/v1/clientes", value = "API's para manipular clientes", authorizations = {@Authorization(value="jwtToken")})
@ApiResponses(value = {
		@ApiResponse(code = 201, message = "Retorno para a inclusão de cliente"),
		@ApiResponse(code = 401, message = "Erro de autenticação"),
		@ApiResponse(code = 403, message = "Erro de autorização"),
		@ApiResponse(code = 404, message = "Recurso não encontrado")}
)
@CrossOrigin(origins = "*", maxAge = 3600L)
public class ClienteController  {

	@Autowired
	private ClienteService service;
	
	@Autowired
	protected ModelMapper modelMapper;

	
	@ApiOperation(value = "API para salvar um cliente", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ClienteResponse> salvar(@RequestBody @Valid ClienteRequest clienteRequest, UriComponentsBuilder uriBuilder) {
		Cliente cliente = buildCliente(clienteRequest);
		this.service.salvar(cliente);
		URI uri = uriBuilder.path("{id}").buildAndExpand(cliente.getId()).toUri();
		return ResponseEntity.created(uri).body(buildClienteResponse(cliente));
	}
	
	@ApiOperation(value = "API para listar clientes", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@GetMapping
	@ResponseBody
	public List<ClienteResponse> listar(@PageableDefault(sort="id", direction = Direction.DESC) Pageable pageable) {
		 List<Cliente> clientes = service.capturarTodos(new Paginador(pageable.getOffset(), pageable.getPageNumber(), pageable.getPageSize(), false, false));
		return clientes.stream().map(this::buildClienteResponse).collect(Collectors.toList());
	}

	@ApiOperation(value = "API para capturar cliente por id", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@GetMapping("/{id}")
	@ResponseBody
	public ResponseEntity<ClienteResponse> catpurar(@PathVariable Long id) {
		Cliente cliente = service.capturarPorId(id);
		if (Objects.nonNull(cliente)) {
			ClienteResponse response = buildClienteResponse(cliente);
			return ResponseEntity.ok(response);
		}
		return ResponseEntity.notFound().build();
	}
	
	@ApiOperation(value = "API para atualizar o cliente", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PutMapping("/{id}")
	@ResponseBody
	public ResponseEntity<ClienteResponse> atualizar(@PathVariable Long id, @RequestBody @Valid ClienteRequest clienteRequest, UriComponentsBuilder uriBuilder) {
		Cliente cliente = buildCliente(clienteRequest);
		cliente.setId(id);
		this.service.atualizar(cliente);
		return ResponseEntity.ok(buildClienteResponse(cliente));
	}

	@ApiOperation(value = "API para deletar um cliente", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@DeleteMapping("/{id}")
	@ResponseBody
	public ResponseEntity<ClienteResponse> deletar(@PathVariable Long id) {
		this.service.deletar(id);
		return ResponseEntity.ok().build();
	}

	private ClienteResponse buildClienteResponse(Cliente cliente) {
		return modelMapper.map(cliente, ClienteResponse.class);	
	}
	
	private Cliente buildCliente(ClienteRequest clienteRequest) {
		return modelMapper.map(clienteRequest, Cliente.class);
	}

}
