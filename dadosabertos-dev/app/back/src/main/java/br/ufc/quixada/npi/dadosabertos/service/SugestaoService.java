package br.ufc.quixada.npi.dadosabertos.service;

import java.util.List;

import br.ufc.quixada.npi.dadosabertos.model.Sugestao;
import br.ufc.quixada.npi.dadosabertos.model.Sugestao.TipoRelato;

public interface SugestaoService {

	public void saveSugestao(Sugestao sugestao);
	public List<Sugestao> getAllSugestao();
	public List<Sugestao> getTipoRelato(TipoRelato tipoRelato);
}
