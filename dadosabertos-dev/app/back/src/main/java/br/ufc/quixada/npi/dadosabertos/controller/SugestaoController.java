package br.ufc.quixada.npi.dadosabertos.controller;

import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.ufc.quixada.npi.dadosabertos.model.Sugestao;
import br.ufc.quixada.npi.dadosabertos.model.Sugestao.Status;
import br.ufc.quixada.npi.dadosabertos.model.Sugestao.TipoRelato;
import br.ufc.quixada.npi.dadosabertos.service.SugestaoService;
import br.ufc.quixada.npi.dadosabertos.validation.Validator;

@RestController
@RequestMapping(path = "/api/v1/sugestao")
public class SugestaoController {
	
	@Autowired
	private SugestaoService sugestaoService;

	@RequestMapping(path="/add", method=RequestMethod.POST)
	public void saveSugestao(
			@RequestParam("nome") String nome, 
			@RequestParam("email") String email,
			@RequestParam("relato") TipoRelato tipoRelato, 
			@RequestParam("obs") String observacao){
		Timestamp tempo = new Timestamp(ZonedDateTime.now().toInstant().toEpochMilli());
		Validator.getInstance().saveSugestaoValidation(nome, email, tipoRelato.toString(), observacao);
		sugestaoService.saveSugestao(new Sugestao(nome, email, tipoRelato, observacao, Status.novo, tempo));
	}
	
	@RequestMapping(path="/getAll")
	public List<Sugestao> getAllSugestao(){
		return sugestaoService.getAllSugestao();
	}
	
	@RequestMapping(path="/getTipoRelato")
	public List<Sugestao> getTipoRelato(@RequestParam("relato") TipoRelato tipoRelato){
		return sugestaoService.getTipoRelato(tipoRelato);
	}
}
