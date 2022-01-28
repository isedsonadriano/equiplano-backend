package br.com.equiplano.seguradora.core.service;

import java.util.List;

import br.com.equiplano.seguradora.core.domain.entity.Apolice;
import br.com.equiplano.seguradora.core.util.Paginador;

public interface ApoliceService {

	public List<Apolice> capturarTodos(Paginador paginador);

	public Apolice capturarPorId(Long id);

	public void salvar(Apolice associado);

	public void atualizar(Apolice associado);

	public void deletar(Long id);

	public List<Apolice> pesquisar(String numeroApolice);

}
