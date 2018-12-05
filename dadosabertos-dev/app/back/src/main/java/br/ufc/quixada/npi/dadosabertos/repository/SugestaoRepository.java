package br.ufc.quixada.npi.dadosabertos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufc.quixada.npi.dadosabertos.model.Sugestao;
import br.ufc.quixada.npi.dadosabertos.model.Sugestao.Status;
import br.ufc.quixada.npi.dadosabertos.model.Sugestao.TipoRelato;

@Repository
public interface SugestaoRepository extends JpaRepository<Sugestao, Integer> {
	
	List<Sugestao> findByNome(String nome);
	List<Sugestao> findByEmail(String email);
	List<Sugestao> findByTipoRelato(TipoRelato tipoRelato);
	List<Sugestao> findByStatus(Status status);
}