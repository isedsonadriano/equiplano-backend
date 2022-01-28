package br.com.equiplano.seguradora.core.service.implementation;

import static java.util.Objects.nonNull;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.equiplano.seguradora.core.domain.entity.Cliente;
import br.com.equiplano.seguradora.core.domain.enums.Status;
import br.com.equiplano.seguradora.core.exception.DomainException;
import br.com.equiplano.seguradora.core.service.ClienteService;
import br.com.equiplano.seguradora.core.util.Paginador;
import br.com.equiplano.seguradora.infra.repository.ClienteRepository;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteRepository clientRepository;


	@Override
	public Cliente capturarPorId(Long id) {
		return clientRepository.findById(id).get();
	}

	@Override
	public void salvar(Cliente cliente) {
		DomainException.throwIf(nonNull(this.clientRepository.findByCpfNumeroCpf(cliente.getCpf().getNumeroCpf())), "Cliente já cadastrado");
		cliente.setStatus(Status.ATIVO);
		cliente.setDataCadastro(LocalDateTime.now());
		this.clientRepository.save(cliente);
	}

	@Override
	public void atualizar(Cliente cliente) {
		Optional<Cliente> clienteAtualizar = this.clientRepository.findById(cliente.getId());
		if(clienteAtualizar.isPresent()) {
			clienteAtualizar.get().setCpf(cliente.getCpf());
			clienteAtualizar.get().setNome(cliente.getNome());
			clienteAtualizar.get().setCidade(cliente.getCidade());
			clienteAtualizar.get().setUf(cliente.getUf());
			clienteAtualizar.get().setDataAlteracao(LocalDateTime.now());
			this.clientRepository.save(clienteAtualizar.get());
		}else {
			throw new DomainException("Cliente inexistente");
		}
	}

	@Override
	public void deletar(Long id) {
		Cliente cliente = clientRepository.findById(id).get();
		cliente.setDataExclusao(LocalDateTime.now());
		cliente.setStatus(Status.INATIVO);
		this.clientRepository.save(cliente);
	}


	@Override
	public List<Cliente> capturarTodos(Paginador paginador) {
		return this.clientRepository.findAll();
	}
}
