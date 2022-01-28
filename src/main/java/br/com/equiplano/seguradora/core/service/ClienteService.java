package br.com.equiplano.seguradora.core.service;

import java.util.List;

import br.com.equiplano.seguradora.core.domain.entity.Cliente;
import br.com.equiplano.seguradora.core.util.Paginador;

public interface ClienteService {

	public List<Cliente> capturarTodos(Paginador paginador);

	public Cliente capturarPorId(Long id);

	public void salvar(Cliente associado);

	public void atualizar(Cliente associado);

	public void deletar(Long id);

}
