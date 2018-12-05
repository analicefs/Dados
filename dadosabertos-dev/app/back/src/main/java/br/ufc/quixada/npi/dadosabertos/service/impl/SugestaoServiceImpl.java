package br.ufc.quixada.npi.dadosabertos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.quixada.npi.dadosabertos.model.Sugestao;
import br.ufc.quixada.npi.dadosabertos.model.Sugestao.TipoRelato;
import br.ufc.quixada.npi.dadosabertos.repository.SugestaoRepository;
import br.ufc.quixada.npi.dadosabertos.service.SugestaoService;

@Service
public class SugestaoServiceImpl implements SugestaoService{

	@Autowired
	private SugestaoRepository sugestaoRepository;
	
	@Override
	public void saveSugestao(Sugestao sugestao) {
		sugestaoRepository.save(sugestao);
	}

	@Override
	public List<Sugestao> getAllSugestao() {
		return sugestaoRepository.findAll();
	}

	@Override
	public List<Sugestao> getTipoRelato(TipoRelato tipoRelato) {
		return sugestaoRepository.findByTipoRelato(tipoRelato);
	}
	
}
