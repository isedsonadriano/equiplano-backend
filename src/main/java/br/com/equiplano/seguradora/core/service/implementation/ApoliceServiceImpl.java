package br.com.equiplano.seguradora.core.service.implementation;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.javafaker.Business;

import br.com.equiplano.seguradora.core.domain.entity.Apolice;
import br.com.equiplano.seguradora.core.domain.enums.Status;
import br.com.equiplano.seguradora.core.exception.DomainException;
import br.com.equiplano.seguradora.core.service.ApoliceService;
import br.com.equiplano.seguradora.core.util.Paginador;
import br.com.equiplano.seguradora.core.util.UtilString;
import br.com.equiplano.seguradora.infra.repository.ApoliceRepository;

@Service
public class ApoliceServiceImpl implements ApoliceService {

	@Autowired
	private ApoliceRepository apoliceRepository;


	@Override
	public Apolice capturarPorId(Long id) {
		return apoliceRepository.findById(id).get();
	}

	@Override
	public void salvar(Apolice apolice) {
		apolice.setNumero(criarNumeroApolice());
		apolice.setStatus(Status.ATIVO);
		apolice.setDataCadastro(LocalDateTime.now());
		this.apoliceRepository.save(apolice);
	}

	private String criarNumeroApolice() {
		return UtilString.retirarCaracteresNaoNumericos(new Date().toString() + new Random().nextInt(122345688));
	}

	@Override
	public void atualizar(Apolice apolice) {
		Optional<Apolice> apoliceAtualizar = this.apoliceRepository.findById(apolice.getId());
		if(apoliceAtualizar.isPresent()) {
			apoliceAtualizar.get().setInicioVigencia(apolice.getInicioVigencia());
			apoliceAtualizar.get().setFimVigencia(apolice.getFimVigencia());
			apoliceAtualizar.get().setValorApolice(apolice.getValorApolice());
			apoliceAtualizar.get().setPlaca(apolice.getPlaca());
			apoliceAtualizar.get().setDataAlteracao(LocalDateTime.now());
			this.apoliceRepository.save(apoliceAtualizar.get());
		}else {
			throw new DomainException("Apólice inexistente!");
		}
	}

	@Override
	public void deletar(Long id) {
		Apolice apolice = apoliceRepository.findById(id).get();
		apolice.setDataExclusao(LocalDateTime.now());
		apolice.setStatus(Status.INATIVO);
		this.apoliceRepository.save(apolice);
	}


	@Override
	public List<Apolice> capturarTodos(Paginador paginador) {
		return this.apoliceRepository.findAll();
	}

	@Override
	public List<Apolice> pesquisar(String numeroApolice) {
		return this.apoliceRepository.findByNumero(numeroApolice);
	}
}
