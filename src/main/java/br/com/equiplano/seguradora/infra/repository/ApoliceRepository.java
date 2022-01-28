package br.com.equiplano.seguradora.infra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.equiplano.seguradora.core.domain.entity.Apolice;

@Repository
public interface ApoliceRepository extends JpaRepository<Apolice, Long> {

	List<Apolice> findByNumero(String numeroApolice);


}
